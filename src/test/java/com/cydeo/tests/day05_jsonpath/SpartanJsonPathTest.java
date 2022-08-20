package com.cydeo.tests.day05_jsonpath;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanJsonPathTest extends SpartanTestBase {


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

    @Test
    public void getSpartanJsonPath(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");
//
//        assertEquals(HttpStatus.SC_OK,response.statusCode());
//        assertEquals(ContentType.JSON.toString(),response.contentType());
//
//        assertEquals("13",response.jsonPath().getString("id"));
//        assertEquals("Jaimie",response.jsonPath().getString("name"));
//        assertEquals("Female",response.jsonPath().getString("gender"));
//        assertEquals("7842554879",response.jsonPath().getString("phone"));
//
//        Headers headers = response.getHeaders();
//        System.out.println(headers);

//        for (int i = 0; i < response.; i++) {
//
//        }
        Map<Object, Object> map = response.jsonPath().getMap("[0]");
        System.out.println(map);


    }

}
