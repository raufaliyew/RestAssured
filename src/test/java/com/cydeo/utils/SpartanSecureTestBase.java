package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanSecureTestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.secure.api.url");
    }
}
