package com.cydeo.tests.day06_xmlpath_deserialization;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanXMLPath extends SpartanTestBase {

    /**
     * Given accept type is apllication/xml
     * When I send GET request to /api/spartans
     * Then status code is 200
     * And content type is XML
     * And spartan at index 0 is
     */

    @Test
    public void testXML(){
        Response response = given().accept(ContentType.XML)
                .when().get("/spartans");

        assertEquals(200,response.statusCode());
        assertEquals(ContentType.XML.toString(),response.getContentType());

        XmlPath xmlPath = response.xmlPath();
        List<Object> item = xmlPath.getList("List.item[0].id");
        System.out.println(item);

    }





}
