package com.cydeo.tests.day06_xmlpath_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPOJO extends SpartanTestBase {

    @Test
    public void spartanToPojoTest(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/spartans/{id}");

        Spartan as = response.as(Spartan.class);
        System.out.println(as);
    }

//    @Test
//    public void spartansToPojoTest(){
//
//        Response response = given().accept(ContentType.JSON)
//
//                .when().get("/spartans");
//
//        Spartan [] as = response.as(Spartan.class);
//        System.out.println(as);
//    }
}
