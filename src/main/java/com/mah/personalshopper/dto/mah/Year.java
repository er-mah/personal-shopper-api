package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Year {

    @JsonProperty("id")
    Long id = null;

    @JsonProperty("anio")
    String year = null;
}
