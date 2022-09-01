package com.mah.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("person_id")
    public Long personId;

    @JsonProperty("deal_id")
    public Long dealId;

    @JsonProperty("content")
    public String content;

}
