package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class YearMahDto {

    @JsonProperty("status")
    public String status;

    @JsonProperty("msg")
    public String message;

    @JsonProperty("data")
    public List<Year> data;
}
