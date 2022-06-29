package com.mah.personalshopper.dto;

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

    String id;
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
    String comments;
    OwnerDto owner;
    // String documentationUrl;
    // Owner owner;

}
