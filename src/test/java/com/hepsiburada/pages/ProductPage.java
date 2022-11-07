package com.hepsiburada.pages;

import com.hepsiburada.utilities.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class ProductPage extends HomePage{

    @FindBy(id = "merchantTabTrigger")
    public WebElement allSellerList;

    @FindBy(xpath = "(//button[@class='add-to-basket button'])[1]")
    public WebElement firstBasket;

    @FindBy(xpath = "(//button[@class='add-to-basket button'])[2]")
    public WebElement secondBasket;

    @FindBy(xpath = "//a[contains(@class,'checkoutui-Modal')]")
    public WebElement notificationClose;

    @FindBy(xpath = "//*[text()='Sepete git']")
    public WebElement goToBasket;

    @FindBy(xpath = "(//div[@class='product_name_3Lh3t'])[1]")
    public WebElement firstProduct;

    @FindBy(xpath = "(//div[@class='product_name_3Lh3t'])[2]")
    public WebElement secondProduct;

    @FindBy(xpath = "(//span[@data-bind='text: merchantName'])[1]")
    public WebElement firstMerchant;

    @FindBy(xpath = "(//span[@data-bind='text: merchantName'])[2]")
    public WebElement secondMerchant;

    @FindBy(xpath = "//span[@class='merchantLink_2Ii8s']/a")
    public List<WebElement> allMerchantsInBasket;

    @FindBy(xpath = "(//div[@class='product_name_3Lh3t']/a)[1]")
    public WebElement firstProductInBasket;

    @FindBy(xpath = "(//div[@class='product_name_3Lh3t']/a)[2]")
    public WebElement secondProductInBasket;

    @FindBy(xpath = "//a[@aria-label='Ürünü Kaldır']")
    public WebElement erase;

    @FindBy(xpath = "//a[@class='delete_product_3DFC0']")
    public WebElement eraseButton;



    public boolean assertMerchant(String name){
        boolean merchantInBasket = false;
        for (WebElement merchant : allMerchantsInBasket) {
            if (merchant.getText().equalsIgnoreCase(name)){
                merchantInBasket = true;
            }else {
                merchantInBasket = false;
            }
        }
      return   merchantInBasket;
    }

    public void assertFirstProduct(String name){
         Assertions.assertTrue(firstProduct.getText().equalsIgnoreCase(name));
    }

    public void assertSecontProduct(String name){
        Assertions.assertTrue(secondProduct.getText().equalsIgnoreCase(name));
    }

    public void scrollDown(){
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN,Keys.PAGE_DOWN).perform();
    }


}
