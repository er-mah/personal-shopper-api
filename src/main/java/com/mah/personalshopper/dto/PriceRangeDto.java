package com.mah.personalshopper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PriceRangeDto {

    public Double minValue;
    public Double maxValue;

    public PriceRangeDto(Double minValue, Double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
