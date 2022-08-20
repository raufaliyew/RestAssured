package com.cydeo.tests.day08_hamcrest;

import com.cydeo.utils.HRTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrest extends HRTestBase {

    /**
     * given accept type is json
     * when I send get request to /countries
     * Then status code is 200
     * and content type is json
     * and count should be 25
     * and country ids should contain "AR,AU,BE,BR,CA"
     * and country names should have "Argentina,Australia,Belgium,Brazil,Canada"
     *
     * items[0].country_id ==> AR
     * items[1].country_id ==> AU
     * items.country_id ==> AR, AU, .... all of them as a list of string
     */
    @DisplayName("GET /countries")
    @Test
    public void countryTest(){
        String countryID = given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("count", is(25))
                .and().body("items.country_id", hasItems("AR", "AU", "BE", "BR", "CA"))
                .and().body("items.country_name", hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"))
                .and().body("items.country_id[0]", is(equalTo("AR")))
                .and().body("items.country_id[1]", is(equalTo("AU")))
                .and().extract().body().path("items[0].country_id");

        System.out.println("countryID = " + countryID);


        given().accept(ContentType.JSON)
                .and().pathParam("country_id",countryID)
                .when().get("/countries/{country_id}")
                .then().assertThat().statusCode(200)
                .and().body("country_name",equalTo("Argentina"));





    }
}
