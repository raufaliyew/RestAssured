package com.cydeo.tests.day12_jsonscehma_auth;


import com.cydeo.utils.SpartanRestUtil;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SingleSpartanJsonSchema extends SpartanTestBase {

    /**
     * given accept type is json
     * and path param id is 15
     * when I send GEt request to /spartans/{id}
     * then status code is 200
     * And json payload/body matches SingleSpartanSchema.json
     */

    @Test
    public void singleSpartanSchemaValidation(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonSchemas/SingleSpartanSchema.json")))
                .log().all();

    }

    @Test
    public void allSpartansSchemaValidation(){
        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonSchemas/AllSpartansSchema.json")))
                .log().all();

    }

    /**
     given accept type is json
     And query params: nameContains "e" and gender "Female"
     when I send GET request to /spartans/search
     Then status code is 200
     And json payload/body matches SearchSpartansSchema.json
     */
    @Test
    public void searchSpartansSchemaValidation(){
        given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("spartans/search")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonSchemas/SearchSpartansSchema.json")))
                .and().log().all();

    }

    /**
     given accept is json and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "TestPost1"
     "phone": 1234567425
     }
     When I send POST request to /spartans
     Then status code is 201
     And body should match SpartanPostSchema.json schema
     */
    @Test
    public void postSpartanSchemaValidation(){
        Map <String,Object> map = new HashMap<>();
        map.put("gender","Male");
        map.put("name","TestPost1");
        map.put("phone",1234567425);


        int anInt = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(map)
                .when().post("/spartans")
                .then().statusCode(201)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonSchemas/SpartanPostSchema.json")))
                .log().all()
                .and().extract().jsonPath().getInt("data.id");

        System.out.println(anInt);

        SpartanRestUtil.deleteSpartanById(anInt);

    }


}
