package com.mah.personalshopper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PriceRangeDto {

    public int minValue;
    public int maxValue;
    public List<Integer> agenciesPrices;
    public PriceRangeDto(int minValue, int maxValue, List<Integer> agenciesPrices) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.agenciesPrices = agenciesPrices;
    }
}
