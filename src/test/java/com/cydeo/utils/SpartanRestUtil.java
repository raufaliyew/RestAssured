package com.cydeo.utils;
import com.cydeo.pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanRestUtil{

    static {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.api.url");
    }

    public static void deleteSpartanById(int spartanId){

        //send DELETE request {{baseUrl}}/api/spartans/{id}
        given().pathParam("id",spartanId)
                .when().delete("/spartans/{id}")
                .then().log().all();
    }

    public static Spartan getNewSpartan(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        Long phone = Long.valueOf(faker.numerify("##########"));


        Spartan spartan = new Spartan();
        spartan.setName(name);
        spartan.setGender("Male");
        spartan.setPhone(phone);

        return spartan;
    }

    public static Map<String,Object> getSpartan(int id){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get("/spartans/{id}");

        Map <String,Object> spartanMap = response.as(Map.class);
        return spartanMap;
    }
}
