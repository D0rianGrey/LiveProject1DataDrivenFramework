package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void LoginAsBankManager() throws InterruptedException {
        log.debug("Inside of Login Test");
        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        Thread.sleep(3000);
        log.debug("Login successfully executed !!!");
    }
}
