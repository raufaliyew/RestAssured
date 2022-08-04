package com.cydeo.tests.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecRestApiTest {


    String url = "https://reqres.in/api/users/59";

    @Test @DisplayName("GET all users")
    public void usersGetTest(){

        Response response = RestAssured.get(url);

//        Response response = when().get(url);
        response.prettyPrint();

//        Assertions.assertEquals(200,response.statusCode());

//        response.then().statusCode(200);


    }
}
