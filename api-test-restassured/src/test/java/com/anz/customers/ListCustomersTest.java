package com.anz.customers;

import com.anz.dataprovider.CustomerId;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.anz.constants.ServerConstants.*;
import static org.hamcrest.Matchers.equalTo;


public class ListCustomersTest {


    @Test(description = "Test to print all the customer list", priority = 1)
    public void displayAllCustomersDetailTest() {
        RestAssured.given()
                .when()
                .get(BASE_URI + "/Blog.Api/Customers")
                .prettyPrint();
    }

    @Test(description = "Test to fetch particular customer details and validate the same", priority = 2)
    public void verifySpecificCustomerDetailsTest() {
        RestAssured.given()
                .when()
                .get(BASE_URI + "/Blog.Api/3333/CustomerView")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(CUSTOMER_ID, equalTo("3333"))
                .body(FIRST_NAME, equalTo("testerCFirst"))
                .body(LAST_NAME, equalTo("testerCLast"))
                .body(EMAIL, equalTo("testerC@abc.com"));
    }

    @Test(description = "Verify details of all valid customers", priority = 3, dataProvider = "customers-id", dataProviderClass = CustomerId.class)
    public void verifyAllValidCustomersDetailTest(String data) {
        System.out.println("Data is: " + data);
        RestAssured.given()
                .when()
                .get(BASE_URI + "/Blog.Api/" + data + "/CustomerView")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(CUSTOMER_ID, equalTo(data));
    }

    @Test(description = "Validate error massage for invalid customer", priority = 4)
    public void verifyInvalidCustomerErrorMessageTest() {
        RestAssured.given()
                .when()
                .get(BASE_URI + "/Blog.Api/7777/CustomerView")
                .then()
                .body("message", equalTo("Customer details not found."));
    }

}
