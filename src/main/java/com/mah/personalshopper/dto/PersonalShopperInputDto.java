package com.mah.personalshopper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PersonalShopperInputDto {

    @NotNull
    public Integer vehicleYear;

    @NotNull
    public String vehicleBrand;

    @NotNull
    public String vehicleModel;

    @NotNull
    public String vehicleVersion;

    @NotNull
    public String vehicleColor;

    @NotNull
    public String vehicleLicensePlate;

    @NotNull
    public String vehicleKilometers;

    @NotNull
    public String vehicleState;

    @NotNull
    public String vehicleComments;

    @NotNull
    public String saleType;

    @NotNull
    public String saleCurrency;

    @NotNull
    public String saleRequestedAmount;

    @NotNull
    public String saleUrgency;

    @NotNull
    public String saleBaseQuotationValue;

    @NotNull
    public String saleQuotationRange;

    @NotNull
    public List<String> saleMechanicalRevisionDates;

    @NotNull
    public String ownerSex;

    @NotNull
    public String ownerName;

    @NotNull
    public String ownerDni;

    @NotNull
    public String ownerCuil;

    @NotNull
    public String ownerEmail;

    @NotNull
    public String ownerPhone;

    @NotNull
    public String ownerPostalCode;

}
