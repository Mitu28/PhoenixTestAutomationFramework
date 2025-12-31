package com.api.tests;

import com.api.pojo.UserCredentials;
import  static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
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
                             .baseUri(getProperty("BASE_URI"))
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
