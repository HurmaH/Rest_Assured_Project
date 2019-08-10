package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class HeadersTest {

    @Test
    public void test1() {

        //1. get = for making request, accepts the resource uri as parameter
        //2. response = represents the response that came back from server based on out request
        Response response = RestAssured.get("https://uinames.com/api/");

        //3.Return headers of the response
        System.out.println(response.headers());

        //4. getting headers line by line
        //header() -> returns value of header
        String contentType = response.header("Content-Type");
        System.out.println(contentType);
        Assert.assertEquals("application/json; charset=utf-8", contentType);

    }
}
