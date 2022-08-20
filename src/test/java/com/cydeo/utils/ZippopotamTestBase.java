package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class ZippopotamTestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }
}
