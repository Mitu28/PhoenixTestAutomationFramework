package com.api.tests;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constants.Role.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import  static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;



import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class UserDetailsAPITest{


     @Test
    public void userDetailsAPITest()  {
        Header authHeader=new Header("Authorization", getToken(FD));



         given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header(authHeader)
                .and()
                .accept(ContentType.JSON)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()

                .when()
                .get("userdetails")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .log().body()
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));







    }


}