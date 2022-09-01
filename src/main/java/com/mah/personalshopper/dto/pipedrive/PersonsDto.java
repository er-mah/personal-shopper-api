package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonsDto {

    @JsonProperty("success")
    public Boolean success;

    @JsonProperty("data")
    public PersonItems data;

}
