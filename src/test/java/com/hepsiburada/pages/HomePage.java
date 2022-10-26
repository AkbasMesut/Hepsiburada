package com.hepsiburada.pages;

import com.hepsiburada.utilities.Driver;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


import javax.swing.*;

public class HomePage extends BasePage  {

    @FindBy(xpath = "//span[@title='Giriş Yap']")
    public WebElement loginFirst;

    @FindBy(id = "login")
    public WebElement login;

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement cookies;

    @FindBy(name = "username")
    public WebElement mailPlaceHolder;

    @FindBy(name = "password")
    public WebElement passwordPlaceHolder;

    @FindBy(xpath = "//span[@class='sf-OldMyAccount-sS_G2sunmDtZl9Tld5PR']")
    public WebElement userAccount;

    @FindBy(xpath = "//div[@aria-haspopup='listbox']")
    public WebElement searchBar;



    public void acceptCookies(){
        if (cookies.isDisplayed()){
            cookies.click();
        }
    }

    public void userLogin(){
        mailPlaceHolder.sendKeys("mutluates135@gmail.com", Keys.ENTER);
        passwordPlaceHolder.sendKeys("Kartal.9898",Keys.ENTER);
    }

    public void userLoginAssert(){
        String expectedAccount = "mutlu ates";
        String actualAccount = userAccount.getText();
        System.out.println("Actual account name = " + actualAccount);
        Assertions.assertEquals(expectedAccount,actualAccount);
    }

    public void productSearch(String product){
        searchBar.click();
        searchBar.sendKeys(product,Keys.ENTER);
    }

    public void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().contains(targetTitle)) {
                return;
            }
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement productSelect(int num){
        WebElement selectedProduct = Driver.getDriver().findElement(By.xpath("(//h3[@data-test-id='product-card-name'])["+num+"]"));
        return selectedProduct;
    }

    public WebElement switchFrame(WebElement element){
     return (WebElement) Driver.getDriver().switchTo().frame(element);
    }

}
