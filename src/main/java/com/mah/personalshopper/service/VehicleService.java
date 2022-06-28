package com.mah.personalshopper.service;

import com.mah.personalshopper.mapper.VehicleMapper;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.dto.VehicleDto;
import com.mah.personalshopper.model.Vehicle;
import com.mah.personalshopper.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository repository;
    private final VehicleMapper mapper;

    public VehicleService(VehicleRepository actorRepository, VehicleMapper mapper) {
        this.repository = actorRepository;
        this.mapper = mapper;
    }

    public ResponseDto<VehicleDto> create(VehicleDto dto) {
        try {
            Vehicle fromDto = mapper.dtoToVehicle(dto);
            VehicleDto vehicleToDto = mapper.vehicleToDto(repository.save(fromDto));

            return new ResponseDto<>(HttpStatus.CREATED, "", vehicleToDto);
        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }
}
