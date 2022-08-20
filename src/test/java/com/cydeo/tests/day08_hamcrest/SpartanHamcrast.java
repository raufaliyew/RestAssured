package com.cydeo.tests.day08_hamcrest;

import com.cydeo.pojo.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrast extends SpartanTestBase {

    /**
     * given accept type is json
     * and path id is 24
     * When i send get request to /spartans/{id}
     * then status code is 200
     * and content type is application/json
     * and id" is 24,
     *     "name" is "Julio",
     *     "gender" is "Male",
     *     "phone" is 9393139934
     * */
    @Test @DisplayName("GET /spartans/{id}")
    public void hamcrastTest(){

        given().accept(ContentType.JSON)
                .when().get("/spartans/{id}",24)
                .then().statusCode(is(200))
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("id",equalTo(24),"name",equalTo("Julio"));


    }

    /**
     * /**
     *      Given accept type is json
     *      And query param nameContains value is "e"
     *      And query param gender value is "Female"
     *      When I send get request to /spartans/search
     *      Then status code is 200
     *      And content type is Json
     *      And json response data is as expected
     *      */

    @Test @DisplayName("GET /spartans/search")
    public void hamcrastTest1(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "e", "gender", "Female")
                .when().get("/spartans/search");

        JsonPath jsonPath = response.jsonPath();
        SpartanSearch spartanSearch = jsonPath.getObject("", SpartanSearch.class);

        assertThat(response.getHeader("Date"),containsString(LocalDate.now().getYear()+""));
        assertThat(response.path("content.gender"),everyItem(is("Female")));
        assertThat(jsonPath.getString("content.name"),containsString("Janette"));



    }
}
