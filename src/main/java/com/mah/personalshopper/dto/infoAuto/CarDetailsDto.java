package com.mah.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarDetailsDto {

    @JsonProperty("brand")
    String brand;

    @JsonProperty("model")
    String model;

    @JsonProperty("photo_url")
    String photoUrl;

    @JsonProperty("comfort")
    List<SimplifiedCarAttributeDto> comfort;

    @JsonProperty("technical_info")
    List<SimplifiedCarAttributeDto> technicalInfo;

    @JsonProperty("engine_and_transmission")
    List<SimplifiedCarAttributeDto> engineAndTransmission;

    @JsonProperty("security")
    List<SimplifiedCarAttributeDto> security;

    public CarDetailsDto(String brand,
                         String model,
                         String photoUrl,
                         List<SimplifiedCarAttributeDto> comfort,
                         List<SimplifiedCarAttributeDto> technicalInfo,
                         List<SimplifiedCarAttributeDto> engineAndTransmission,
                         List<SimplifiedCarAttributeDto> security) {
        this.brand = brand;
        this.model = model;
        this.photoUrl = photoUrl;
        this.comfort = comfort;
        this.technicalInfo = technicalInfo;
        this.engineAndTransmission = engineAndTransmission;
        this.security = security;
    }
}
