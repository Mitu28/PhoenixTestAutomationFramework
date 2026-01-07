package com.api.tests;
import static com.api.constants.Role.*;
import static com.api.utils.SpecUtil.*;
import  static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class UserDetailsAPITest{


     @Test(description = "Verify if the Userdetails API response is shown correctly",groups = {"api","smoke","regression",})
    public void userDetailsAPITest()  {
         given()
                 .spec(requestSpecWithAuth(FD))
                 .when()
                .get("userdetails")
                .then()
                 .spec(responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));







    }


}