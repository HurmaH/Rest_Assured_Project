package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AccessToken {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI="https://cybertek-reservation-api-qa.herokuapp.com/";
    }

    @Test
    public void getToken(){
        Response response = given().log().all().
                param("email", "teacherva5@gmail.com").
                param("password", "maxpayne").
                get("/sign");

        response.then().log().all().
                assertThat().statusCode(200);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDY0IiwiYXVkIjoidGVhY2hlciJ9.japWZkmQ3ExAJA6HxXEMSm92h5ZfYARx1Ci8sSiLgzM";

        //used to parse through jsaon response easily
        String accessToken = response.jsonPath().get("accessToken");

        System.out.println(accessToken);

        //trying to get the my campus
        //we are passing our token as a part of the request header
        RestAssured.given().header("Authorization", token).
                get("/api/campuses").then().log().all().
                assertThat().statusCode(200);

    }
}
