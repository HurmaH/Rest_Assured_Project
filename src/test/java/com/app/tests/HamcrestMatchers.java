package com.app.tests;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchers {
    //Hamcrest libriary provides matchers (assertion method)
    //Rest Assured uses hamcrest assertions (matchers)
    //works with both junit and testng

    @Test
    public void test(){
        assertThat(1,equalTo(1));

        //verify that first argument i equal to the second
        assertThat("Hurmik", is("Hurmik"));

        //verify if first argument is NOT equal to the second
        assertThat("Hurmik", is(not("Mishka")));

        //compare ignoring case
        assertThat("Hurmik", equalToIgnoringCase("hurmIK") );

        //compare ignoring space
        assertThat("Hurmik", equalToIgnoringWhiteSpace(" Hurmik "));//not Hurm ik

        //comparing numbers
        assertThat(10, greaterThan(9));
        assertThat(10, lessThan(11));
        assertThat(10, lessThanOrEqualTo(11));

        //List
        List <String> list = Arrays.asList("one", "two", "three");
        assertThat(list, hasSize(3));

        assertThat(list, contains("one","two" ,"three" ));
        assertThat(list, hasItems("one", "two"));


        List<Integer> nums = Arrays.asList(9,10,3);
        assertThat(nums, everyItem(greaterThan(2)));


    }
}
