
package com.testing.selenium.pagetests.NewTests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

/*
 Created by traxaem on 2/18/14.*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewseleniumTests extends GenericTest {
    private static final String HREF = "href";
    @BeforeClass
    public static void setUp(){
        Global.getDriver();
        globalDriver.get(Constants.TESTING_URL);
}
    @Test
    public void test_01_paragraph(){
        WebElement paragraphElement = globalDriver.findElement(By.cssSelector("#One"));
        String paragraphText = paragraphElement.getText();
        assertEquals("This is the first paragraph", paragraphText );
        Global.wait(1);
    }

    @Test
    public void test_02_span(){
        WebElement spanElement = globalDriver.findElement(By.cssSelector("#Two"));
        String spanText = spanElement.getText();
        assertEquals("This is a span",spanText);
        Global.wait(2);
    }
   @Test
    public void test_03_formSubmit(){

        WebElement form1 = globalDriver.findElement(By.cssSelector("#Login-Form"));
        WebElement username = form1.findElement(By.name("username"));
        username.sendKeys("sampleusername");
        WebElement password = form1.findElement(By.name("password"));
        password.sendKeys("samplepassword");
        form1.submit();
        Global.wait(3);
       assertEquals("http://localhost:11111/NewTests.html?username=sampleusername&password=samplepassword",
                globalDriver.getCurrentUrl());
   }

    @Test
    public void test_04_AjaxSuccess(){
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject)parser.parse(Constants.JSON_DATA);
        WebElement JSonDiv = globalDriver.findElement(By.cssSelector("#json-content"));
        String FirstName = JSonDiv.findElement(By.name("first-name")).getAttribute("value");
        String LastName = JSonDiv.findElement(By.name("last-name")).getAttribute("value");
        String Age = JSonDiv.findElement(By.name("age")).getAttribute("value");
        assertEquals(object.get("firstName").getAsString(),FirstName);
        assertEquals(object.get("lastName").getAsString(),LastName);
        assertEquals(object.get("age").getAsString(),Age);

    }
    @Test
    public void  test_05_tableItem(){
       List<WebElement> tableRowElements = globalDriver.findElements(By.cssSelector(".sample-row"));

        for(int i = 0; i<tableRowElements.size(); i++){
            String rowText = String.format("Row%d", i+1);
            List<WebElement> tableCellElements = tableRowElements.get(i).findElements(By.cssSelector("td"));
            assertEquals(String.format("%sItem1",rowText), tableCellElements.get(0).getText());
            assertEquals(String.format("%sItem2",rowText), tableCellElements.get(1).getText());
            assertEquals(String.format("%sItem3",rowText), tableCellElements.get(2).getText());
        }
    }


    @AfterClass
    public static void tearDown(){
        closeDriver();
    }
}

