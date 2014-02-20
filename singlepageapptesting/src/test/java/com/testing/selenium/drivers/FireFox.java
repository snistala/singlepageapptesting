package com.testing.selenium.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFox extends Driver {
  public FireFox() {

  }
  public WebDriver getDriver() {
    if (driver == null) {
      driver = new FirefoxDriver();
    }
    return driver;
  }
}
