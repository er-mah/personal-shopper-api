package com.mah.personalshopper.controller;

import com.mah.personalshopper.dto.*;
import com.mah.personalshopper.dto.infoAuto.CarDetailsDto;
import com.mah.personalshopper.dto.mah.*;
import com.mah.personalshopper.service.InfoAutoService;
import com.mah.personalshopper.service.MahService;
import com.mah.personalshopper.service.VehicleService;
import com.mah.personalshopper.util.ControllerConstants;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping(ControllerConstants.VEHICLE)
public class VehicleController {
    private final MahService mahService;
    private final VehicleService vehicleService;
    private final InfoAutoService infoAutoService;

    public VehicleController(MahService mahService, VehicleService vehicleService, InfoAutoService infoAutoService) {
        this.mahService = mahService;
        this.vehicleService = vehicleService;
        this.infoAutoService = infoAutoService;
    }


    @GetMapping("/brands")
    public ResponseEntity<ResponseDto<List<Brand>>> getBrands() {
        ResponseDto<List<Brand>> responseDto = mahService.getBrands();

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/years")
    public ResponseEntity<ResponseDto<List<Year>>> getYears(@RequestParam Integer brandId) {

        ResponseDto<List<Year>> responseDto = mahService.getYears(brandId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/models")
    public ResponseEntity<ResponseDto<List<Model>>> getModels(@RequestParam Integer brandId, @RequestParam Integer year) {

        ResponseDto<List<Model>> responseDto = mahService.getModel(brandId, year);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/versions")
    public ResponseEntity<ResponseDto<List<Version>>> getVersions(@RequestParam Integer brandId, @RequestParam Integer year, @RequestParam Integer modelId) {

        ResponseDto<List<Version>> responseDto = mahService.getVersions(brandId, year, modelId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/{versionId}/details")
    public ResponseEntity<ResponseDto<CarDetailsDto>> getDetailedInfo(@PathVariable("versionId") Integer versionId) {

        ResponseDto<CarDetailsDto> responseDto = this.infoAutoService.getCarDetails(versionId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);

    }

    @PostMapping("/price")
    public ResponseEntity<ResponseDto<PriceRangeDto>> getPrice(@RequestBody PriceInputDto dto) {
        ResponseDto<PriceRangeDto> responseDto = vehicleService.getPriceRange(dto);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

}
