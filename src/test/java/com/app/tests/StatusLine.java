package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class StatusLine {

    @Test
    public void statusLineTest() {

        Response response = RestAssured.get("https://uinames.com/api/");

        //statusLine() - returns all the information in the status line of the response
        String statusLine = response.statusLine();
        System.out.println(statusLine);

        Assert.assertTrue(statusLine.contains("200"));
    }
}
