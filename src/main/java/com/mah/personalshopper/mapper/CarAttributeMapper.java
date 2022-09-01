package com.mah.personalshopper.mapper;

import com.mah.personalshopper.dto.infoAuto.CarAttributeDto;
import com.mah.personalshopper.dto.infoAuto.SimplifiedCarAttributeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarAttributeMapper {

    CarAttributeMapper INSTANCE = Mappers.getMapper(CarAttributeMapper.class);

    SimplifiedCarAttributeDto simpleToAttr(CarAttributeDto detailedAttr);


}
