package com.mah.personalshopper.dto.techmo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {

    @JsonProperty("id")
    Long id = null;

    @JsonProperty("name")
    String brand = null;
}
