package com.cydeo.tests.day04_path;

import com.cydeo.utils.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiPathMethodTest extends HRTestBase {

    @Test
    public void readCountries(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");

        System.out.println("country_id="+response.path("items[0].country_id"));
        System.out.println("country_name="+response.path("items[0].country_name"));

        List<String> listOfCountires = response.path("items.country_name");
        System.out.println(listOfCountires);




    }
}
