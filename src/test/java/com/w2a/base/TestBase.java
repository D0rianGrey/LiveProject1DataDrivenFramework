package com.w2a.base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    private static WebDriver driver;
    private static Properties config = new Properties();
    private static Properties OR = new Properties();
    private static FileInputStream fis;

    @BeforeSuite
    public void setUp(){
        if (driver==null){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/resources/properties/OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @AfterSuite
    public void tearDown(){

    }


}
