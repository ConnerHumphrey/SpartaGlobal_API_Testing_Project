package com.sparta.crh.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Map;

public class LoginUtils {

    public static RequestSpecification postRequestSpecForLoggingInUser(String baseUri, String path, String username, String password) throws JsonProcessingException {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ))
                .setContentType(ContentType.JSON)
                .setBody(createLoginJSON(username,password))
                .build();
    }

    public static Response sendLoginRequest(String baseUri, String path, String username, String password){

        try{
            return RestAssured
                    .given(LoginUtils.postRequestSpecForLoggingInUser(baseUri,path, username, password))
                    .when()
                    .post()
                    .thenReturn();

        }catch (Exception e){

            return null;
        }

    }

    private static JSONObject createLoginJSON(String username, String password){
        JSONObject loginObject = new JSONObject();

        loginObject.put("username", username);
        loginObject.put("password", password);

        return loginObject;

    }
}
