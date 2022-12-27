package com.mah.personalshopper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mah.personalshopper.dto.mah.*;
import com.mah.personalshopper.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

import static com.mah.personalshopper.util.ControllerConstants.MAH_BASE_URI;

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

            // Define payload
            HashMap<String, String> values = new HashMap<>() {{
                put("estado", "usados");
            }};
            String payload = objectMapper.writeValueAsString(values);

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MAH_BASE_URI + "/brands"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            // Make request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BrandMahDto brandsToDto = objectMapper.readValue(response.body(), BrandMahDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", brandsToDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<List<Year>> getYears(Integer brandId) {

        try {

            HashMap<String, String> values = new HashMap<>() {{
                put("estado", "usados");
                put("marca", brandId.toString());
            }};
            String payload = objectMapper.writeValueAsString(values);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MAH_BASE_URI + "/years"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            YearMahDto yearMahDto = objectMapper.readValue(response.body(), YearMahDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", yearMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<List<Model>> getModel(Integer brandId, Integer year) {

        try {
            HashMap<String, String> values = new HashMap<>() {{
                put("estado", "usados");
                put("marca", brandId.toString());
                put("anio", year.toString());
            }};
            String payload = objectMapper.writeValueAsString(values);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MAH_BASE_URI + "/models"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ModelMahDto modelMahDto = objectMapper.readValue(response.body(), ModelMahDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", modelMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public ResponseDto<List<Version>> getVersions(Integer brandId, Integer year, Integer modelId) {

        try {
            HashMap<String, String> values = new HashMap<>() {{
                put("estado", "usados");
                put("marca", brandId.toString());
                put("anio", year.toString());
                put("modelo", modelId.toString());
            }};
            String payload = objectMapper.writeValueAsString(values);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MAH_BASE_URI + "/versions"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            VersionMahDto versionMahDto = objectMapper.readValue(response.body(), VersionMahDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", versionMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

    public Double getPrice(Integer year, Integer versionId) {

        try {
            HashMap<String, String> values = new HashMap<>() {{
                put("estado", "usado");
                put("anio", year.toString());
                put("codia", versionId.toString());
            }};
            String payload = objectMapper.writeValueAsString(values);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MAH_BASE_URI + "/price"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            PriceMahDto priceMahDto = objectMapper.readValue(response.body(), PriceMahDto.class);

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
                    .uri(URI.create(MAH_BASE_URI + "/dni"))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            DniMahDto dniMahDto = objectMapper.readValue(response.body(), DniMahDto.class);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", dniMahDto.data);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }
}
