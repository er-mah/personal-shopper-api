package com.mah.personalshopper.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mah.personalshopper.dto.ResponseDto;
import com.mah.personalshopper.dto.infoAuto.*;
import com.mah.personalshopper.mapper.CarAttributeMapper;
import com.mah.personalshopper.util.MiscMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.*;

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
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.client = HttpClient.newHttpClient();
        this.tokens = null;

    }

    @Autowired
    public void GetProperties(@Value("${info-auto.username}") String username, @Value("${info-auto.password}") String password) {
        this.username = username;
        this.password = password;
    }

    // Set auth header
    private String getBasicAuthenticationHeader() {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    private TokenDto getNewTokens() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(INFOAUTO_AUTH_URI + "/login")).header("Authorization", getBasicAuthenticationHeader()).POST(HttpRequest.BodyPublishers.noBody()).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), TokenDto.class);
    }


    private String getNewAccessToken() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(INFOAUTO_AUTH_URI + "/refresh")).header("Authorization", "Bearer " + this.tokens.refreshToken).POST(HttpRequest.BodyPublishers.noBody()).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        TokenDto tokensFromDto = objectMapper.readValue(response.body(), TokenDto.class);
        return tokensFromDto.accessToken;
    }


    // This is going to be used
    public void setInfoAutoTokens() throws RuntimeException, IOException, InterruptedException {

        if (this.tokens != null) {

            // validate that access token is expired and refresh token is not expired
            if (
                    MiscMethods.checkIfJwtIsExpired(JWT.decode(this.tokens.accessToken)) &&
                            !MiscMethods.checkIfJwtIsExpired(JWT.decode(this.tokens.refreshToken))
            ) {
                // get new access token w/ refresh token
                this.tokens.accessToken = getNewAccessToken();
                return;
            }
        }

        // Get new tokens and store them in the service attributes
        this.tokens = getNewTokens();

    }

    public ResponseDto<CarDetailsDto> getCarDetails(Integer codia) throws RuntimeException, IOException, InterruptedException {

        try {
            setInfoAutoTokens();

            // Create request object
            HttpRequest featuresRequests = HttpRequest.newBuilder().uri(URI.create(INFOAUTO_BASE_URI + "/models/" + codia + "/features/")).header("Authorization", "Bearer " + this.tokens.accessToken).GET().build();

            // Make request
            HttpResponse<String> featuresResponse = client.send(featuresRequests, HttpResponse.BodyHandlers.ofString());

            if (featuresResponse.statusCode() == 404 || featuresResponse.statusCode() == 500) {
                return new ResponseDto<>(HttpStatus.NOT_FOUND, "", null);
            }
            CarAttributeDto[] detailedCarInfo = objectMapper.readValue(featuresResponse.body(), CarAttributeDto[].class);

            List<SimplifiedCarAttributeDto> comfort = new ArrayList<>();
            List<SimplifiedCarAttributeDto> technicalInfo = new ArrayList<>();
            List<SimplifiedCarAttributeDto> engineAndTransmission = new ArrayList<>();
            List<SimplifiedCarAttributeDto> security = new ArrayList<>();


            for (CarAttributeDto feature : detailedCarInfo) {
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

            // Create request object
            HttpRequest detailsRequest = HttpRequest.newBuilder().uri(URI.create(INFOAUTO_BASE_URI + "/models/" + codia)).header("Authorization", "Bearer " + this.tokens.accessToken).GET().build();

            // Make request
            HttpResponse<String> response = client.send(detailsRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new Exception(response.body());
            }
            ModelDto modelInfo = objectMapper.readValue(response.body(), ModelDto.class);

            CarDetailsDto detailsDto = new CarDetailsDto(modelInfo.brand.name, modelInfo.description, modelInfo.url, comfort, technicalInfo, engineAndTransmission, security);
            return new ResponseDto<>(HttpStatus.ACCEPTED, "", detailsDto);

        } catch (Exception e) {
            this.tokens = getNewTokens();
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }

    }

}
