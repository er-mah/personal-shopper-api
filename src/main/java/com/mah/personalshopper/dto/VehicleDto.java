package com.mah.personalshopper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mah.personalshopper.model.Brand;
import com.mah.personalshopper.model.Colour;
import com.mah.personalshopper.model.DeclaredState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VehicleDto {

    @JsonIgnore
    Long id;
    Long year;
    Brand brand;
    String licencePlate;
    String model;
    String version;
    DeclaredState state;
    Colour colour;
    Double kilometers;
    Double sellingPrice;
    Integer sellingTime;
    // String documentationUrl;
    // Owner owner;

}
