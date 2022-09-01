package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {

    @JsonProperty("id")
    Long id = null;

    @JsonProperty("name")
    String brand = null;
}
