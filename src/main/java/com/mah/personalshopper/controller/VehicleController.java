package com.mah.personalshopper.controller;

import com.mah.personalshopper.model.Vehicle;
import com.mah.personalshopper.service.VehicleService;
import com.mah.personalshopper.util.ControllerConstants;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.VEHICLE)
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Vehicle>> getAll() throws RuntimeException {
        return service.getAll();
    }

}
