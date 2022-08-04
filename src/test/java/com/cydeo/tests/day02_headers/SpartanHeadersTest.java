package com.cydeo.tests.day02_headers;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHeadersTest {

    String url = "http://44.201.77.133:8000/api/spartans";

    @Test
    @DisplayName("expecting JSon response")
    public void getAllSpartanHeaders() {

        when().get(url).then().assertThat().header("Content-Type", "application/json");

        when().get(url).then().assertThat().contentType(ContentType.JSON);

    }

    /**
     * • When I send a GET request to
     * • spartan_base_url/api/spartans
     * • Then Response STATUS CODE must be 200
     * • And I Should see all Spartans data in JSON format
     */
    @Test
    public void getHeader() {
        given().accept(ContentType.XML)
                .when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);


    }

    @Test
    public void readResponseHeaders(){
        Response response = given().accept(ContentType.JSON)
                .when().get(url);

        List<Header> headers = response.getHeaders().asList();

        Headers headers1 = response.getHeaders();
        System.out.println(headers1);
        System.out.println(headers1.get("Date"));
        System.out.println(headers);



    }
}
