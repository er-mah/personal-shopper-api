package com.techmo.personalshopper.dto.techmo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dni {

    @JsonProperty("cuil")
    public Long cuil;

    @JsonProperty("nombre")
    public String name;

}
