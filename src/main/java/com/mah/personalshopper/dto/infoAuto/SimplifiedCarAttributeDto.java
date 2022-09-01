package com.mah.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimplifiedCarAttributeDto {

    @JsonProperty("description")
    public String description;

    @JsonProperty("type")
    public String type;

    @JsonProperty("value")
    public String value;
    @JsonProperty("value_description")
    public String valueDescription;

}
