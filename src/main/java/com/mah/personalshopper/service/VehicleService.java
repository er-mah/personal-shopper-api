package com.mah.personalshopper.service;

import com.mah.personalshopper.model.Vehicle;
import com.mah.personalshopper.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository actorRepository) {
        this.repository = actorRepository;
    }

    public ResponseEntity<List<Vehicle>> getAll() throws RuntimeException {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
