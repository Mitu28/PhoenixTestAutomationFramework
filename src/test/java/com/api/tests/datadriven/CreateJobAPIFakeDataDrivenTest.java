package com.api.tests.datadriven;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobAPIFakeDataDrivenTest {



    @Test(description = "Verify if the create job is able to create Inwarranty job",groups = {"api","dtadriven","regression"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,dataProvider = "CreateJobAPIFakerDataProvider")
    public void createJobAPITest(CreateJobPayload createJobPayload){
        given()
               .spec(requestSpecWithAuth(Role.FD,createJobPayload ))
               .when()
               .post("/job/create")
               .then()
               .spec(responseSpec_OK())
               .body(matchesJsonSchemaInClasspath("response-schema/CreateJobResponseSchema.json"))
               .body("message", equalTo("Job created successfully. "))
               .body("data.mst_service_location_id",equalTo(1))
               .body("data.job_number",startsWith("JOB_"));
    }
}
