package com.mah.personalshopper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.dto.infoAuto.CarAttributeDto;
import com.mah.personalshopper.dto.infoAuto.CarDetailsDto;
import com.mah.personalshopper.dto.infoAuto.SimplifiedCarAttributeDto;
import com.mah.personalshopper.dto.infoAuto.TokenDto;
import com.mah.personalshopper.mapper.CarAttributeMapper;
import com.mah.personalshopper.util.MiscMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import com.auth0.jwt.JWT;

import static com.mah.personalshopper.util.ControllerConstants.*;

@Service
public class InfoAutoService {

    @Value("${info-auto.username}")
    String username;
    @Value("${info-auto.password}")
    String password;

    ObjectMapper objectMapper;
    CarAttributeMapper carAttributeMapper;
    HttpClient client;
    TokenDto tokens;


    public InfoAutoService(CarAttributeMapper carAttributeMapper) {
        this.carAttributeMapper = carAttributeMapper;
        this.objectMapper = new ObjectMapper();
        this.client = HttpClient.newHttpClient();
        this.tokens = null;

    }


    @Autowired
    public void GetProperties(@Value("${info-auto.username}") String username,
                                  @Value("${info-auto.password}") String password) {
        this.username = username;
        this.password = password;
    }

    // Set auth header
    private String getBasicAuthenticationHeader() {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    private TokenDto getTokens() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(INFOAUTO_AUTH_URI + "/login"))
                .header("Authorization", getBasicAuthenticationHeader())
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), TokenDto.class);
    }


    private String getNewAccessToken() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(INFOAUTO_AUTH_URI  + "/refresh"))
                .header("Authorization", "Bearer "+ this.tokens.refreshToken)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        TokenDto tokensFromDto = objectMapper.readValue(response.body(), TokenDto.class);
        return tokensFromDto.accessToken;
    }



    // This is going to be used
    public void setInfoAutoTokens() throws RuntimeException, IOException, InterruptedException {

        if (this.tokens != null) {
            if (MiscMethods.checkIfJwtIsExpired(JWT.decode(this.tokens.accessToken)) &&
                    !MiscMethods.checkIfJwtIsExpired(JWT.decode(this.tokens.refreshToken))) {
                // If refresh token is not expired
                this.tokens.accessToken = getNewAccessToken();
            }

        } else {
            // Store both tokens in service's attributes
            this.tokens = getTokens();
        }

    }

    public ResponseDto<CarDetailsDto> getCarDetails(Integer codia) throws RuntimeException {

        try {
            this.setInfoAutoTokens();

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(INFOAUTO_BASE_URI + "/models/" + codia + "/features/"))
                    .header("Authorization", "Bearer "+ this.tokens.accessToken)
                    .GET()
                    .build();

            // Make request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                return new ResponseDto<>(HttpStatus.NOT_FOUND, "", null);
            }
            CarAttributeDto[] detailedCarInfo = objectMapper.readValue(response.body(), CarAttributeDto[].class);

            List<SimplifiedCarAttributeDto> comfort = new ArrayList<>();
            List<SimplifiedCarAttributeDto> technicalInfo = new ArrayList<>();
            List<SimplifiedCarAttributeDto> engineAndTransmission = new ArrayList<>();
            List<SimplifiedCarAttributeDto> security = new ArrayList<>();


            for (CarAttributeDto feature: detailedCarInfo) {
                SimplifiedCarAttributeDto carAttr = carAttributeMapper.simpleToAttr(feature);

                if (feature.category.equals("Confort")) {
                    comfort.add(carAttr);
                }
                if (feature.category.equals("Datos técnicos")) {
                    technicalInfo.add(carAttr);
                }
                if (feature.category.equals("Motor y transmisión")) {
                    engineAndTransmission.add(carAttr);
                }
                if (feature.category.equals("Seguridad")) {
                    security.add(carAttr);
                }
            }

            CarDetailsDto detailsDto = new CarDetailsDto(comfort, technicalInfo, engineAndTransmission, security);

            return new ResponseDto<>(HttpStatus.ACCEPTED, "", detailsDto);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

}
