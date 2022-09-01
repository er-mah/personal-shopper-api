package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonItems {

    @JsonProperty("items")
    public List<PersonItem> items;


}
