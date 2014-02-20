package com.testing.selenium.pagetests;

import com.testing.selenium.Constants;
import com.testing.selenium.GenericTest;
import com.testing.selenium.drivers.Global;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.testing.selenium.drivers.Global.globalDriver;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElementsTestCases extends GenericTest {
    private static final String HREF = "href";

    @BeforeClass
    public static void setUp(){
        Global.getDriver();
        globalDriver.get(Constants.TESTING_URL);
    }

    @Test
    public void test_01_table() {
        List<WebElement> tableRowElements = globalDriver.findElements(By.cssSelector(".sample-row"));

        for (int i = 0; i <tableRowElements.size(); i++) {
            String rowText = String.format("Row%d", i+1);
            List<WebElement> tableCellElements = tableRowElements.get(i).findElements(By.cssSelector("td"));
            assertEquals(String.format("%sItem1", rowText), tableCellElements.get(0).getText());
            assertEquals(String.format("%sItem2", rowText), tableCellElements.get(1).getText());
            assertEquals(String.format("%sItem3", rowText), tableCellElements.get(2).getText());
        }
    }

    @Test
    public void test_02_backgroundColor() {
        WebElement bodyElement = globalDriver.findElement(By.cssSelector("body"));
        String backgroundColor = bodyElement.getCssValue("background-color");
        assertEquals("rgba(104, 166, 220, 1)", backgroundColor);
    }

//    @Test
//    public void test_03_fillInputBox() {
//        WebElement formElement = globalDriver.findElement(By.cssSelector("#sample-form"));
//        WebElement inputElement = formElement.findElement(By.xpath("//*[@name='sample-text']"));
//        inputElement.sendKeys("Test input");
//        assertEquals("Test input", inputElement.getAttribute("value"));
//    }

    @Test
    public void test_05_findElement(){
        WebElement paragraphElement = globalDriver.findElement(By.cssSelector("#sample1-paragraph"));
        String paragraphText = paragraphElement.getText();
        assertEquals("This is not paragraph",paragraphText);
     }
    @Test
    public void test_06_findElement(){
        WebElement paragraphElement = globalDriver.findElement(By.cssSelector("#old"));
        String paragraphText = paragraphElement.getText();
        assertEquals("This is a new page", paragraphText);
    }
    @Test
    public void test_07_findElement(){
        WebElement paragraphElement = globalDriver.findElement(By.xpath("//*[@id='old']"));
        String paragraphText = paragraphElement.getText();
        assertEquals("This is a new page",paragraphText);
    }


    @AfterClass
    public static void tearDown() {
        closeDriver();
    }
}
