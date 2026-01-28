package com.api.utils;

import com.api.request.model.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FakerDemo2 {

   public static void main(String[] args) {

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


       String dop=DateTimeUtil.getTimeWithDaysAgo(10);
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

       CreateJobPayload payload=new CreateJobPayload(0,
               2,
               1,
               1,
               customer,
               customerAddress,
               customerProduct,
               problemsList);
System.out.println(payload);












    }


}
