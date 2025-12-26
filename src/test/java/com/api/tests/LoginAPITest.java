package com.api.tests;

import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest
{

    private static final Logger log = LoggerFactory.getLogger(LoginAPITest.class);

    @Test
    public  void loginAPITest(){
        UserCredentials usercredentials=new UserCredentials("iamfd","password");
                            given()
                             .baseUri("http://64.227.160.186:9000/v1")
                             .and()
                             .contentType(ContentType.JSON)
                             .and()
                             .accept(ContentType.JSON)

                                    .and()
                                    .body(usercredentials)
                             .log().uri()
                             .log().method()
                             .log().headers()
                             .log().body()



                               .when()

                               .post("login")
                               .then()
                                    .statusCode(200)
                                    .time(lessThan(1000L))
                                    .body("message",equalTo("Success"))
                                    .log().body()
                                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));










    }




}
