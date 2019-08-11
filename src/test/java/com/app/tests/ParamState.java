package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ParamState{

    @BeforeClass
    public static void setUpClass(){

        RestAssured.baseURI = "https://api.got.show/api";
    }

    @Test
    public void getCityByNameTest() {
       //given() --> statement where we prepare the request
        // when() --> send request
        given().
                pathParam("name", "Qarth").//adding param variable name, value Qarth

        when().
                get("/map/cities/{name}").//{name} will be replaced by value of the path param
                prettyPrint();
    }
}
