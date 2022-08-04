package com.cydeo.tests.day03_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanParameterTest {

    String url = "http://44.201.77.133:8000/api/spartans";

    /**
     * Given accept type is Json
     * And Id parameter value is 5
     * When user sends GET request to /api/spartans/{id}
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Blythe" should be in response payload(body)
     */

    @Test
    @DisplayName("Testing parameter")
    public void getSingleSpartan() {


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get(url + "/{id}");


        System.out.println(response.asString());
        System.out.println(response.contentType());
        System.out.println(response.getHeader("Content-type"));

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals("application/json", response.getContentType());
        assertTrue(response.asString().contains("Blythe"));


    }

    /**
     *  Given accept type is Json
     *  And Id parameter value is 500
     *  When user sends GET request to /api/spartans/{id}
     *  Then response status code should be 404
     *  And response content-type: application/json
     *  And "Not Found" message should be in response payload
     */

    @Test @DisplayName("Negative test")
    public void negativeTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(url + "/{id}");

        System.out.println(response.toString());

        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());
        assertEquals("application/json", response.contentType());

        assertTrue(response.asString().contains("Not Found"));
        assertEquals(ContentType.JSON.toString(),response.contentType());

    }
}
