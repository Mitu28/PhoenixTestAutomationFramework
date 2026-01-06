package com.api.tests;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class LoginAPITest
{

    //private static final Logger log = LoggerFactory.getLogger(LoginAPITest.class);

    @Test
    public  void loginAPITest() {
        System.out.println(System.getProperty("env"));
        UserCredentials usercredentials=new UserCredentials("iamfd","password");


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
