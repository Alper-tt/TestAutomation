package com.trendyol.stepdefs;

import com.trendyol.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CartSteps {

    BaseTest baseTest = new BaseTest();
    WebDriver driver;

    @Given("kullanıcı trendyol sitesini ziyaret eder")
    public void kullaniciTrendyolSitesiniZiyaretEder() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com");
    }

    @And("cinsiyet olarak {string} seçer")
    public void cinsiyetOlarakSecer(String gender) {
        baseTest.selectGender(driver, "kadın");
    }

    @And("giriş yap ekranına gidilir")
    public void girisYapEkraninaGidilir() {
        baseTest.waitUntilClickable(driver, By.cssSelector("div.link.account-user")).click();
    }

    @And("kullanıcı doğru bilgilerle giriş yapar")
    public void dogruBilgilerleGirisYapilir() {
        baseTest.waitUntilVisible(driver, By.id("login-email")).sendKeys("alpertopraktepe46@gmail.com");
        baseTest.waitUntilVisible(driver, By.id("login-password-input")).sendKeys("Alper1903bjk*");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        baseTest.waitUntilVisible(driver, By.xpath("//div[contains(@class,'account-user')]//p[contains(text(),'Hesabım')]"));
    }

    @When("ürün detay sayfasına gider")
    public void urunDetaySayfasinaGider() {
        driver.get("https://www.trendyol.com/trendyol-plus/aylik-trendyol-plus-p-927952329?boutiqueId=682261&merchantId=109");
    }

    @And("sepete ekle butonuna tıklar")
    public void sepeteEkleButonunaTiklar() {
        baseTest.waitUntilClickable(driver, By.cssSelector("button[data-testid='add-to-cart-button']")).click();
    }

    @Then("kullanıcı sepet sayfasına yönlendirilir")
    public void kullaniciSepetSayfasinaYonlendirilir() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.trendyol.com/sepet-oneri/trendyol-plus/aylik-trendyol-plus-p-927952329?boutiqueId=682261&merchantId=109"));
    }

    @And("sepete eklenen ürün görünür olmalı")
    public void urunGorunurOlmali(){
        driver.get("https://www.trendyol.com/sepet");
        WebElement urun = baseTest.waitUntilVisible(driver,
                By.xpath("//p[contains(@class,'pb-item') and contains(text(),'Trendyol Plus Üyelik Paketi')]"));
        Assertions.assertTrue(urun.isDisplayed());
    }
}