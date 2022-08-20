package com.cydeo.tests.day11_put_request;


import com.cydeo.utils.SpartanRestUtil;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPutTest extends SpartanTestBase {

    /**
     * Given accept type is json
     * And content type is json
     * And path param id is 15
     * And json body is
     * {
     * "gender": "Male",
     * "name": "PutRequest"
     * "phone": 1234567425
     * }
     * When i send PUT request to /spartans/{id}
     * Then status code 204
     */

    @Test
    public void updateSpartanTest() {

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gender", "Male");
        requestMap.put("name", "JokerIsHere");
        requestMap.put("phone", 1234567425L);

        Response responsePut = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 1021)
                .and().body(requestMap)
                .and().put("/spartans/{id}");

        responsePut.prettyPrint();
        assertThat(responsePut.statusCode(), is(HttpStatus.SC_NO_CONTENT));

    }


    @Test
    public void partialUpdateSpartan(){

        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put("gender", "Male");
        requestMap.put("name", "JokerJoker");
//        requestMap.put("phone", 1234567425L);
        int id = 1027;
        Response responsePut = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", id)
                .and().body(requestMap)
                .and().patch("/spartans/{id}");

        assertThat(SpartanRestUtil.getSpartan(id).get("name"),is(equalTo(requestMap.get("name"))));
    }
}
