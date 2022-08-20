package com.cydeo.tests.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;
import javax.management.Query;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class TasksPart1 {


    String url = "https://jsonplaceholder.typicode.com";


    /**
     * Given accept type is Json
     * When user sends request to https://jsonplaceholder.typicode.com/posts
     * Then print response body
     * And status code is 200
     * And Content - Type is Json
     */
    @Test
    public void verifyStatusCode() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id","2")
                .when().get(url+"/posts/{id}/comments");

        System.out.println(response.asString());


        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.getContentType().contains(ContentType.JSON.toString()));

    }

    /**
     * Given accept type is Json
     * Query Param "postId" value is 333
     * When user sends request to  https://jsonplaceholder.typicode.com/comments
     * And header Content - Type is Json
     * And header "Content-Length" value is 2
     * And json body contains "[]"
     */
    @Test
    public void getPostAndVerify(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("postId",1)
                .when().get(url+"/comments");

        int contentLength  = response.getContentType().split(";").length;

        Map map = get(url + "/comments").as(Map.class);

        map.keySet().forEach(k ->{
            System.out.println(k);
        });
        System.out.println(response.statusCode());
//        assertTrue(response.getContentType().contains(ContentType.JSON.toString()));
//        assertEquals(2,contentLength);
//        assertTrue(response.asString().contains("[]"));


    }

    /**
     * Given accept type is Json
     * Query Param "postId" value is 1
     * When user sends request to  https://jsonplaceholder.typicode.com/comments
     * Then status code is 200
     * And header Content - Type is Json
     * And header "Connection" value is "keep-alive"
     * And json body contains "Lew@alysha.tv"*/
    @Test
    public void getCommentAndVerify(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("postId",1)
                .when().get(url+"/comments");


        assertTrue(response.getContentType().contains(ContentType.JSON.toString()));
        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("Lew@alysha.tv"));
        assertTrue(response.getHeader("Connection").contains("keep-alive"));

    }

}
