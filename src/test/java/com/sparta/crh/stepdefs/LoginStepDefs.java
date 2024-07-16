package com.sparta.crh.stepdefs;

import com.sparta.crh.Pojos.loginPojos.LoginResponse;
import com.sparta.crh.utilities.AppConfig;
import com.sparta.crh.utilities.LoginUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefs extends StepDefsSuper{

    private String baseUrl = AppConfig.getBaseUrl();
    private String loginUrl = AppConfig.getLoginUrl();
    private String username;
    private String password;
    private LoginResponse loginResponse;
    private JSONObject errorResponse;
    private String errors;

    @Given("I have access to a valid sparta global account")
    public void iHaveAccessToAValidSpartaGlobalAccount() {

        username = AppConfig.getValidUsername();
        password = AppConfig.getValidPassword();

    }

    @When("I send a post request to the \\/Auth\\/login endpoint with a JSON object containing the valid username and password.")
    public void iSendAPostRequestToTheAuthLoginEndpointWithAJSONObjectContainingTheValidUsernameAndPassword() {
        response = LoginUtils.sendLoginRequest(baseUrl,loginUrl,username,password);

        loginResponse = response.as(LoginResponse.class);
    }


    @Then("I will be sent back a response with a JSON object in the body containing an authorisation token")
    public void iWillBeSentBackAResponseWithAJSONObjectInTheBodyContainingAnAuthorisationToken() {
        Assertions.assertFalse(loginResponse.getToken().isBlank());
    }

    @And("A status code of {int}")
    public void aStatusCodeOf(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());

    }


    @Given("I have access to a invalid sparta global account")
    public void iHaveAccessToAInvalidSpartaGlobalAccount() {
        username = "incorrect";
        password = "details";

    }

    @When("I send a post request to the \\/Auth\\/login endpoint with a JSON object containing the invalid username and password.")
    public void iSendAPostRequestToTheAuthLoginEndpointWithAJSONObjectContainingTheInvalidUsernameAndPassword() {
        response = LoginUtils.sendLoginRequest(baseUrl,loginUrl,username,password);
    }

    @Then("I will be sent back a response with a JSON object in the body containing a status code of {int} Unauthorised")
    public void iWillBeSentBackAResponseWithAJSONObjectInTheBodyContainingAStatusCodeOfUnauthorised(int statusCode) {
        Assertions.assertEquals(statusCode, response.statusCode());
    }

    @Given("I do not have access to any sparta global accounts")
    public void iDoNotHaveAccessToAnySpartaGlobalAccounts() {
        username = null;
        password = null;

    }

    @When("I send a post request to the \\/Auth\\/login endpoint with a JSON object containing a null username and password.")
    public void iSendAPostRequestToTheAuthLoginEndpointWithAJSONObjectContainingANullUsernameAndPassword() {

        response = LoginUtils.sendLoginRequest(baseUrl,loginUrl,username,password);
        //parsing the responseBody as a JSON object
        errorResponse = response.getBody().as(JSONObject.class);
        //parsing the errors array into a String
        errors = errorResponse.get("errors").toString();

        
    }

    @Then("I will be sent back a response with a JSON object in the body containing a status code of {int}")
    public void iWillBeSentBackAResponseWithAJSONObjectInTheBodyContainingAStatusCodeOf(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @And("an informative error message stating i need a username and password")
    public void anInformativeErrorMessage() {

        //Asserting that the error array contains the expected error messages
        Assertions.assertTrue(errors.contains("The Password field is required.") && errors.contains("The Username field is required."));

    }

    @Given("I only have access to a sparta global username")
    public void iDoNotHaveAccessToASpartaGlobalUsername() {
        username = "sparta";
        password = null;
    }

    @When("I send a post request to the \\/Auth\\/login endpoint with a JSON object containing a username and null password.")
    public void iSendAPostRequestToTheAuthLoginEndpointWithAJSONObjectContainingAUsernameAndNullPassword() {
        response = LoginUtils.sendLoginRequest(baseUrl,loginUrl,username,password);
        errorResponse = response.getBody().as(JSONObject.class);
        errors = errorResponse.get("errors").toString();
    }

    @And("an informative error message stating I need a password")
    public void anInformativeErrorMessageStatingINeedAPassword() {
        Assertions.assertTrue(errors.contains("The Password field is required."));
    }

    @Given("I only have access to a sparta global password")
    public void iOnlyHaveAccessToASpartaGlobalPassword() {
        username = null;
        password = "global";
    }


    @When("I send a post request to the \\/Auth\\/login endpoint with a JSON object containing a null username and valid password.")
    public void iSendAPostRequestToTheAuthLoginEndpointWithAJSONObjectContainingANullUsernameAndValidPassword() {
        response = LoginUtils.sendLoginRequest(baseUrl,loginUrl,username,password);
        errorResponse = response.getBody().as(JSONObject.class);
        errors = errorResponse.get("errors").toString();
    }

    @And("an informative error message stating I need a username")
    public void anInformativeErrorMessageStatingINeedAUsername() {
        Assertions.assertTrue(errors.contains("The Username field is required."));
    }
}
