package com.cydeo.tests.practice;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.formula.functions.Count;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;
import javax.management.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.baseURI;

public class TasksPart2 {

    @BeforeAll
    public static void setUp() {
        System.out.println("Setting up base Url ... ");
        baseURI = ConfigurationReader.getProperty("hr.api.url");
    }

    /**
     * Given accept type is Json
     * Query param value q= region_id 3
     * When users sends request to /countries
     * Then status code is 200
     * And all regions_id is 3
     * And count is 6
     * And hasMore is false
     * And Country_name are;
     Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void testCountries(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");


        assertEquals(HttpStatus.SC_OK,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        List<Object> list = jsonPath.getList("items.region_id");
        list.forEach(each -> assertEquals(3,each));
        assertEquals(6,list.size());
        assertFalse(jsonPath.getBoolean("hasMore"));

        List <String> expectedCountries = new ArrayList<>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        List <String> actualCountries = jsonPath.getList("items.country_name");

        assertEquals(expectedCountries.stream().sorted().collect(Collectors.toList()), actualCountries.stream().sorted().collect(Collectors.toList()));

    }

    /**
     * Given accept type is Json
     * Query param value - q={"department_id":80}
     * When users sends request to /employees
     * Then status code is 200
     * And Content - Type is Json
     * And all job_ids start with 'SA'
     * And all department_ids are 80
     * Count is 25*/
    @Test
    public void testJobs(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals(ContentType.JSON.toString(),response.contentType());

        JsonPath jsonPath = response.jsonPath();
        List<Object> actualJobIdList = jsonPath.getList("items.job_id");
        actualJobIdList.forEach(each ->assertTrue(each.toString().startsWith("SA")));

        List<Object> actualDepList = jsonPath.getList("items.department_id");
        actualDepList.forEach(each ->assertEquals(80,each));

        assertEquals(25, actualJobIdList.size());
    }


}
