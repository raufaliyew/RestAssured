package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        response.prettyPrint();

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        Assertions.assertEquals(200,response.statusCode());




    }

}