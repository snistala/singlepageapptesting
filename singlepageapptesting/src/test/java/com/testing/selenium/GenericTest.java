package com.testing.selenium;


import static com.testing.selenium.drivers.Global.globalDriver;

public class GenericTest {

    public static void closeDriver() {
        if (globalDriver != null) {
            globalDriver.quit();
            globalDriver = null;
        }
    }

}