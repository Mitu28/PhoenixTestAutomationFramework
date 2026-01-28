package com.api.tests;

import com.api.constants.Role;
import com.api.request.model.*;
import com.api.utils.FakerDataGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobAPITestWithFakeData {
   private CreateJobPayload createJobPayload;

   @BeforeMethod(description = "Creating create job api request payload")
    public void Setup(){

       createJobPayload = FakerDataGenerator.generateFakeCreateJobData();

   }

    @Test(description = "Verify if the create job is able to create Inwarranty job",groups = {"api","smoke","regression"})
    public void createJobAPITest(){
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
