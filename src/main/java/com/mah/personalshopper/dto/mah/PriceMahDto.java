package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceMahDto {

    @JsonProperty("status")
    public String status;

    @JsonProperty("msg")
    public String message;

    @JsonProperty("data")
    public Price data;
}
