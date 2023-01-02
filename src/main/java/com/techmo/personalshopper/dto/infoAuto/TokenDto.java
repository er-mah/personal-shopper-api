package com.techmo.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {

    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("refresh_token")
    public String refreshToken;

}
