package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.SpartanSearch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanSearchPojo extends SpartanSearch {

    @Test
    public void test(){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");


        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");

        SpartanSearch spartanSearch = response.body().as( SpartanSearch.class);
        System.out.println(spartanSearch);



    }
}
