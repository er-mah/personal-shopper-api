package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mah.personalshopper.dto.mah.Model;

import java.util.List;

public class ModelMahDto {

    @JsonProperty("status")
    public String status;

    @JsonProperty("msg")
    public String message;

    @JsonProperty("data")
    public List<Model> data;
}
