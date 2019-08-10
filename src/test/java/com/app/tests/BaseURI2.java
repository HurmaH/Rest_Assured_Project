package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseURI2 {

    @BeforeClass
    public static void setUpClass(){

        RestAssured.baseURI = "https://api.got.show/api";
    }

    @Test
    public void continentTest(){

        //get all continent
        Response response = RestAssured.get("/book/continents/");
        response.prettyPrint();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(response.asString().contains("Westeros"));

    }

    @Test
    public void episodeTest(){
        Response response = RestAssured.get("/show/episodes/");
        Assert.assertEquals(200, response.statusCode());
        response.prettyPrint();
       Assert.assertTrue(response.asString().contains("The North Remembers"));
    }
}
