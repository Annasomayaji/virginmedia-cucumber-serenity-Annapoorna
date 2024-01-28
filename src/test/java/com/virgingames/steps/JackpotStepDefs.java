package com.virgingames.steps;

import com.virgingames.testbase.TestBase;
import com.virgingames.utils.ExcelReader;
import com.virgingames.virginmediasteps.JackpotSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class JackpotStepDefs {

    @Steps
    JackpotSteps steps;
    private static final Logger log = LogManager.getLogger(TestBase.class);
    static ValidatableResponse response;


    @When("I send a GET request to jackpot endpoint with query param {string}")
    public void iSendAGETRequestToJackpotEndpointWithQueryParam(String currency) {
        log.info("Sending GET request....");
        response = steps.getJackpot(currency);

    }

    @Then("I should get back a valid status code {int}")
    public void iShouldGetBackAValidStatusCode(int statusCode) {
        log.info("Verifying response status code and that response body is not empty....");
        response.statusCode(statusCode).body("$", not(empty()));  //soft assert
        response.body("$", hasKey("data"));  //Validating whether the response body has the key
    }

    @When("I send a GET request to jackpot endpoint with {string}")
    public void iSendAGETRequestToJackpotEndpointWith(String currency) {
        log.info("Sending GET request with currency value....");
        response = steps.getJackpot(currency);
    }

    @Then("I should see the {string} in the response body")
    public void iShouldSeeTheInTheResponseBody(String currency) {
        log.info("Verifying response body has same currency as request....");
        response.body("data.pots.currency", hasItem(currency));
    }

    @Then("I should see names from sheet name {string} and row number {string} associated with id")
    public void iShouldSeeNamesFromSheetNameAndRowNumberAssociatedWithId(String sheetName, String rowNumber) {
        //reading test data from excel file
        log.info("Verifying the id is associated with correct name....");
        ExcelReader reader = new ExcelReader();
        String id = null;
        String name = null;
        try {
            List<Map<String, String>> testData = reader.getData("src/test/resources/testdata/jackpot-names.xlsx", sheetName);
            id = testData.get(Integer.parseInt(rowNumber)).get("id");
            name = testData.get(Integer.parseInt(rowNumber)).get("name");
            System.out.println("Id: " + id);
            System.out.println("Name: " + name);
        } catch (IOException e) {
            System.out.println("Jackpot-name excel data file not found.");
        }
        //verifying if response body contains the name associated with id
        response.body("data.pots.findAll{it.id=='" + id + "'}.name", hasItem(name));

    }
}
