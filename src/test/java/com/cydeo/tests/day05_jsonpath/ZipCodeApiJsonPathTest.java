package com.cydeo.tests.day05_jsonpath;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ZipCodeApiJsonPathTest {

    /**
     Given accept type is json
     and country path param value is "us"
     and postal code path param value is 22102
     When I send get request to http://api.zippopotam.us/{country}/{postal-code}
     Then status code is 200
     Then "post code" is "22102"
     And  "country" is "United States"
     And "place name" is "Mc Lean"
     And  "state" is "Virginia"
     */
    @BeforeAll
    public static void setUp() {
        System.out.println("Setting up base Url ... ");
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @Test
    public void zipCodeJsonPathTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country", "us")
                .and().pathParam("postal-code", "22102")
                .when().get("/{country}/{postal-code}");

        JsonPath jsonPath = response.jsonPath();
        String country = jsonPath.getString("places[0].'state'");
        System.out.println(country);




    }
}
