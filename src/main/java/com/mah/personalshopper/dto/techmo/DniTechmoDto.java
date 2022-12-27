package com.mah.personalshopper.dto.techmo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DniTechmoDto {

    @JsonProperty("status")
    public String status;

    @JsonProperty("msg")
    public String message;

    @JsonProperty("data")
    public Dni data;
}
