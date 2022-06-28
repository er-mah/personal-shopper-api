package com.mah.personalshopper.mapper;

import com.mah.personalshopper.dto.OwnerDto;
import com.mah.personalshopper.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    @Mapping(target = "id", ignore = true) // Ignores the id clause if parsed from a json
    Owner dtoToOwner(OwnerDto dto);

    OwnerDto ownerToDto(Owner entity);
}
