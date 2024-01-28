package com.virgingames.virginmediasteps;

import com.virgingames.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class JackpotSteps {

    @Step("Getting all jackpot bingo details")
    public ValidatableResponse getJackpot(String currency) {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("currency", currency);
        return SerenityRest.given().log().all()
                .queryParams(qParam)
                .when()
                .get(EndPoints.JACKPOT_BINGO)
                .then().log().all();
    }
}
