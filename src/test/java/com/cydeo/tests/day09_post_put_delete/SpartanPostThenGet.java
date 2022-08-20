package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.DB_Utils;
import com.cydeo.utils.SpartanRestUtil;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanPostThenGet extends SpartanTestBase {

    Spartan newSpartan = SpartanRestUtil.getNewSpartan();

    @Test
    public void postNewSpartanThenGet() {

        Response responsePost = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");

        assertThat(responsePost.statusCode(),is(201));



        int newSpartanID = responsePost.jsonPath().getInt("data.id");

        Response responseGet = given().accept(ContentType.JSON)
                .and().pathParam("id", newSpartanID)
                .when().get("/spartans/{id}");

        responseGet.prettyPrint();

        Spartan spartanFromGet = responseGet.as(Spartan.class);


        assertThat(newSpartan.getName(),is(equalTo(spartanFromGet.getName())));

    }

    @Test
    public void newSpartan(){

        Spartan newSpartan = new Spartan();
        newSpartan.setPhone(3333367425L);
        newSpartan.setName("Mert");
        newSpartan.setGender("Male");

        Response responsePost = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newSpartan)
                .when().post("/spartans");

        responsePost.prettyPrint();

        assertThat(responsePost.statusCode(),is(201));
        assertThat(responsePost.contentType(),is(equalTo("application/json")));
        assertThat(responsePost.jsonPath().getString("success"),is(equalTo("A Spartan is Born!")));
        int spartanID = responsePost.jsonPath().getInt("data.id");


        //Validating data with API by GET
        Response responseGet = given().accept(ContentType.JSON)
                .and().pathParam("id", spartanID)
                .when().get("/spartans/{id}");



        assertThat(responseGet.jsonPath().getString("name"),is(equalTo(newSpartan.getName())));


        //Validating data with DB
        DB_Utils.createConnection();
        String query = "select * from spartans where spartan_id="+spartanID;

        Map<String, Object> rowMap = DB_Utils.getRowMap(query);

        System.out.println(rowMap);
        assertThat(newSpartan.getName(),equalTo(rowMap.get("NAME")));
        assertThat(newSpartan.getGender(),equalTo(rowMap.get("GENDER")));

        DB_Utils.destroy();
        SpartanRestUtil.deleteSpartanById(spartanID);

    }
}
