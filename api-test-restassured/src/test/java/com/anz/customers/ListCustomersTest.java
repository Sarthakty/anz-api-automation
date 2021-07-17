package com.anz.customers;

import com.anz.dataprovider.DataProviderId;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class ListCustomersTest {

    public static final String BASE_URL = "http://localhost:4547/Blog.Api/";
    public static final String CUSTOMER_ID = "data.customerID";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";


    @Test(description = "Test to print all the customer list", priority = 1)
    public void displayAllCustomersDetailTest() {
        RestAssured.given()
                .when()
                .get(BASE_URL + "Customers")
                .prettyPrint();
    }

    @Test(description = "Test to fetch particular customer details and validate the same", priority = 2)
    public void fetchParticularCustomersEmailTest() {
        RestAssured.given()
                .when()
                .get(BASE_URL + "1111/CustomerView")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(CUSTOMER_ID, equalTo("1111"))
                .body(FIRST_NAME, equalTo("testerAFirst"))
                .body(LAST_NAME, equalTo("testerALast"))
                .body(EMAIL, equalTo("testerA@abc.com"));
    }

    @Test(description = "Verify customer details for all valid customers", priority = 3, dataProvider = "data-provider", dataProviderClass = DataProviderId.class)
    public void testMethod(String data) {
        System.out.println("Data is: " + data);
        RestAssured.given()
                .when()
                .get(BASE_URL + data + "/CustomerView")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(CUSTOMER_ID, equalTo(data));
    }

    @Test(description = "Validate error massage for invalid customer", priority = 4)
    public void fetchParticularCustomersEmail() {
        RestAssured.given()
                .when()
                .get(BASE_URL + "7777/CustomerView")
                .then()
                .body("message", equalTo("Customer details not found."));
    }

}
