package com.sparta.crh.stepdefs;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class StepDefsSuper {
     protected static Response response;
     protected static JSONObject responseBody;
     protected static Response invalidResponse;
     protected static JSONObject invalidResponseBody;
}
