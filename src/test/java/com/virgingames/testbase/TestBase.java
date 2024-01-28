package com.virgingames.testbase;

import com.virgingames.utils.PropertyReader;
import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;

public class TestBase {

    public TestBase(){

    }

    @BeforeClass
    public static void init() {
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/resources/propertiesfile/log4j2.properties");
        PropertyReader.getInstance().getProperty("baseUri");
        RestAssured.baseURI = PropertyReader.getInstance().getProperty("baseUri");
       // RestAssured.basePath = Path.API_JACKPOTS;
    }


}
