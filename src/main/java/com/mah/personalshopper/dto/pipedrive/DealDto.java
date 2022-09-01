package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DealDto {

    @JsonProperty("success")
    public Boolean success;

    @JsonProperty("data")
    public Deal data;

}
