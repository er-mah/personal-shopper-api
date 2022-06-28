package com.mah.personalshopper.mapper;

import com.mah.personalshopper.dto.VehicleDto;
import com.mah.personalshopper.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target = "id", ignore = true)
    Vehicle dtoToVehicle(VehicleDto dto);      // Ignores the id clause if parsed from a json

    VehicleDto vehicleToDto(Vehicle entity);
}
