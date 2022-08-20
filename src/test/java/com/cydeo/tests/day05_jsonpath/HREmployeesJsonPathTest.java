package com.cydeo.tests.day05_jsonpath;

import com.cydeo.utils.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class HREmployeesJsonPathTest extends HRTestBase {


    @Test
    public void jsonPathTest(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", 200)
                .when().get("/employees");

        Map<Object, Object> map = response.jsonPath().getMap("items[0]");
        System.out.println(map);

        List<String> list = response.jsonPath().getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println(list);


        Integer maxSalary = response.jsonPath().getInt("items.max{it.salary}.salary");
        System.out.println(maxSalary);


        Integer minSalary = response.jsonPath().getInt("items.min{it.salary}.salary");
        System.out.println(minSalary);
    }


}
