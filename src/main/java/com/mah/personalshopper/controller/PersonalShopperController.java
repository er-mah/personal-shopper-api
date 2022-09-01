package com.mah.personalshopper.controller;

import com.mah.personalshopper.dto.PersonalShopperInputDto;
import com.mah.personalshopper.dto.PriceRangeDto;
import com.mah.personalshopper.dto.ResponseDto;
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

    @PostMapping("/persist-form")
    public ResponseEntity<ResponseDto<Object>> persistFormInCRM(@RequestBody PersonalShopperInputDto dto) {

        ResponseDto<Object> responseDto = service.persistPersonalShopperData(dto);

        if (responseDto.getStatus() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

}
