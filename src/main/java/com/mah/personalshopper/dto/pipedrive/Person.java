package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Person {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("phones")
    public List<String> phones;

    @JsonProperty("emails")
    public List<String> emails;

    @JsonProperty("custom_fields")
    public List<String> customFields;

}
