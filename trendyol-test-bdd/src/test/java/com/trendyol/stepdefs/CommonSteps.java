package com.trendyol.stepdefs;

import com.trendyol.BaseTest;
import com.trendyol.utils.DriverManager;
import com.trendyol.utils.Methods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CommonSteps {
    WebDriver driver = DriverManager.getDriver();
    BaseTest baseTest = new BaseTest();

    String urunURL = "https://www.trendyol.com/trendyol-plus/aylik-trendyol-plus-p-927952329?boutiqueId=682261&merchantId=109";

    @Given("kullanıcı trendyol sitesini ziyaret eder")
    public void kullaniciTrendyolSitesiniZiyaretEder() {
        driver.get("https://www.trendyol.com");
    }

    @And("cinsiyet olarak {string} seçer")
    public void cinsiyetOlarakSecer(String gender) {
        baseTest.selectGender(driver, gender);
    }

    @And("giriş yap ekranına gidilir")
    public void girisYapEkraninaGidilir() {
        baseTest.waitUntilClickable(driver, By.cssSelector("div.link.account-user")).click();

    }

    @And("kullanıcı giriş yapar")
    public void girisYapilir() {
        baseTest.waitUntilVisible(driver, By.id("login-email")).sendKeys("alpertopraktepe46@gmail.com");
        baseTest.waitUntilVisible(driver, By.id("login-password-input")).sendKeys("dogruSifre");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        baseTest.waitUntilVisible(driver, By.xpath("//div[contains(@class,'account-user')]//p[contains(text(),'Hesabım')]"));
    }

    @When("ürün detay sayfasına gider")
    public void urunDetaySayfasinaGider() {
        driver.get(urunURL);
        new Methods().removeOverlayByJS();
    }
}
