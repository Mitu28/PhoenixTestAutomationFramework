package com.api.tests;
import  static com.api.constants.Role.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import com.api.utils.SpecUtil;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class CountAPITest {

    @Test(description = "Verify if the count api is giving correct response",groups = {"api","smoke","regression",})

    public void verifyCountAPIResponse() {
        given()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("/dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("message",equalTo("Success"))

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
                .spec(SpecUtil.requestSpec())
                .when()
                .get("/dashboard/count")
                .then()
                .spec(SpecUtil.responseSpec_TEXT(401));


    }
}






