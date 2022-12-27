package com.techmo.personalshopper.service;

import com.techmo.personalshopper.dto.PriceInputDto;
import com.techmo.personalshopper.dto.PriceRangeDto;
import com.techmo.personalshopper.dto.ResponseDto;
import com.techmo.personalshopper.util.PriceConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    TechMoService techMoService;

    public VehicleService(TechMoService techMoService) {
        this.techMoService = techMoService;
    }

    public static int getRoundedPrice(double price) {

        double priceQuotient = price / PriceConstants.roundValue;
        return (int) priceQuotient * PriceConstants.roundValue;
    }
    public static List<Integer> getRandomAgenciesPrices(ArrayList<Double> percentages, double infoAutoPrice) {

        // We only sum the negative values
        double percentageSum = -0.25;

        // Generate random percentages
        for (Double perc: percentages) {
            if (perc < 0.0) {
                percentageSum = percentageSum + perc;
            }
        }

        double percent1 = percentageSum - 0.025;
        double percent2 = percentageSum - 0.005;

        // Generate random prices
        List<Integer> prices = new ArrayList<>();
        prices.add(getRoundedPrice(infoAutoPrice + infoAutoPrice * percent1));
        prices.add(getRoundedPrice(infoAutoPrice + infoAutoPrice * percent2));
        return prices;
    }

    public ResponseDto<PriceRangeDto> getPriceRange(PriceInputDto dto) throws RuntimeException {
        try {

            Double priceFromInfoautoApi = techMoService.getPrice(dto.year, dto.versionId);
            assert priceFromInfoautoApi != null;

            boolean sellingTimeFound = PriceConstants.availableSellingTime.containsKey(dto.sellingTime);
            boolean colourFound = PriceConstants.colour.containsKey(dto.colour);
            boolean kilometersFound = PriceConstants.kilometers.containsKey(dto.kilometers);
            boolean stateFound = PriceConstants.state.containsKey(dto.state);
            boolean ageFound = PriceConstants.age.containsKey(dto.age);

            if (!(sellingTimeFound || colourFound || kilometersFound || stateFound || ageFound)) {
                return new ResponseDto<>(HttpStatus.BAD_REQUEST, "Invalid parameters", null);
            }

            Double startingPerc = PriceConstants.startingPercentageDiscount;

            Double sellingTimePerc = PriceConstants.availableSellingTime.get(dto.sellingTime);
            Double colourPerc = PriceConstants.colour.get(dto.colour);
            Double kilometersPerc = PriceConstants.kilometers.get(dto.kilometers);
            Double statePerc = PriceConstants.state.get(dto.state);
            Double agePerc = PriceConstants.state.get(dto.state);

            ArrayList<Double> percentages = new ArrayList<Double>();
            percentages.add(sellingTimePerc);
            percentages.add(colourPerc);
            percentages.add(kilometersPerc);
            percentages.add(statePerc);
            percentages.add(agePerc);

            double percentageSum = startingPerc + sellingTimePerc + colourPerc + kilometersPerc + statePerc + agePerc;
            Double finalPercentage = 1 + percentageSum;

            double finalResult = priceFromInfoautoApi * finalPercentage;

            int minRangeValue = getRoundedPrice((finalResult - finalResult * PriceConstants.rangeVariation));
            int maxRangeValue = getRoundedPrice((finalResult + finalResult * PriceConstants.rangeVariation));

            List<Integer> prices = getRandomAgenciesPrices(
                    percentages,
                    priceFromInfoautoApi
            );

            PriceRangeDto rangeDto = new PriceRangeDto(minRangeValue, maxRangeValue, prices);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", rangeDto);

        } catch (Error e) {
            System.out.println(e.getMessage());
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
    }
}
