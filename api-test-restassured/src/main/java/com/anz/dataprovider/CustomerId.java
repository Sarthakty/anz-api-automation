package com.anz.dataprovider;

import org.testng.annotations.DataProvider;

public class CustomerId {

    @DataProvider(name = "customers-id")
    public static Object[][] getValidCustomersId()
    {
        return new Object[][] { {"1111"}, { "2222" }, { "3333" }, {"4444"}, {"5555"}, {"6666"} };
    }
}
