package com.mah.personalshopper.dto.techmo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {

    @JsonProperty("id")
    Long id = null;

    @JsonProperty("name")
    String model = null;
}
