package com.cydeo.tests.day12_jsonscehma_auth;

import com.cydeo.utils.SpartanSecureTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanBasicAuthTest extends SpartanSecureTestBase {

    /**
     * given accept type is json
     * and basic auth credentials are admin, admin
     * when user sends get request to /spartans
     * then status code is 200
     * and content type is json
     * and print response
     */
    @Test
    public void getSpartansWithAuth(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/spartans")
                .then().statusCode(200)
                .log().all();

        
    }


}
