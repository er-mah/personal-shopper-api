package com.techmo.personalshopper.controller;

import com.techmo.personalshopper.dto.infoAuto.CarDetailsDto;
import com.techmo.personalshopper.dto.infoAuto.CarPriceDto;
import com.techmo.personalshopper.service.InfoAutoService;
import com.techmo.personalshopper.service.TechMoService;
import com.techmo.personalshopper.service.VehicleService;
import com.techmo.personalshopper.util.ControllerConstants;

import com.techmo.personalshopper.dto.PriceInputDto;
import com.techmo.personalshopper.dto.PriceRangeDto;
import com.techmo.personalshopper.dto.ResponseDto;
import com.techmo.personalshopper.dto.techmo.Brand;
import com.techmo.personalshopper.dto.techmo.Model;
import com.techmo.personalshopper.dto.techmo.Version;
import com.techmo.personalshopper.dto.techmo.Year;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping(ControllerConstants.VEHICLE)
public class VehicleController {
    private final TechMoService techMoService;
    private final VehicleService vehicleService;
    private final InfoAutoService infoAutoService;

    public VehicleController(TechMoService techMoService, VehicleService vehicleService, InfoAutoService infoAutoService) {
        this.techMoService = techMoService;
        this.vehicleService = vehicleService;
        this.infoAutoService = infoAutoService;
    }


    @GetMapping("/brands")
    public ResponseEntity<ResponseDto<List<Brand>>> getBrands() {
        ResponseDto<List<Brand>> responseDto = techMoService.getBrands();

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/years")
    public ResponseEntity<ResponseDto<List<Year>>> getYears(@RequestParam Integer brandId) {

        ResponseDto<List<Year>> responseDto = techMoService.getYears(brandId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/models")
    public ResponseEntity<ResponseDto<List<Model>>> getModels(@RequestParam Integer brandId, @RequestParam Integer year) {

        ResponseDto<List<Model>> responseDto = techMoService.getModel(brandId, year);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/versions")
    public ResponseEntity<ResponseDto<List<Version>>> getVersions(@RequestParam Integer brandId, @RequestParam Integer year, @RequestParam Integer modelId) {

        ResponseDto<List<Version>> responseDto = techMoService.getVersions(brandId, year, modelId);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @GetMapping("/{versionId}/details")
    public ResponseEntity<ResponseDto<CarDetailsDto>> getDetailedInfo(@PathVariable("versionId") Integer versionId) throws IOException, InterruptedException {

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



    @GetMapping("/prices/{vehicleType}/{codia}/details")
    public ResponseEntity<ResponseDto<CarPriceDto[]>> getInfoAutoPrices(@PathVariable("codia") Integer codia, @PathVariable("vehicleType") String vehicleType) throws IOException, InterruptedException {
        ResponseDto<CarPriceDto[]> responseDto;
        if (Objects.equals(vehicleType, "new-vehicle")) {
            responseDto = this.infoAutoService.getModelBasePrices(false, codia);
        } else {
            responseDto = this.infoAutoService.getModelBasePrices(true, codia);
        }

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }

        if (responseDto.getStatus() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);

    }

}
