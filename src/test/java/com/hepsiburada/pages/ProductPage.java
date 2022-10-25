package com.hepsiburada.pages;

import com.hepsiburada.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends HomePage{

    @FindBy(id = "merchantTabTrigger")
    public WebElement allSellerList;

    @FindBy(xpath = "(//button[@class='add-to-basket button'])[1]")
    public WebElement firstBasket;

    @FindBy(xpath = "(//button[@class='add-to-basket button'])[2]")
    public WebElement secondBasket;

    @FindBy(xpath = "//a[@class='checkoutui-Modal-2iZXl']")
    public WebElement notificationClose;

    @FindBy(xpath = "//*[text()='Sepete git']")
    public WebElement goToBasket;

    @FindBy(xpath = "(//div[@class='product_name_3Lh3t'])[1]")
    public WebElement firstProduct;

    @FindBy(xpath = "(//div[@class='product_name_3Lh3t'])[2]")
    public WebElement secondProduct;

    public void scrollDown(){
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN,Keys.PAGE_DOWN).perform();
    }


}
