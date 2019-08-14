package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class PostRequest {


    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI="https://cybertek-reservation-api-qa.herokuapp.com/";
    }

    @Test
    public void createTeam(){
        Response response = given().log().all().
                param("email", "teacherva5@gmail.com").
                param("password", "maxpayne").
                get("/sign");

        response.then().log().all().
                assertThat().statusCode(200);

        String accessToken = response.jsonPath().get("accessToken");

        String team = "Adkjjlk";
        RestAssured.given().
                header("Authorization", accessToken).
                queryParam("campus-location", "VA").
                queryParam("batch-number", "8").
                queryParam("team-name", team).
                post("/api/teams/team").
                then().log().all().statusCode(201).
                body(containsString("added"));

        //can be verified created team by:
        //sending sql query and see whether tema exist or not
        //checking id number, which should be higher than previous time each time
        //
    }







}
