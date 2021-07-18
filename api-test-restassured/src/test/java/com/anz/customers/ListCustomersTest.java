package com.anz.customers;

import com.anz.dataprovider.CustomerId;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class ListCustomersTest {

    public static final String BASE_URL = "http://localhost:4547/Blog.Api/";
    public static final String CUSTOMER_ID = "data.customerID";
    public static final String FIRST_NAME = "data.first_name";
    public static final String LAST_NAME = "data.last_name";
    public static final String EMAIL = "data.email";


    @Test(description = "Test to print all the customer list", priority = 1)
    public void displayAllCustomersDetailTest() {
        RestAssured.given()
                .when()
                .get(BASE_URL + "Customers")
                .prettyPrint();
    }

    @Test(description = "Test to fetch particular customer details and validate the same", priority = 2)
    public void verifySpecificCustomerDetailsTest() {
        RestAssured.given()
                .when()
                .get(BASE_URL + "3333/CustomerView")
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
                .get(BASE_URL + data + "/CustomerView")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(CUSTOMER_ID, equalTo(data));
    }

    @Test(description = "Validate error massage for invalid customer", priority = 4)
    public void verifyInvalidCustomerErrorMessageTest() {
        RestAssured.given()
                .when()
                .get(BASE_URL + "7777/CustomerView")
                .then()
                .body("message", equalTo("Customer details not found."));
    }

}
