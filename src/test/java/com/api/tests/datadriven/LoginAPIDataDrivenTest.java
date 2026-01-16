package com.api.tests.datadriven;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginAPIDataDrivenTest
{

    //private static final Logger log = LoggerFactory.getLogger(LoginAPITest.class);

    @Test(description ="Verifying if login api is working for FD user",
            groups  ={"api", "regression", "datadriven"},
    dataProviderClass=com.dataproviders.DataProviderUtils.class,
            dataProvider ="LoginAPIDataProvider")

    public  void loginAPITest(UserBean userBean) {
        System.out.println(System.getProperty("env"));
        UserCredentials usercredentials=new UserCredentials("iamfd","password");












                            given()
                                    .spec(SpecUtil.requestSpec(userBean))
                                    .when()
                                    .post("login")
                                    .then()
                                    .spec((SpecUtil.responseSpec_OK()))
                                    .body("message",equalTo("Success"))
                                    .log().body()
                                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));


    }




}
