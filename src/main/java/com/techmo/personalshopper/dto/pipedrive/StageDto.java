package com.techmo.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StageDto {

    @JsonProperty("success")
    public Boolean success;

    @JsonProperty("data")
    public Stage data;

}
