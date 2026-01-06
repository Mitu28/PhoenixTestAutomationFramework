package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.request.model.*;
import com.api.utils.SpecUtil;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {
    @Test
    public void createJobAPITest(){

        Customer customer=new Customer("Mits","K","890876546","","mitu@gmail.test","");
        System.out.println(customer.first_name());
        CustomerAddress customerAddress=new CustomerAddress("D 404","Vasant Vihar","Bhanu nagar","Inorbit mall","Mumbai","411039","India","Maharastra");
        CustomerProduct customerProduct=new CustomerProduct("2025-04-25T05:00:00.000Z","80183914317995","70183914317925","70183914317929","2025-04-25T05:00:00.000Z",1,1);
        Problems problems=new Problems(1,"Battery Issue");
      List<Problems> problemList=new ArrayList<>();
        problemList.add(problems);
        CreateJobPayload createJobPayload=new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemList);
       given()
               .spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload ))
               .when()
               .post("/job/create")
               .then()
               .spec(SpecUtil.responseSpec_OK())
               .body(matchesJsonSchemaInClasspath("response-schema/CreateJobResponseSchema.json"))
               .body("message", equalTo("Job created successfully. "))
               .body("data.mst_service_location_id",equalTo(1))
               .body("data.job_number",startsWith("JOB_"));
















    }
}
