package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StatusCodeValidation {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";
    }

    @Test
    public void testOK(){
        //given --> prep request
        given().
        //when() --> making query, submit query
        when().get().
        //then --> verify response
        then().statusCode(200);
    }

    @Test
    public void notFoundTest(){
        given().
        //we are passing wrong value on purpose
        //so that it goes to wrong url
        when().get("ljlkjlkj").
                then().statusCode(404);
    }

}
