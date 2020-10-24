package com.w2a.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static WebDriver driver;
    protected static FileInputStream fis;
    protected static Properties config = new Properties();
    protected static Properties OR = new Properties();
    protected static Logger log = Logger.getLogger("devpinoyLogger");
    protected Date date;

    @BeforeSuite
    public void setUp() {
        date = new Date();
        System.setProperty("current.date", date.toString().replace(":", "_").replace(" ", "_"));
        PropertyConfigurator.configure("./src/test/java/resources/properties/log4j.properties");
        log.debug("Test");
        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config file is loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/properties/OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file is loaded !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\java\\resources\\executables\\geckodriver.exe");
                driver = new FirefoxDriver();
                log.debug("Firefox Launched !!!");

            } else if (config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\resources\\executables\\chromedriver.exe");
                driver = new ChromeDriver();
                log.debug("Chrome Launched !!!");
            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("Navigate to : " + config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            driver.manage()
                    .timeouts()
                    .implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        }

    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        log.debug("Test execution is completed !!!");
    }


}
