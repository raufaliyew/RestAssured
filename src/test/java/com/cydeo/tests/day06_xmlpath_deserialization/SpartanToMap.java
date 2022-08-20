package com.cydeo.tests.day06_xmlpath_deserialization;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanToMap extends SpartanTestBase {

    /**
     Given accept type is application/json
     And Path param id = 10
     When i send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
     id > 10
     name>Lorenza
     gender >Female
     phone >3312820936
     */
    @Test
    public void mapTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/spartans/{id}");

        Map map = response.as(Map.class);
        System.out.println(map);
        assertEquals(10,map.get("id"));
        assertEquals("Lorenza",map.get("name"));
        assertEquals("Female",map.get("gender"));
        assertEquals(3312820936L,map.get("phone"));



    }
}
