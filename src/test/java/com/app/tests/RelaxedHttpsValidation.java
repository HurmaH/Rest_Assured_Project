package com.app.tests;

import io.restassured.RestAssured;
import org.junit.Test;

public class RelaxedHttpsValidation {

    @Test
    public void relaxY0(){

        //we are goin to trust unsecured connections with bad certification

        RestAssured.useRelaxedHTTPSValidation();

        RestAssured.get("https://untrusted-root.badssl.com/").
                then().log().all().statusCode(200);
    }
}
