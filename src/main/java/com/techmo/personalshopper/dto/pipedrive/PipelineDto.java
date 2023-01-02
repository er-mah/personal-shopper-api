package com.techmo.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PipelineDto {

    @JsonProperty("success")
    public Boolean success;

    @JsonProperty("data")
    public Pipeline data;

}
