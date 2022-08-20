package com.cydeo.tests.day10_db_vs_api_put_delete;

import com.cydeo.utils.DB_Utils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class SpartanAPIandDBValidation extends SpartanTestBase {


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
     And content type is json
     And "success" is "A Spartan is Born!"
     When I send database query
     SELECT name, gender, phone
     FROM spartans
     WHERE spartan_id = newIdFrom Post request;
     Then name, gender , phone values must match with POST request details
     */
    @DisplayName("POST /api/spartan -> then validate in DB")
    @Test
    public void postThenDBTest(){

        Map<String,Object> postRequestMap = new HashMap<>();
        postRequestMap.put("gender","Male");
        postRequestMap.put("name","JokerIsBack");
        postRequestMap.put("phone",1234567425);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                .when().post("/spartans");

        assertThat(response.statusCode(),is(equalTo(201)));
        assertThat(response.jsonPath().getString("success"),equalTo("A Spartan is Born!"));

        int newSpartanID = response.jsonPath().getInt("data.id");

        String query = "select name,gender,phone from spartans where spartans_id="+newSpartanID;

        try {
            DB_Utils.createConnection();
        }catch (Exception e){
            DB_Utils.destroy();
            e.printStackTrace();
        }

        Map<String, Object> dbMap = DB_Utils.getRowMap(query);

        assertThat(dbMap.get("name"),is(equalTo(postRequestMap.get("name"))));


        DB_Utils.destroy();


    }
}
