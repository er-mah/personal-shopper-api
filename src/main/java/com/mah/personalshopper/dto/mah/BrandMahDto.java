package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.mah.personalshopper.dto.mah.Brand;

import java.util.List;

public class BrandMahDto {

    @JsonProperty("status")
    public String status;

    @JsonProperty("msg")
    public String message;

    @JsonProperty("data")
    public List<Brand> data;
}
