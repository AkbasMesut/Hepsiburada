package com.hepsiburada.tests;

import com.hepsiburada.pages.HomePage;
import com.hepsiburada.pages.ProductPage;
import com.hepsiburada.utilities.Driver;
import com.hepsiburada.utilities.TestLogger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestLogger.class)

public class BaseTest {

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
    String firstProduct = "kosu bandı";
    String firstProductBrand = "Hattrick";
    String secondProduct = "Kondisyon bisikleti";
    String secondProductBrand = "Dynamic";

    @BeforeAll
    public void setUp() {
        Driver.getDriver().get("https://www.hepsiburada.com/");
    }

    @AfterAll
    public void teardown() {
           Driver.getDriver().quit();
    }


}
