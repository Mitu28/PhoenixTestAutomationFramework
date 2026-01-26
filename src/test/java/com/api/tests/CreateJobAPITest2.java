package com.api.tests;

import com.api.constants.Role;
import com.api.request.model.*;
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class CreateJobAPITest2 {
   private CreateJobPayload createJobPayload;

   @BeforeMethod(description = "Creating createjob api request payload")
    public void Setup(){
       Faker faker=new Faker();
       String fname=faker.name().firstName();
       String lname=faker.name().lastName();
       String mobileNumber=faker.numerify("70########");
       String altMobileNumber=faker.numerify("70########");
       String emailId=faker.internet().emailAddress();
       String altEmailId=faker.internet().emailAddress();

       Customer customer=new Customer(fname,lname,mobileNumber,altMobileNumber,emailId,altEmailId);
       System.out.println(customer);


       String faltNumber=faker.numerify("###");
       String aparmentName=faker.address().streetName();
       String streetName=faker.address().streetName();
       String landmark=faker.address().streetName();
       String area=faker.address().streetName();
       String pinCode=faker.numerify("#####");
       String country=faker.address().country();
       String state=faker.address().state();


       CustomerAddress customerAddress=new CustomerAddress(faltNumber,
               aparmentName,
               streetName,
               landmark,
               area, pinCode,
               country,
               state);
       System.out.println(customerAddress);


       String dop= DateTimeUtil.getTimeWithDaysAgo(10);
       String serial_number=faker.numerify("###############");
       String imei1=faker.numerify("###############");
       String imei2=faker.numerify("###############");
       String popurl=faker.internet().url();




       CustomerProduct customerProduct=new CustomerProduct(dop,
               serial_number,
               imei1,imei2
               ,popurl,1
               ,1);
       System.out.println(customerProduct);


       String fakeRemark=faker.lorem().sentence(5);
       Random random=new Random();
       int pronlemId=random.nextInt((26)+1);

       Problems problems=new Problems(pronlemId,fakeRemark);
       System.out.println(problems);

       List<Problems> problemsList=new ArrayList<Problems>();
       problemsList.add(problems);

       createJobPayload = new CreateJobPayload(
               0,
               2,
               1,
               1,
               customer,
               customerAddress,
               customerProduct,
               problemsList
       );





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
