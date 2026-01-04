package com.api.tests;
import static com.api.constants.Role.*;
import com.api.utils.SpecUtil;
import  static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class UserDetailsAPITest{


     @Test
    public void userDetailsAPITest()  {
         given()
                 .spec(SpecUtil.requestSpecWithAuth(FD))
                 .when()
                .get("userdetails")
                .then()
                 .spec(SpecUtil.responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));







    }


}