package com.mah.personalshopper.controller;

import com.mah.personalshopper.dto.*;
import com.mah.personalshopper.dto.mah.*;
import com.mah.personalshopper.service.MahService;
import com.mah.personalshopper.service.VehicleService;
import com.mah.personalshopper.util.ControllerConstants;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ControllerConstants.VEHICLE)
public class VehicleController {
    private final VehicleService vehicleService;
    private final MahService mahService;

    public VehicleController(VehicleService vehicleService, MahService mahService) {
        this.vehicleService = vehicleService;
        this.mahService = mahService;
    }

    // TODO: Protect endpoint
    @ApiOperation(
            value = "Persists a new vehicle"
    )
    @PostMapping("")
    public ResponseEntity<ResponseDto<VehicleDto>> addNewVehicle(@RequestBody VehicleDto dto) {
        ResponseDto<VehicleDto> responseDto = vehicleService.createVehicle(dto);
        if (responseDto.getStatus() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    // TODO: Protect endpoint - Associate to an account
    @PostMapping("/{vehicleId}/owner")
    public ResponseEntity<ResponseDto<VehicleDto>> addOwner(@PathVariable UUID vehicleId,
                                                            @RequestBody OwnerDto dto) {
        ResponseDto<VehicleDto> responseDto = vehicleService.addVehicleOwner(vehicleId, dto);
        if (responseDto.getStatus() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @PostMapping("/brands")
    public ResponseEntity<ResponseDto<List<Brand>>> getBrands() {
        ResponseDto<List<Brand>> responseDto = mahService.getBrands();

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }


    @PostMapping("/years")
    public ResponseEntity<ResponseDto<List<Year>>> getYears(@RequestParam Integer brandId) {

        ResponseDto<List<Year>> responseDto = mahService.getYears(brandId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @PostMapping("/models")
    public ResponseEntity<ResponseDto<List<Model>>> getModels(@RequestParam Integer brandId,
                                                              @RequestParam Integer year) {

        ResponseDto<List<Model>> responseDto = mahService.getModel(brandId, year);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @PostMapping("/versions")
    public ResponseEntity<ResponseDto<List<Version>>> getVersions(@RequestParam Integer brandId,
                                                                  @RequestParam Integer year,
                                                                  @RequestParam Integer modelId) {

        ResponseDto<List<Version>> responseDto = mahService.getVersions(brandId, year, modelId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @PostMapping("/price")
    public ResponseEntity<ResponseDto<Price>> getPrice(@RequestParam Integer year,
                                                       @RequestParam Integer versionId) {

        ResponseDto<Price> responseDto = mahService.getPrice(year, versionId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }


}
