package com.mah.personalshopper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.dto.VehicleDto;
import com.mah.personalshopper.model.Vehicle;
import com.mah.personalshopper.service.VehicleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @MockBean
    VehicleService vehicleService;      // UNDER TEST

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Disabled
    public void AddNewVehicle_CorrectDto_CreatedVehicle() throws Exception {
        // Create mock vehicle dto
        // underTest.
        //ResponseDto<VehicleDto> request = new ResponseDto<VehicleDto>();
        // request.getData().setBrand("test toyota");

        // Vehicle vehicle = new Vehicle();
        // vehicle.setBrand(request.getData().getBrand());

        // when(vehicleService.createVehicle(any(VehicleDto.class))).thenReturn(request);
    }

}