package com.mah.personalshopper.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ControllerConstants {
    @Value("${miautohoy.urls.base}")
    public static String MAH_BASE_URI;
    @Value("${info-auto.urls.base}")
    public static String INFOAUTO_BASE_URI;
    @Value("${info-auto.urls.auth}")
    public static String INFOAUTO_AUTH_URI;
    @Value("${pipedrive.urls.base}")
    public static String PIPEDRIVE_BASE_URI;

    @Value("${pipedrive.custom-field-hashes.dni}")
    public static String PIPEDRIVE_DNI_HASH;
    @Value("${pipedrive.custom-field-hashes.brand}")
    public static String PIPEDRIVE_VEHICLE_BRAND_HASH;
    @Value("${pipedrive.custom-field-hashes.year}")
    public static String PIPEDRIVE_VEHICLE_YEAR_HASH;
    @Value("${pipedrive.custom-field-hashes.model}")
    public static String PIPEDRIVE_VEHICLE_MODEL_HASH;
    @Value("${pipedrive.custom-field-hashes.version}")
    public static String PIPEDRIVE_VEHICLE_VERSION_HASH;
    @Value("${pipedrive.custom-field-hashes.input-channel}")
    public static String PIPEDRIVE_INPUT_CHANNEL;       // Canal de ingreso de lead

    public static final String VEHICLE = "/vehicle";
    public static final String PERSONAL_SHOPPER = "/personal-shopper";
    public static final String USER = "/user";


    @Autowired
    public void GetProperties(
            @Value("${miautohoy.urls.base}") String mahBase,
            @Value("${info-auto.urls.base}") String infoAutoBase,
            @Value("${info-auto.urls.auth}") String infoAutoAuth,
            @Value("${pipedrive.urls.base}") String pipedriveBase,
            @Value("${pipedrive.custom-field-hashes.dni}") String dniHash,
            @Value("${pipedrive.custom-field-hashes.brand}") String brandHash,
            @Value("${pipedrive.custom-field-hashes.year}") String yearHash,
            @Value("${pipedrive.custom-field-hashes.model}") String modelHash,
            @Value("${pipedrive.custom-field-hashes.version}") String versionHash,
            @Value("${pipedrive.custom-field-hashes.input-channel}") String inputChannel
    ) {
        MAH_BASE_URI = mahBase;
        INFOAUTO_BASE_URI = infoAutoBase;
        INFOAUTO_AUTH_URI = infoAutoAuth;
        PIPEDRIVE_BASE_URI = pipedriveBase;
        PIPEDRIVE_DNI_HASH = dniHash;
        PIPEDRIVE_VEHICLE_BRAND_HASH = brandHash;
        PIPEDRIVE_VEHICLE_YEAR_HASH = yearHash;
        PIPEDRIVE_VEHICLE_MODEL_HASH = modelHash;
        PIPEDRIVE_VEHICLE_VERSION_HASH = versionHash;
        PIPEDRIVE_INPUT_CHANNEL = inputChannel;
    }


}
