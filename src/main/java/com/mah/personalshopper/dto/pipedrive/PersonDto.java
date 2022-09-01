package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDto {

    @JsonProperty("success")
    public Boolean success;

    @JsonProperty("data")
    public Person data;

}
