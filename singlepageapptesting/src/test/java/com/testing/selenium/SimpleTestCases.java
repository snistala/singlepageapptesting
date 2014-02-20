package com.testing.selenium;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import static org.junit.Assert.assertTrue;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleTestCases extends GenericTest {
    private static final String HREF = "href";
    @BeforeClass
    public static void setUp(){
        Global.getDriver();
        globalDriver.get(Constants.TESTING_URL);
    }

    @Test
    public void test_01_paragraph() {
        WebElement paragraphElement = globalDriver.findElement(By.cssSelector("#sample-paragraph"));
        String paragraphText = paragraphElement.getText();
        assertEquals("This is a paragraph.", paragraphText);
    }

    @Test
    public void test_03_span() {
        WebElement spanElement = globalDriver.findElement(By.cssSelector("#sample-span"));
        String spanText = spanElement.getText();
        assertEquals("This is a sample span.", spanText);
    }

    @Test
    public void test_02_title() {
        assertEquals("Testing new Html", globalDriver.getTitle());
    }
    @Test
    public void test_03_span1() {
        WebElement spanElement = globalDriver.findElement(By.cssSelector("#sample1-span"));
        String spanText = spanElement.getText();
        assertEquals("This is a complicated span",spanText);
    }

    @Test
    public void test_04_formSubmit() {
        WebElement form = globalDriver.findElement(By.cssSelector("#sample-form"));
        WebElement username = form.findElement(By.name("sample-username"));
        username.sendKeys("sampleusername");
        WebElement password = form.findElement(By.name("sample-password"));
        password.sendKeys("samplepassword");
        form.submit();
        Global.wait(2);
        assertEquals( "http://localhost:11111/submitform.html?sample-username=sampleusername&sample-password=samplepassword",
            globalDriver.getCurrentUrl());
    }

    @Test
    public void test_05_formError() {
        globalDriver.get(Constants.TESTING_URL);
        WebElement form = globalDriver.findElement(By.cssSelector("#sample-form2"));
        form.submit();
        Global.wait(2);
        assertEquals("http://localhost:11111/submiterror.html?sample-username=&sample-password=",
                globalDriver.getCurrentUrl());
    }

    @Test
    public void test_06_formAjaxFailure() {
        globalDriver.get(Constants.TESTING_URL);
        WebElement form = globalDriver.findElement(By.cssSelector("#sample-ajax-form"));
        form.submit();
        Global.wait(2);
        List<WebElement> errors = globalDriver.findElements(By.cssSelector("#notification-container .error"));
        assertTrue(errors.size() != 0);
    }

    @Test
    public void test_07_formAjaxSuccess() {
        WebElement form = globalDriver.findElement(By.cssSelector("#sample-ajax-form"));
        WebElement username = form.findElement(By.name("sample-username"));
        username.sendKeys("sampleusername");
        WebElement password = form.findElement(By.name("sample-password"));
        password.sendKeys("samplepassword");
        form.submit();
        Global.wait(3);
        List<WebElement> errors = globalDriver.findElements(By.cssSelector("#notification-container .error"));
        assertTrue(errors.size() == 0);
        List<WebElement> successes = globalDriver.findElements(By.cssSelector("#notification-container .success"));
        assertTrue(successes.size() != 0);
        assertTrue(globalDriver.findElement(By.cssSelector("#success-content")).isDisplayed());
    }

    @Test
    public void test_08_jsonTest() {
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject)parser.parse(Constants.JSON_DATA);
        WebElement jsonDiv = globalDriver.findElement(By.cssSelector("#json-content"));
        String firstName = jsonDiv.findElement(By.name("first-name")).getAttribute("value");
        String lastName = jsonDiv.findElement(By.name("last-name")).getAttribute("value");
        String age = jsonDiv.findElement(By.name("age")).getAttribute("value");
        assertEquals(obj.get("firstName").getAsString(), firstName);
        assertEquals(obj.get("lastName").getAsString(), lastName);
        assertEquals(obj.get("age").getAsString(), age);
    }

//    @Test
//    public void test_09_randomWebpageTest() {
//        globalDriver.get("https://developer.mozilla.org/en-US/Add-ons/Code_snippets/QuerySelector");
//        String menuText = globalDriver.findElements(By.cssSelector("#Subnav .accordion"))
//                .get(0).findElements(By.xpath(".//*")).get(3).getText();
//        assertEquals("RESTARTLESS EXTENSIONS", menuText);
//    }

    @AfterClass
    public static void tearDown(){
        closeDriver();
    }
}