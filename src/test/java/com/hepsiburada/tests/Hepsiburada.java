package com.hepsiburada.tests;

import com.hepsiburada.pages.HomePage;
import com.hepsiburada.pages.ProductPage;
import com.hepsiburada.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Hepsiburada extends BaseTest {
    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Test
    @Order(1)
    public void kullanıcı_hepsiburada_sitesine_gider_ve_cerezleri_kabul_eder() {
        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = "Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assertions.assertEquals(expectedTitle, actualTitle);
        homePage.acceptCookies();
    }

    @Test()
    @Order(2)
    public void kullanıcı_hesabına_giriş_yapar_ve_hesabının_açık_olduğunu_doğrular() {
        homePage.loginFirst.click();
        homePage.login.click();
        homePage.userLogin();
        homePage.userLoginAssert();
    }

    @Test()
    @Order(3)
    public void kullanıcı_satın_almak_istediği_ürün_için_arama_yapar() {
        homePage.productSearch("Koşu Bandı");
    }

    @Test()
    @Order(4)
    public void kullanıcı_arama_sonucunda_ekrana_gelen_ürün_listesinden_bir_ürünü_secer() {
        homePage.productSelect("Hattrick").click();
    }

    @Test()
    @Order(5)
    public void kullanıcı_secili_ürünün_satıcılarını_listeler() throws InterruptedException {
        homePage.switchToWindow("kosu bandi");
        productPage.scrollToElement(productPage.allSellerList);
        productPage.allSellerList.click();
    }

    @Test()
    @Order(6)
    public void kullanıcı_listedeki_iki_farklı_satıcıdan_ürünü_sepete_ekler() throws InterruptedException {
        try {
            productPage.firstBasket.click();
            String firstMerchant = productPage.firstMerchant.getText();
        } catch (ElementClickInterceptedException e) {
            System.out.println("ilk ürün seçildi");
        }
        wait.until(ExpectedConditions.visibilityOf(productPage.notificationClose));
        productPage.notificationClose.click();
        try {
            productPage.secondBasket.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("ikinci ürün seçildi");
        }
        wait.until(ExpectedConditions.visibilityOf(productPage.goToBasket));
        productPage.goToBasket.click();
    }

    @Test()
    @Order(7)
    public void kullanıcı_seçilen_ürünlerin_sepete_eklendiğini_doğrular() throws InterruptedException {
        String expectedProduct = "Hattrick Smart Energy Yeni Nesil Koşu Bandı 2.25 Hp";
        Assertions.assertEquals("Spor Dünyası", productPage.firstMerchantInBasket.getText());
        Assertions.assertEquals("Mutfak Dünyası", productPage.secondMerchantInBasket.getText());
        Assertions.assertEquals(expectedProduct, productPage.firstProductInBasket.getText());
        Assertions.assertEquals(expectedProduct, productPage.secondProductInBasket.getText());
        productPage.erase.click();
        productPage = new ProductPage();
        try {
            productPage.eraseButton.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("product not erased");
            productPage.erase.click();
        }
        Driver.closeDriver();
    }

    @Test
    @Order(8)
    public void ikinci_kullanıcı_hepsiburada_sitesine_gider_ve_cerezleri_kabul_eder() {
        Driver.getDriver().get("https://www.hepsiburada.com/");
        homePage = new HomePage();
        homePage.acceptCookies();
        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = "Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test()
    @Order(9)
    public void ikinci_kullanıcı_satın_almak_istediği_ürün_için_arama_yapar() {
        homePage.productSearch("Kondisyon bisikleti");
    }

    @Test()
    @Order(10)
    public void ikinci_kullanıcı_arama_sonucunda_ekrana_gelen_ürün_listesinden_bir_ürün_secer() {
        homePage.productSelect("Dynamic").click();
    }

    @Test()
    @Order(11)
    public void ikinci_kullanıcı_secili_ürünün_satıcılarını_listeler() throws InterruptedException {
        homePage.switchToWindow("Dynamic");
        productPage = new ProductPage();
        productPage.scrollToElement(productPage.allSellerList);
        productPage.allSellerList.click();
    }

    @Test()
    @Order(12)
    public void ikinci_kullanıcı_listedeki_iki_farklı_satıcıdan_ürünü_sepete_ekler() throws InterruptedException {
        try {
            productPage.firstBasket.click();
            String firstMerchant = productPage.firstMerchant.getText();
        } catch (ElementClickInterceptedException e) {
            System.out.println("ilk ürün seçildi");
        }
        wait.until(ExpectedConditions.visibilityOf(productPage.notificationClose));
        productPage.notificationClose.click();
        try {
            productPage.secondBasket.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("ikinci ürün seçildi");
        }
        wait.until(ExpectedConditions.visibilityOf(productPage.goToBasket));
        productPage.goToBasket.click();
    }

    @Test()
    @Order(13)
    public void ikinci_kullanıcı_seçilen_ürünlerin_sepete_eklendiğini_doğrular() throws InterruptedException {
        String expectedProduct = "Dynamic R102N Eliptik Bisiklet Orbitroller Orbitrack";
        Assertions.assertEquals("Seçkinpazarlama", productPage.firstMerchantInBasket.getText());
        Assertions.assertEquals("Hepsiburada", productPage.secondMerchantInBasket.getText());
        Assertions.assertEquals(expectedProduct, productPage.firstProductInBasket.getText());
        Assertions.assertEquals(expectedProduct, productPage.secondProductInBasket.getText());

    }
}