package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.constants.Role;
import com.api.pojo.*;
import com.api.utils.SpecUtil;
import org.testng.annotations.Test;

public class CreateJobAPITest {
    @Test
    public void createJobAPITest(){

        Customer customer=new Customer("Mits","K","890876546","","mitu@gmail.test","");
        CustomerAddress customerAddress=new CustomerAddress("D 404","Vasant Vihar","Bhanu nagar","Inorbit mall","Mumbai","411039","India","Maharastra");
        CustomerProduct customerProduct=new CustomerProduct("2025-04-25T05:00:00.000Z","50183914317997","50183914317920","50183914317920","2025-04-25T05:00:00.000Z",1,1);
        Problems problems=new Problems(1,"Battery Issue");
        Problems[] problemsArray=new Problems[1];
        problemsArray[0]=problems;
        CreateJobPayload createJobPayload=new CreateJobPayload(0,2,1,1,customer,customerAddress,customerProduct,problemsArray);
       given()
               .spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload ))
               .when()
               .post("/job/create")
               .then()
               .spec(SpecUtil.responseSpec_OK());













    }
}
