package com.virgingames.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestUtils {

    /*
    This method generates a random integer and converts it into String
     */
    public static String getRandomValue(){
        Random random =new Random();
        int randomInt= random.nextInt(100000);
        return Integer.toString(randomInt);
    }

    public static String generateFirstName(){
        Faker faker=new Faker();
       return faker.name().firstName();
    }

}
