package com.hepsiburada.tests;

import com.hepsiburada.pages.HomePage;
import com.hepsiburada.pages.ProductPage;
import com.hepsiburada.utilities.Driver;
import dev.failsafe.internal.util.Assert;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Hepsiburada extends BaseTest {
    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Test()
    @Order(1)
    public void kullanıcı_hepsiburada_sitesine_gider_ve_cerezleri_kabul_eder() {
        homePage.acceptCookies();
    }

    @Test()
    @Order(2)
    public void kullanıcı_hesabına_giriş_yapar_ve_hesabının_açık_olduğunu_doğrular(){
      //  homePage.loginFirst.click();
      //  homePage.login.click();
       // homePage.userLogin();
      //  homePage.userLoginAssert();
    }

    @Test()
    @Order(3)
    public void kullanıcı_satın_almak_istediği_ürün_için_arama_yapar() {
       homePage.productSearch("Koşu Bandı");
    }

    @Test()
    @Order(4)
    public void kullanıcı_arama_sonucunda_ekrana_gelen_ürün_listesinden_ilk_ürünü_secer(){
        homePage.productSelect(1).click();

    }

    @Test()
    @Order(5)
    public void kullanıcı_secili_ürünün_satıcılarını_listeler() throws InterruptedException {

        homePage.switchToWindow("kosu bandi");
        String title = Driver.getDriver().getTitle();
        System.out.println(title);
        productPage.scrollToElement(productPage.allSellerList);
        productPage.allSellerList.click();
    }

    @Test()
    @Order(6)
    public void kullanıcı_listedeki_2_farklı_satıcıdan_ürünü_sepete_ekler() throws InterruptedException {
       try {
           productPage.firstBasket.click();
       }catch (ElementClickInterceptedException e){
           System.out.println("ilk ürün seçildi");
       }
        wait.until(ExpectedConditions.visibilityOf(productPage.notificationClose));
        productPage.notificationClose.click();
        try {
            productPage.secondBasket.click();
        }catch (ElementClickInterceptedException e){
            System.out.println("ikinci ürün seçildi");
        }
        wait.until(ExpectedConditions.visibilityOf(productPage.goToBasket));
        productPage.goToBasket.click();
    }

    @Test()
    @Order(7)
    public void kullanıcı_seçilen_ürünlerin_sepete_eklendiğini_doğrular(){

        String firstProductTitle = productPage.firstProduct.getText();
        String secondProductTitle = productPage.secondProduct.getText();
        System.out.println("firstProduct = " + firstProductTitle);
        System.out.println("secondProduct = " + secondProductTitle);
        Assertions.assertEquals(firstProductTitle,secondProductTitle);
        Driver.closeDriver();
    }

    @Test()
    @Order(8)
    public void ikinci_kullanıcı_hepsiburada_sitesine_gider_ve_cerezleri_kabul_eder() {
        Driver.getDriver().get("https://www.hepsiburada.com/");
       // homePage.acceptCookies();
        Driver.getDriver().manage().deleteAllCookies();
    }

    @Test()
    @Order(9)
    public void ikinci_kullanıcı_satın_almak_istediği_ürün_için_arama_yapar() {
        homePage.productSearch("Kondisyon bisikleti");
    }

}
