package com.api.tests;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LoginAPITest
{
   private UserCredentials usercredentials;

   @BeforeMethod(description = "Create the Payload for the Login API")
    public void SetUp(){
        usercredentials=new UserCredentials("iamfd","password");

    }


    @Test(description = "Verifying if login api is working for FD user",groups = {"api","regression","smoke"})

    public  void loginAPITest() {
        System.out.println(System.getProperty("env"));


                            given()
                                    .spec(SpecUtil.requestSpec(usercredentials))
                                    .when()
                                    .post("login")
                                    .then()
                                    .spec((SpecUtil.responseSpec_OK()))
                                    .body("message",equalTo("Success"))
                                    .log().body()
                                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));


    }




}
