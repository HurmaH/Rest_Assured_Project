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
                pathParam("name", "Braavos").//adding param variable name, value Qarth

        when().
                get("/map/cities/{name}").//{name} will be replaced by value of the path param
                prettyPrint();
    }


    @Test
    public void getCharacterByName(){
        given().
                pathParam("name", "Pypar").
        when().
                get("/map/characters/{name}").
                prettyPrint();
    }

    @Test
    public void getCharacterById(){
        given().
                pathParam("id", "5cc0743604e71a0010b854cd").
        when().
                get("map/characters/byId/{id}"). //https://api.got.show/api/map/characters/byId/:id
                prettyPrint();
    }
}
