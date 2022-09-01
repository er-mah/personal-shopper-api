package com.mah.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarAttributeDto {

    @JsonProperty("category_name")
    public String category;

    @JsonProperty("description")
    public String description;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("length")
    public Integer length;

    @JsonProperty("decimals")
    public Integer decimals;

    @JsonProperty("position")
    public Integer position;

    @JsonProperty("type")
    public String type;

    @JsonProperty("value")
    public String value;

    @JsonProperty("value_description")
    public String valueDescription;

}
