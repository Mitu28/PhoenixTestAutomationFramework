package com.api.tests;

import  static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class CountAPITest {

    @Test
    public void verifyCountAPIResponse() {
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header("Authorization",getToken(FD))
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/dashboard/count")
                .then()
                .log().all()
                .statusCode(200)
                .body("message",equalTo("Success"))
                .time(lessThan(1000L))
                .body(notNullValue())
                .body("data.size()",equalTo(3))
                .body("data.count",everyItem(greaterThanOrEqualTo(0)))
                .body("data.label",everyItem(not(blankOrNullString())))
                .body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponse_FD.json"))
                .body("data.key",containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"));

    }


    @Test
    public void countAPITest_MissingAuthToken(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/dashboard/count")
                .then()
                .log().all()
                .statusCode(401);


    }
}






