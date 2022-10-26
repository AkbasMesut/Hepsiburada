package com.hepsiburada.tests;

import com.hepsiburada.utilities.Driver;
import com.hepsiburada.utilities.TestLogger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestLogger.class)

public class BaseTest {

    @BeforeAll
    public void setup(){
        Driver.getDriver().get("https://www.hepsiburada.com/");
    }

    @AfterAll
    public void tearDown() throws InterruptedException {
        if (null != Driver.getDriver()) {
            Driver.getDriver().quit();
        }
    }

}
