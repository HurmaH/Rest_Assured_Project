package com.app.tests;

import com.app.pojos.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ResponseToJsonExample {

    @Test
    public void test() throws JsonProcessingException {
        Response response = given().get("https://uinames.com/api/");

        //        String name = response.path("name");
//        System.out.println("name = " + name);
        // USING REST ASSURED

        // values from the json response is automatically mapped to correspoonding fields
        // in the person object
        Person p1 = response.as(Person.class);
        System.out.println("p1.getName() = " + p1.getName());
        System.out.println("p1.getSurname() = " + p1.getSurname());
        System.out.println("p1.getGender() = " + p1.getGender());
        System.out.println("p1.getRegion() = " + p1.getRegion());

        // USING GSON
        Gson gson = new Gson();
        Person p2 = gson.fromJson(response.asString(), Person.class);
        System.out.println("p2 = " + p2);

        // USING JACKSON DATABIND
        ObjectMapper objectMapper = new ObjectMapper();
        Person p3 = objectMapper.readValue(response.asString(), Person.class);
        System.out.println("p3 = " + p3);

    }

    @Test
    public void responseListOfObjects() {
        Response response = given().
                queryParam("amount", "9").
                when().get("https://uinames.com/api/");

        List<Person> people = response.jsonPath().getList("", Person.class);//path"" = top level

        for (Person person : people) {
            System.out.println(person);
        }
    }
}
