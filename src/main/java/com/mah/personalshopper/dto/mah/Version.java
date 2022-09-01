package com.mah.personalshopper.dto.mah;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Version {

    @JsonProperty("id")
    Long codia = null;

    @JsonProperty("name")
    String version = null;
}
