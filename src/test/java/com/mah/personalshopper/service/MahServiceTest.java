package com.mah.personalshopper.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MahServiceTest {

    MahService service;

    @BeforeEach
    void setUp() {
        this.service = new MahService();
    }

    @Test
    void getBrands() {

        this.service.getBrands();
    }
}