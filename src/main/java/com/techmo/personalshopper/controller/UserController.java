package com.techmo.personalshopper.controller;

import com.techmo.personalshopper.dto.ResponseDto;
import com.techmo.personalshopper.dto.techmo.Dni;
import com.techmo.personalshopper.service.TechMoService;
import com.techmo.personalshopper.util.ControllerConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping(ControllerConstants.USER)
public class UserController {
    private final TechMoService mahService;

    public UserController(TechMoService mahService) {
        this.mahService = mahService;
    }

    @GetMapping("/dni")
    public ResponseEntity<ResponseDto<Dni>> getDni(@RequestParam Long dni, @RequestParam String sex) {

        ResponseDto<Dni> responseDto = mahService.getDni(dni, sex);

        if (responseDto.getStatus() == HttpStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }


}
