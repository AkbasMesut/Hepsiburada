package com.hepsiburada.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }

    private static WebDriver driver;

    //  private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();


    public static WebDriver getDriver() {

        if (driver == null) {

            // We read our browserType from configuration.properties.
            // this way, we can control which browser is opened from outside our code, from configuration.properties
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                   // options.addArguments("--incognito");
                    options.addArguments("--start-maximized");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--acceptInsecureCerts");
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-extensions");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    FirefoxOptions option = new FirefoxOptions();
                    option.addArguments("--incognito");
                    option.addArguments("--start-maximized");
                    option.addArguments("--ignore-certificate-errors");
                    option.addArguments("--allow-insecure-localhost");
                    option.addArguments("--acceptInsecureCerts");
                    option.addArguments("--disable-blink-features=AutomationControlled");
                    option.addArguments("--disable-extensions");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(option);
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
