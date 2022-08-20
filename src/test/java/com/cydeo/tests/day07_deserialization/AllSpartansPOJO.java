package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.*;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllSpartansPOJO extends SpartanTestBase {

    @Test
    public void allSpartansPojoTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

        JsonPath jsonPath = response.jsonPath();

        List<Spartan> list = jsonPath.getList("",Spartan.class);
        System.out.println(list.get(0));

        List<Spartan> females = list.stream().filter(each -> each.getGender().equals("Female")).collect(Collectors.toList());
        System.out.println(females);

    }
}
