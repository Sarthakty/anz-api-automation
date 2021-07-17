package com.anz.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderId {

    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderMethod()
    {
        return new Object[][] { {"1111"}, { "2222" }, { "3333" }, {"4444"}, {"5555"}, {"6666"} };
    }
}
