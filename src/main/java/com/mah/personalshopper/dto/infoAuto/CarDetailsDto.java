package com.mah.personalshopper.dto.infoAuto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CarDetailsDto {
    public CarDetailsDto(List<SimplifiedCarAttributeDto> comfort,
                         List<SimplifiedCarAttributeDto> technicalInfo,
                         List<SimplifiedCarAttributeDto> engineAndTransmission,
                         List<SimplifiedCarAttributeDto> security) {
        this.comfort = comfort;
        this.technicalInfo = technicalInfo;
        this.engineAndTransmission = engineAndTransmission;
        this.security = security;
    }

    @JsonProperty("comfort")
    List<SimplifiedCarAttributeDto> comfort;

    @JsonProperty("technical_info")
    List<SimplifiedCarAttributeDto> technicalInfo;

    @JsonProperty("engine_and_transmission")
    List<SimplifiedCarAttributeDto> engineAndTransmission;

    @JsonProperty("security")
    List<SimplifiedCarAttributeDto> security;

}
