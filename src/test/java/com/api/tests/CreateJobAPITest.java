package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.request.model.*;
import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {
   private CreateJobPayload createJobPayload;

   @BeforeMethod(description = "Creating createjob api request payload")
    public void Setup(){

        Customer customer=new Customer("Mits","K","890876546","","mitu@gmail.test","");
        System.out.println(customer.first_name());
        CustomerAddress customerAddress=new CustomerAddress("D 404","Vasant Vihar","Bhanu nagar","Inorbit mall","Mumbai","411039","India","Maharastra");
        CustomerProduct customerProduct=new CustomerProduct(getTimeWithDaysAgo(10),"90183914317995","90183914317925","70183914317929",getTimeWithDaysAgo(10),1,1);
        Problems problems=new Problems(1,"Battery Issue");
        List<Problems> problemList=new ArrayList<>();
        problemList.add(problems);
        createJobPayload=new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemList);
    }

    @Test(description = "Verify if the create job is able to create Inwarranty job",groups = {"api","smoke","regression",})
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
