package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestAssuredLogging {

    @Test
    public void loggingTest(){
        //log---> provides loggong options for request
        given().log().all().
                when().get("https://uinames.com/api/");//print request and response

        given().log().headers().
                when().get("https://uinames.com/api/");
    }

    @Test
    public void logResponseTest(){
        Response response = RestAssured.get("https://uinames.com/api/");

        response.then().log().body();
        System.out.println("*********");

        response.then().log().all();
    }


}
