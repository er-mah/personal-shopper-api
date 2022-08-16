package com.mah.personalshopper.service;

import com.mah.personalshopper.dto.PriceInputDto;
import com.mah.personalshopper.dto.PriceRangeDto;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.model.constants.PriceConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    MahService mahService;

    public VehicleService(MahService mahService) {
        this.mahService = mahService;
    }

    public static int getRoundedPrice(double price) {

        double priceQuotient = price / PriceConstants.roundValue;
        return (int) priceQuotient * PriceConstants.roundValue;
    }
    public static List<Integer> getRandomAgenciesPrices(double min, double max, double finalResult) {

        List<Double> percentages = new ArrayList<>();
        // Generate random percentages
        for (int i = 0; i < 2; i++) {
            percentages.add(((Math.random() * ((max - min) + 1)) + min) / 100);
        }

        // Generate random prices
        List<Integer> prices = new ArrayList<>();
        for (double p: percentages) {
            double newPrice = finalResult - (finalResult * p);
            prices.add(getRoundedPrice(newPrice));
        }

        return prices;
    }

    public ResponseDto<PriceRangeDto> getPriceRange(PriceInputDto dto) throws RuntimeException, NullPointerException {
        try {

            Double priceFromInfoautoApi = mahService.getPrice(dto.year, dto.versionId);
            assert priceFromInfoautoApi != null;

            boolean sellingTimeFound = PriceConstants.availableSellingTime.containsKey(dto.sellingTime);
            boolean colourFound = PriceConstants.colour.containsKey(dto.colour);
            boolean kilometersFound = PriceConstants.kilometers.containsKey(dto.kilometers);
            boolean stateFound = PriceConstants.state.containsKey(dto.state);

            if (!(sellingTimeFound || colourFound || kilometersFound || stateFound)) {
                return new ResponseDto<>(HttpStatus.BAD_REQUEST, "Invalid parameters", null);
            }

            Double startingPerc = PriceConstants.startingPercentageDiscount;
            Double sellingTimePerc = PriceConstants.availableSellingTime.get(dto.sellingTime);
            Double colourPerc = PriceConstants.colour.get(dto.colour);
            Double kilometersPerc = PriceConstants.kilometers.get(dto.kilometers);
            Double statePerc = PriceConstants.state.get(dto.state);

            double percentageSum = startingPerc + sellingTimePerc + colourPerc + kilometersPerc + statePerc;
            Double finalPercentage = 1 + percentageSum;

            double finalResult = priceFromInfoautoApi * finalPercentage;

            int minRangeValue = getRoundedPrice((finalResult - finalResult * PriceConstants.rangeVariation));
            int maxRangeValue = getRoundedPrice((finalResult + finalResult * PriceConstants.rangeVariation));

            List<Integer> prices = getRandomAgenciesPrices(
                    PriceConstants.otherDealershipsMin,
                    PriceConstants.otherDealershipsMax,
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
