package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static io.restassured.RestAssured.*;
import static com.api.utils.ConfigManager.*;

import  static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

public class MasterAPITest {

    @Test
    public void masterAPITest(){

        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header("Authorization",getToken(FD))
                .contentType("")
                .log().all()
                .when()
                .post("master")//default content-type application/url-formencoded
                .then()
                .log().all()
                .statusCode((200))
                .time(lessThan(1000L))
                .body("data",notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",equalTo(2))
                .body("data.mst_model.size()",greaterThan(0))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema"));







    }

    @Test
    public void InvalidToken(){
           given()
                   .baseUri(getProperty("BASE_URI"))
                .and()
                   .header("Authorization","")
                   .and()
                .contentType("")
                .log().all()
                   .when()
                .post("master")//default content-type application/url-formencoded
                .then()
                .log().all()
                .statusCode((401));



    }

}
