package com.cydeo.tests.day03_parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanQueryParamTest {

    String url = "http://44.201.77.133:8000/api/spartans";

    /**
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test @DisplayName("Getting name")
    public void searchForSpartan(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get(url);

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.getContentType());

        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));



    }

    @DisplayName("GET /api/spartans/search for 'Females' with 'e' in name")
    @Test
    public void queryParamsTest() {
        String url = "http://3.93.242.50:8000/api/spartans/search?";

        String genderParamFemale = "Female";
        String nameContainsParam = "e";

        RestAssured.baseURI = "http://3.93.242.50:8000";
        RequestSpecification request = RestAssured.given().accept(ContentType.JSON);

        Response response = request
                .queryParam("nameContains", nameContainsParam)
                .and().queryParam("gender", genderParamFemale)
                .get("/api/spartans/search");

        String jsonString = response.asString();

        response.then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON);
        Assertions.assertTrue(jsonString.contains(genderParamFemale));
        Assertions.assertTrue(jsonString.contains("Janette"));
    }




}


