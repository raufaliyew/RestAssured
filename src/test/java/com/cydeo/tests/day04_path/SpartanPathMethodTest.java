package com.cydeo.tests.day04_path;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPathMethodTest extends SpartanTestBase {

    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */
    @Test @DisplayName("GET /spartan/{id} and path()")
    public void readSpartanJsonUsingPath(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/spartans/{id}");


        System.out.println("id="+response.path("id"));
        System.out.println("name="+response.path("name"));
        System.out.println("gender="+response.path("gender"));
        System.out.println("phone="+response.path("phone"));

//        another way asserting value with hamcrest -> Matchers
//        given().accept(ContentType.JSON)
//                .and().pathParam("id", 13)
//                .when().get("/spartans/{id}")
//                .then().assertThat().body("id", Matchers.is(13));
    }



    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */
    @Test
    public void readSpartanJsonArrayUsingPathTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

        int actualStatusCode = response.statusCode();
        String actualContentType = response.contentType().toString();

        System.out.println(response.path("[0].id").toString());
        System.out.println(response.path("id[-1]").toString());

//       to get all id's
       List<Integer> listOfIds = response.path("id");


    }











}
