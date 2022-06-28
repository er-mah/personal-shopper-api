package com.mah.personalshopper.service;

import com.mah.personalshopper.dto.OwnerDto;
import com.mah.personalshopper.mapper.OwnerMapper;
import com.mah.personalshopper.mapper.VehicleMapper;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.dto.VehicleDto;
import com.mah.personalshopper.model.Owner;
import com.mah.personalshopper.model.Vehicle;
import com.mah.personalshopper.repository.OwnerRepository;
import com.mah.personalshopper.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;
    private final VehicleMapper vehicleMapper;
    private final OwnerMapper ownerMapper;

    public VehicleService(VehicleRepository actorRepository, OwnerRepository ownerRepository, VehicleMapper vehicleMapper, OwnerMapper ownerMapper) {
        this.vehicleRepository = actorRepository;
        this.ownerRepository = ownerRepository;
        this.vehicleMapper = vehicleMapper;
        this.ownerMapper = ownerMapper;
    }

    public ResponseDto<VehicleDto> createVehicle(VehicleDto dto) throws RuntimeException {
        try {
            Vehicle fromDto = vehicleMapper.dtoToVehicle(dto);
            VehicleDto vehicleToDto = vehicleMapper.vehicleToDto(vehicleRepository.save(fromDto));

            return new ResponseDto<>(HttpStatus.CREATED, "", vehicleToDto);
        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<VehicleDto> addVehicleOwner(Long vehicleId, OwnerDto dto) throws RuntimeException {
        try {
            Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

            if (vehicle.isPresent()) {

                Owner fromDto = ownerMapper.dtoToOwner(dto);
                Owner owner = ownerRepository.save(fromDto);
                vehicle.get().setOwner(owner);

                VehicleDto vehicleToDto = vehicleMapper.vehicleToDto(vehicle.get());

                return new ResponseDto<>(HttpStatus.CREATED, "", vehicleToDto);
            }

            return new ResponseDto<>(HttpStatus.NOT_FOUND, "Vehicle not found", null);
        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }
}
