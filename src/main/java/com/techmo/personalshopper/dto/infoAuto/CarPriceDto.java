package com.techmo.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarPriceDto {

    @JsonProperty("price")
    public Integer price;


    @JsonProperty("list_price")
    public Integer list_price;


    @JsonProperty("year")
    public Integer year;

}
