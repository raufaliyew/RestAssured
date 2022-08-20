package com.cydeo.tests.day08_hamcrest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void numbersTest(){
        assertThat(3+2,is(5));
        assertThat(3+2,equalTo(5));
        assertThat(3+2,is(equalTo(5)));
        assertThat(3+2,is(greaterThan(4)));
        assertThat(3+2,is(not(equalTo(4))));
    }

    @Test
    public void stringTest(){
        String word = "wooden spoon";
        assertThat(word,startsWith("wooden"));
        assertThat(word,startsWithIgnoringCase("Wooden"));
        assertThat(word, is("wooden spoon"));
        assertThat(word,is(equalToIgnoringCase("Wooden SPOON")));

        assertThat(word, containsString("den"));
        assertThat(word,is(blankOrNullString()));

    }

    @Test
    public void listTest(){

        List<Integer> nums = Arrays.asList(5, 10, 1, 54);
        assertThat(nums,hasSize(4));
        assertThat(nums,hasItem(10));
        assertThat(nums,hasItems(10,5));
        assertThat(nums,containsInAnyOrder(10,5,1,54));
        assertThat(nums,containsInRelativeOrder(1,54));
        assertThat(nums,everyItem(greaterThanOrEqualTo(0)));



    }
}
