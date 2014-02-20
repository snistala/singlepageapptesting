package com.testing.selenium.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class PhantomDriver extends Driver {
  public PhantomDriver () {
    //pass
  }
  public WebDriver getDriver() {
    String path = Global.properties.getProperty("phantomLinux");
    System.setProperty("phantomjs.binary.path", "resources/phantomjs/bin/phantomjs");
    driver = new PhantomJSDriver();
    return this.driver;
  }
}
