package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class QueryParam {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";
    }

    @Test
    public void testAmount(){
        given().
                //proýide the key and value of the query param
                queryParam("amount", 2).
                when().get().
                prettyPrint();
    }

    @Test
    public void testRegion(){
        given().
                queryParam("region", "Colombia").
                when().get().
                prettyPrint();
    }


    @Test
    public void amountAndRegionTest(){
        given().
                queryParam("amount", 2).
                queryParam("region", "Colombia").
                when().get().
                prettyPrint();
    }
}
