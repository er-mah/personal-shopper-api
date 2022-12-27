package com.techmo.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stage {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

}
