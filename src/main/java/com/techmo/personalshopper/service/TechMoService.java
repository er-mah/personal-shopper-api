package com.techmo.personalshopper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmo.personalshopper.dto.techmo.*;
import com.techmo.personalshopper.dto.ResponseDto;
import com.techmo.personalshopper.dto.techmo.*;
import com.techmo.personalshopper.util.ControllerConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class TechMoService {

    HttpClient client;
    ObjectMapper objectMapper;

    public TechMoService() {
        this.objectMapper = new ObjectMapper();
        this.client = HttpClient.newHttpClient();
    }

    public ResponseDto<List<Brand>> getBrands() throws RuntimeException {
        try {
            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ControllerConstants.TECHMO_BASE_URI + "/brands?" +
                            "estado=usado"))
                    .GET()
                    .build();

            // Make request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BrandTechmoDto brandsToDto = objectMapper.readValue(response.body(), BrandTechmoDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", brandsToDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<List<Year>> getYears(Integer brandId) {

        try {
            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ControllerConstants.TECHMO_BASE_URI + "/years?" +
                            "estado=usado&" +
                            "marca=" + brandId))
                    .GET()
                    .build();

            // Make request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new Exception(response.body());
            }
            YearTechmoDto yearMahDto = objectMapper.readValue(response.body(), YearTechmoDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", yearMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<List<Model>> getModel(Integer brandId, Integer year) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ControllerConstants.TECHMO_BASE_URI + "/models?" +
                            "estado=usado&" +
                            "marca=" + brandId + "&" +
                            "anio=" + year))
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new Exception(response.body());
            }
            ModelTechmoDto modelMahDto = objectMapper.readValue(response.body(), ModelTechmoDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", modelMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<List<Version>> getVersions(Integer brandId, Integer year, Integer modelId) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ControllerConstants.TECHMO_BASE_URI + "/versions?" +
                            "estado=usado" + "&" +
                            "marca=" + brandId + "&" +
                            "anio=" + year + "&" +
                            "modelo=" + modelId))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new Exception(response.body());
            }
            VersionTechmoDto versionMahDto = objectMapper.readValue(response.body(), VersionTechmoDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", versionMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public Double getPrice(Integer year, Integer versionId) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ControllerConstants.TECHMO_BASE_URI + "/price?" +
                            "codia=" + versionId + "&" +
                            "estado=usado" + "&" +
                            "anio=" + year))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new Exception(response.body());
            }
            PriceTechmoDto priceMahDto = objectMapper.readValue(response.body(), PriceTechmoDto.class);

            return Double.parseDouble(priceMahDto.data.price);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public ResponseDto<Dni> getDni(Long dni, String sex) {

        try {

            HashMap<String, String> values = new HashMap<>() {{
                put("dni", dni.toString());
                put("sexo", sex);
            }};
            String payload = objectMapper.writeValueAsString(values);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ControllerConstants.TECHMO_BASE_URI + "/dni"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            DniTechmoDto dniMahDto = objectMapper.readValue(response.body(), DniTechmoDto.class);

            if (Objects.equals(dniMahDto.status, "error")) {
                throw new Exception("error");
            }

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", dniMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

}
