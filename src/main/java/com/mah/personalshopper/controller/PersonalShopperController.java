package com.mah.personalshopper.controller;

import com.mah.personalshopper.dto.*;
import com.mah.personalshopper.dto.pipedrive.*;
import com.mah.personalshopper.service.PipedriveService;
import com.mah.personalshopper.util.ControllerConstants;
import com.mah.personalshopper.util.MiscMethods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping(ControllerConstants.PERSONAL_SHOPPER)
public class PersonalShopperController {

    private final PipedriveService service;

    public PersonalShopperController(PipedriveService service) {
        this.service = service;
    }

    @PostMapping("/persist-deal")
    public ResponseEntity<ResponseDto<Deal>> persistDealInCRM(@RequestBody PersonalShopperInputDto dto) {

        ResponseDto<Deal> responseDto = service.persistPersonalShopperData(dto);

        if (responseDto.getStatus() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }


    @PostMapping("/{dealId}/revision-dates")
    public ResponseEntity<ResponseDto<Object>> addRevisionDatesToDeal(@RequestBody RevisionDatesInputDto dto, @PathVariable Long dealId) {

        ResponseDto<Object> responseDto = service.addRevisionDatesToDeal(dto, dealId);

        if (responseDto.getStatus() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    @PostMapping("/{dealId}/sale-type")
    public ResponseEntity<ResponseDto<Object>> addSaleTypeToDeal(@RequestBody SaleTypeInputDto dto, @PathVariable Long dealId) {

        ResponseDto<Object> responseDto = service.addSelectedSaleType(dto, dealId);

        if (responseDto.getStatus() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

}
