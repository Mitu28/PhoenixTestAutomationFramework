package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

public class MasterAPITest {

    @Test
    public void masterAPITest(){

        given()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .post("master")//default content-type application/url-formencoded
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body("data",notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",equalTo(2))
                .body("data.mst_model.size()",greaterThan(0))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));







    }

    @Test
    public void InvalidToken(){
           given()
                   .spec(SpecUtil.requestSpec())
                   .log().all()
                   .when()
                   .post("master")//default content-type application/url-formencoded
                   .then()
                   .spec(SpecUtil.responseSpec_TEXT(401));



    }

}
