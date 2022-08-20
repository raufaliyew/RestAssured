package com.cydeo.tests.day12_jsonscehma_auth;


import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;

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



}
