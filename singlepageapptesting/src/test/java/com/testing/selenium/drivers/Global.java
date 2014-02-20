package com.testing.selenium.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Global {
  public static WebDriver globalDriver;
  public static Properties properties;
  public static boolean read = readProperties();
  public static final String ROOT_URL = Global.properties.getProperty("rootUrl");
  public static final String ACCOUNT_URL = Global.properties.getProperty("accountUrl");
  public static final String SUPPORT_URL = Global.properties.getProperty("supportUrl");
  public static final String TERMS_URL = Global.properties.getProperty("termsUrl");
  public static final String CONTACT_URL = Global.properties.getProperty("contactUrl");;
  public static  final OS OS_VERSION = detectOs();
  public static final boolean RUN_SUITE = Boolean.parseBoolean(Global.properties.getProperty("runSuite"));
  
  static {
    chooseDriver();
  }
  public static void assignUrls() {

  }
  public static void wait(int sec) {
    try {
      Thread.sleep(sec * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static boolean readProperties() {
    properties = new Properties();
    try {
      String pfile = "resources/properties/Global.properties";
      FileInputStream in = new FileInputStream(pfile);
      properties.load(in);
      in.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return true;
  }
  public static void getDriver() {
    if (globalDriver == null) {
      chooseDriver();
    }
  }
  public static OS detectOs() {
    String osVersion = System.getProperty("os.name").toLowerCase();
    if (osVersion.indexOf("mac") >= 0) {
      return OS.MAC_OS;
    } else if (osVersion.indexOf("nux") >= 0) {
      return OS.LINUX;
    } else {
      System.err.println("Operating system not supported");
      System.exit(1);
      return null;
    }
      
  }
  public static void chooseDriver() {
    String driverType = Global.properties.getProperty("driverType");
    if (driverType.equals("Chrome")) {
      globalDriver = new Chrome().getDriver();
    } else if (driverType.equals("Phantom")) {
      globalDriver = new PhantomDriver().getDriver();
    } else if (driverType.equals("Firefox")) {
      globalDriver = new FirefoxDriver();
    }
  }

  public enum OS {
    MAC_OS, LINUX
  }
   
}
