package com.techmo.personalshopper.util;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class MiscMethods {

    public static boolean checkIfJwtIsExpired(DecodedJWT jwt) {
        return jwt.getExpiresAt().before(new Date()); // if true, it is expired
    }

    public static String capitalizeFirstLetterOfEachWord(String message) {

        return Arrays.stream(message.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }


}