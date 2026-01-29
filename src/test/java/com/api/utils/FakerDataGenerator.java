package com.api.utils;


import com.api.request.model.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FakerDataGenerator {
    private static Faker faker=new Faker();
    public final static Random RANDOM=new Random();
    private final static int MST_SERVICE_LOCATION_ID=0;
    private final static int MST_PLATFORM_ID=2;
    private final static int MST_WARRANTY_STATUS_ID=1;
    private final static int MST_OEM_ID=1;
    private final static int PRODUCT_ID=1;
    private final static int MST_MODEL_ID=1;
    private final static int validProblemsId[]={1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};


    private FakerDataGenerator(){

    }

    public static CreateJobPayload generateFakeCreateJobData(){
        Customer customer=generateFakeCustomerJobData();
        CustomerAddress customerAddress=generateFakeCustomerAddressData();
        CustomerProduct customerProduct=generateFakeCustomerProductData();
        List<Problems> problemsList=generateFakeCustomerProblemData();
        CreateJobPayload payload=new CreateJobPayload(MST_SERVICE_LOCATION_ID
                ,MST_PLATFORM_ID
                ,MST_WARRANTY_STATUS_ID,
                MST_OEM_ID,customer,customerAddress,customerProduct,problemsList);
        return payload;

    }
    public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count){

        List<CreateJobPayload> payloadList=new ArrayList<CreateJobPayload>();

        for (int i=1;i<=count;i++) {

            Customer customer = generateFakeCustomerJobData();
            CustomerAddress customerAddress = generateFakeCustomerAddressData();
            CustomerProduct customerProduct = generateFakeCustomerProductData();
            List<Problems> problemsList = generateFakeCustomerProblemData();
            CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID
                    , MST_PLATFORM_ID
                    , MST_WARRANTY_STATUS_ID,
                    MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
            payloadList.add(payload);

        }
        return payloadList.iterator();

    }

    private static Customer generateFakeCustomerJobData() {
        String fname=faker.name().firstName();
        String lname=faker.name().lastName();
        String mobileNumber=faker.numerify("70########");
        String altMobileNumber=faker.numerify("70########");
        String emailId=faker.internet().emailAddress();
        String altEmailId=faker.internet().emailAddress();
        Customer customer=new Customer(fname,lname,mobileNumber,altMobileNumber,emailId,altEmailId);
        System.out.println(customer);
        return customer;
    }

    private static CustomerAddress generateFakeCustomerAddressData() {
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
        return customerAddress;
    }
    private static CustomerProduct generateFakeCustomerProductData() {

        String dop=DateTimeUtil.getTimeWithDaysAgo(10);
        String serial_number=faker.numerify("###############");
        String imei1=faker.numerify("###############");
        String imei2=faker.numerify("###############");
        String popurl=faker.internet().url();
        CustomerProduct customerProduct=new CustomerProduct(dop,
                serial_number,
                imei1,imei2
                ,popurl,PRODUCT_ID
                ,MST_MODEL_ID);
        System.out.println(customerProduct);
        return customerProduct;
    }
    private static List<Problems> generateFakeCustomerProblemData() {
        String fakeRemark=faker.lorem().sentence(5);
        int randomIndex=RANDOM.nextInt(validProblemsId.length);
        Problems problems=new Problems(validProblemsId[randomIndex], fakeRemark);
        System.out.println(problems);
        List<Problems> problemsList=new ArrayList<Problems>();
        problemsList.add(problems);
        return problemsList;
    }


}

