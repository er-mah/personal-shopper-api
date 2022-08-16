package com.mah.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelDto {

    @JsonProperty("brand")
    public Brand brand;

    @JsonProperty("description")
    public String description;

    @JsonProperty("photo_url")
    public String url;

}
