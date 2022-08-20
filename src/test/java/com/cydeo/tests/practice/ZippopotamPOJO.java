package com.cydeo.tests.practice;

import com.cydeo.pojo.Zippopotam;
import com.cydeo.pojo.ZippopotamPlace;
import com.cydeo.pojo.ZippopotamSearch;
import com.cydeo.utils.ZippopotamTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZippopotamPOJO extends ZippopotamTestBase {

    Zippopotam listZips;
    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     * */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("post code", "22031")
                .when().get("/us/{post code}");

        assertEquals("cloudflare",response.getHeader("Server"));
        assertTrue(response.headers().toString().contains("Report-To"));

        JsonPath jsonPath = response.jsonPath();

            listZips = jsonPath.getObject("", Zippopotam.class);

        assertEquals("22031",listZips.getPostCode());
        assertEquals("United States",listZips.getCountry());
        assertEquals("Fairfax",listZips.getPlaces().get(0).getPlaceName());


    }

    /**
     * Given Accept application/json
     * And path state is va
     * And path city is farifax
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And payload should contains following information
     *     country abbreviation is US
     *     country  United States
     *     place name  Fairfax
     *     each places must contains fairfax as a value
     *     each post code must start with 22
     * */

    @Test
    public void test2(){

        Map <String,String> queries = new HashMap<>();
        queries.put("state","va");
        queries.put("place name","Fairfax");

        Response response = given().accept(ContentType.JSON)
                .and().pathParams(queries)
                .when().get("/us/{state}/{place name}");

        JsonPath jsonPath = response.jsonPath();
        ZippopotamSearch object = jsonPath.getObject( "",ZippopotamSearch.class);
        assertEquals("US",object.getCountryAbbr());
        assertEquals("Fairfax",object.getPlaceName());
        assertTrue(object.getPlaces().stream().allMatch(each -> each.getPlaceName().contains("Fairfax")));
        assertTrue(object.getPlaces().stream().allMatch(each -> each.getPostCode().startsWith("22")));

    }
}
