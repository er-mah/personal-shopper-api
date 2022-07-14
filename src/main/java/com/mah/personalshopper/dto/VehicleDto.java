package com.mah.personalshopper.dto;

import com.mah.personalshopper.model.enums.DeclaredState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VehicleDto {

    String id;
    Long year;
    String brand;
    String licencePlate;
    String model;
    String version;
    DeclaredState state;
    String colour;
    Double kilometers;
    Double sellingPrice;
    Integer sellingTime;
    String comments;
    OwnerDto owner;
    // String documentationUrl;
    // Owner owner;

}
