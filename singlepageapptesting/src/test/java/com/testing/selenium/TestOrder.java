package com.testing.selenium;

import com.testing.selenium.pagetests.ElementsTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        SimpleTestCases.class,
        ElementsTestCases.class
})
public class TestOrder {


}