package com.app.tests;

import com.app.pojos.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.junit.Test;

public class PojoExample {

    //seriliztion: java --> jason or other
    //deserilization : jason or other ---> java

    @Test
    public void testGson(){
        Person person = new Person();

        person.setName("Mishka");
        person.setSurname("Gummy");
        person.setGender("male");
        person.setRegion("Lapland");

        System.out.println("Printing the person object: "+person);

        Gson gson = new Gson();

        String jsonString = gson.toJson(person);
        System.out.println("Printin Json from object: "+jsonString);

        //TODO deserilization

        String myJsonString = "{\"name\":\"Mishka\"," +
                                "\"surname\":\"Yummy\","+
                                "\"gender\":\"male\",+" +
                                "\"region\":\"EverLand\"}";
        //fromJason() --> converts the input to given type
        Person myPerson= gson.fromJson(myJsonString, Person.class);
        System.out.println("Printing object from Json: "+myPerson);

    }

    @Test
    public void testJson() throws JsonProcessingException {
        Person person = new Person();

        person.setName("Mishka");
        person.setSurname("Gummy");
        person.setGender("male");
        person.setRegion("Lapland");

        System.out.println("Printing the person object: " + person);

        //ObjectMapper --> class which converts from and to pojos
        ObjectMapper objectMapper = new ObjectMapper();

        //writeValueAsString(person) --> takes the input and converts to json
        String json = objectMapper.writeValueAsString(person);
        System.out.println("Printing from person to Json: "+json);

        // TODO deserialization example
        String myJsonString = "{\"name\":\"Jack\"," +
                "\"surname\":\"ONeil\"," +
                "\"gender\":\"male\"," +
                "\"region\":\"Earth\"}";
        // readValue() --> converts the input to given type
        Person myPerson = objectMapper.readValue(myJsonString, Person.class);
        System.out.println("Printing the new object:" + myPerson);
    }
}
