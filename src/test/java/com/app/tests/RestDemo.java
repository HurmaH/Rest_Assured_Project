package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class RestDemo {

    @Test
    public void test1(){

        //this is class from rest assured libriary, it contains methods and objects used for
        //creating and sending requests

        //1. get = for making request, accepts the resource uri as parameter
        //2. response = represents the response that came back from server based on out request
        Response response =RestAssured.get("https://uinames.com/api/");

        //3. prints the response
        response.prettyPrint();

        //4.asString() = converts the response to String
        String resString = response.asString();
        //System.out.println(resString);
        Assert.assertTrue(resString.contains("name"));

        //5. getting status code
        int code = response.statusCode();
        Assert.assertEquals(200, code);
    }
}
