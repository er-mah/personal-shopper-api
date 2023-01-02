package com.techmo.personalshopper.dto.pipedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteDto {

    @JsonProperty("success")
    public Boolean success;

    @JsonProperty("data")
    public Note data;

}
