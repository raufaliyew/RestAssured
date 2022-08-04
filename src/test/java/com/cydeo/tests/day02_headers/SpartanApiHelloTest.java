package com.cydeo.tests.day02_headers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiHelloTest {

    String url = "http://3.93.242.50:8000/api/hello";

    @Test @DisplayName("API testing")
    public void testAPI(){
        Response response = when().get(url);
        response.prettyPrint();
        System.out.println(response.contentType());

        response.then().statusCode(200);


        assertEquals(200,response.statusCode());
        assertEquals("Hello from Sparta",response.body().asString());

        when().get(url).then().assertThat().statusCode(200).and().contentType("text/plain;charset=UTF-8");



    }
}
