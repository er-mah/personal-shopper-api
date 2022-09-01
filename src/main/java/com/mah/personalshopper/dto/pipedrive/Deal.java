package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Deal {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("title")
    public String title;

}
