package com.mah.personalshopper.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;



public class MiscMethods {

    public static boolean checkIfJwtIsExpired(DecodedJWT jwt) {

        return jwt.getExpiresAt().before(new Date());
    }
}