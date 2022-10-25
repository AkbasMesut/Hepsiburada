package com.hepsiburada.tests;

import com.hepsiburada.utilities.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup(){
        Driver.getDriver().get("https://www.hepsiburada.com/");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3);
       // Driver.closeDriver();
    }

}
