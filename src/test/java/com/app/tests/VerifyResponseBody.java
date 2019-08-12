package com.app.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

public class VerifyResponseBody {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";
    }

    @Test
    public void bodyTest(){
        given().when().get().

        //body("name" --> first param is locator, gets the value from the response
        // it will be actual value
        // second argument will be matcher
        then().assertThat().body("name", notNullValue());//verify key name has value
    }

    @Test
    public void verifyRegion(){
        String region = "Canada";

        //where only want to get people name from Canada
        given().
                queryParam("region", region).
                when().get().
                then().log().body().
                assertThat().body("country", is(region));
    }

    @Test
    public void verifyNumberOfResults(){
        given().
                queryParam("amount", 2).
                when().get().
                then().
                assertThat().body("$", hasSize(2));//verify number of objects in Json response
    }


}
