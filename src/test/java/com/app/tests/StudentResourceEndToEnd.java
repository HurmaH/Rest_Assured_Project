package com.app.tests;

import com.app.utlities.BookITRestUtility;
import com.app.utlities.DatabaseUtility;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class StudentResourceEndToEnd {

    Logger log = Logger.getLogger(StudentResourceEndToEnd.class);

    @Test
    public void postStudent() throws InterruptedException {
        // Create test data
        Faker faker = new Faker();
        String firstName = faker.name().firstName();//we are creating ourselves
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase()+lastName.toLowerCase()+"@"+faker.internet().domainName();
        String password = firstName.toLowerCase()+lastName.toLowerCase();
        String role = "student-team-member";
        String campusLocation = "VA";
        String batchNumber = "8";
        String teamName = "CodeHunters";

        String expectedMessage = "user "+firstName+" "+lastName+" has been added to database.";

        String token = BookITRestUtility.getTeacherToken();//only teacher can create

        //building request
        given().
                log().all().
                header("Authorization", token).
                queryParam("first-name", firstName).//exact spelling of params taken
                queryParam("last-name", lastName).
                queryParam("password", password).
                queryParam("email", email).
                queryParam("role", role).
                queryParam("campus-location", campusLocation).
                queryParam("batch-number", batchNumber).
                queryParam("team-name", teamName).
                when().                                 //going to POST
                post("api/students/student").        //POST to URL
                then().
                log().all().
                assertThat().statusCode(201).
                body( is(expectedMessage));//HamcrestMatchers

        // GET THE NEW USER FROM DB USING JDBC
        Map<String, Object> dbUser = getDBUser(email);
        log.info(dbUser);

        // VERIFY THE DB DATA HAS THE CORRECT INFO
        //Comparing expected with actual
        assertThat(dbUser.get("firstname"), is(firstName));
        assertThat(dbUser.get("lastname"), is(lastName));
        assertThat(dbUser.get("email"), is(email));
       // verify that id is not empty
        assertThat(dbUser.get("id"), is(notNullValue()));

        // GET THE USER INFO FROM DB USING API
        String id = dbUser.get("id").toString();

        given().
                log().all().
                header("Authorization", token).
                pathParam("id", id).
                when().
                get("/api/students/{id}").//{id} will be replaced by actual id
                then().
                log().all().
                assertThat().statusCode(200).
                body("firstName", is(firstName)).
                body("lastName", is(lastName)).
                body("role", is(role));



        // VERIFY USING UI
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://cybertek-reservation-qa.herokuapp.com/sign-in");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password + Keys.ENTER);
        Thread.sleep(5000);
        assertThat(driver.getCurrentUrl(), endsWith("map"));


    }


    public static Map<String, Object> getDBUser(String email){
        DatabaseUtility.createConnection();
        String sql = "select firstname, lastname, role, id, email from users where email = '"+email+"';";
        Map<String, Object> rowMap = DatabaseUtility.getRowMap(sql);
        DatabaseUtility.closeConnection();
        return rowMap;
    }
}