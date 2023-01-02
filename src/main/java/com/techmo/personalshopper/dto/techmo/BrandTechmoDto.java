package com.techmo.personalshopper.dto.techmo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BrandTechmoDto {

    @JsonProperty("status")
    public String status;

    @JsonProperty("msg")
    public String message;

    @JsonProperty("data")
    public List<Brand> data;
}
