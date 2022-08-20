package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtil;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostTest extends SpartanTestBase {

    /**
     * /**
     *      given accept is json and content type is json
     *      and body is:
     *      {
     *      *   "gender": "Male",
     *      *   "name": "jokerSmile",
     *      *   "phone": 6462693223
     *      * }
     *      When I send POST request to /spartans
     *      Then status code is 201
     *      And content type is json
     *      And "success" is "A Spartan is Born!"
     *      Data name, gender , phone matches my request details
     *      */

    @Test @DisplayName("POST /spartans")
    public void addNewSpartan(){
        String jsonBody = "{\"gender\": \"Male\",\"name\":\"jokerSmile\",\"phone\": 6462693221}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getString("success"),is("A Spartan is Born!"));

        assertThat(jsonPath.getString("data.name"),equalTo("jokerSmile"));


        Map<String,Object> data = jsonPath.getMap("data");
        System.out.println(data);

        int spartanID = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanID);
        SpartanRestUtil.deleteSpartanById(spartanID);

    }


    @Test
    public void addNewSpartanAsMap(){
        Map<String,Object> spartanPostMap = new HashMap<>();
        spartanPostMap.put("gender","Male");
        spartanPostMap.put("name","Joker");
        spartanPostMap.put("phone",1234567425);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanPostMap)
                .when().post("/spartans");

        JsonPath jsonPath = response.jsonPath();
        int spartanID = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanID);
        SpartanRestUtil.deleteSpartanById(spartanID);

    }

    @Test
    public void addNewSpartanAsPOJO(){

        Spartan newSpartan = new Spartan();
        newSpartan.setGender("Male");
        newSpartan.setName("Joker");
        newSpartan.setPhone(3431412321L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");

        JsonPath jsonPath = response.jsonPath();


        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        assertThat(jsonPath.getString("success"),is("A Spartan is Born!"));
        System.out.println(newSpartan.getName());
        assertThat(jsonPath.getString("data.name"),equalTo(newSpartan.getName()));

        int spartanID = jsonPath.getInt("data.id");
        System.out.println("spartanId = " + spartanID);
        SpartanRestUtil.deleteSpartanById(spartanID);

    }


}
