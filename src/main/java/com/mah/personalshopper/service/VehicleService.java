package com.mah.personalshopper.service;

import com.mah.personalshopper.dto.PriceInputDto;
import com.mah.personalshopper.dto.PriceRangeDto;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.model.constants.PriceConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    MahService mahService;

    public VehicleService(MahService mahService) {
        this.mahService = mahService;
    }

    public ResponseDto<PriceRangeDto> getPriceRange(PriceInputDto dto) throws RuntimeException {

        Double priceFromApi = mahService.getPrice(dto.year, dto.versionId);

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

        double finalResult = priceFromApi * finalPercentage;

        Double minRangeValue = finalResult - finalResult * PriceConstants.rangeVariation;
        Double maxRangeValue = finalResult + finalResult * PriceConstants.rangeVariation;

        PriceRangeDto rangeDto = new PriceRangeDto(minRangeValue, maxRangeValue);

        return new ResponseDto<>(HttpStatus.ACCEPTED, "", rangeDto);

    }
}
