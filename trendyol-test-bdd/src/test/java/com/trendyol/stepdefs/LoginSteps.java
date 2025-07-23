package com.trendyol.stepdefs;

import com.trendyol.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    WebDriver driver;
    BaseTest baseTest = new BaseTest();

    @Given("Trendyol ana sayfasına gidilir")
    public void trendyolAnaSayfasinaGidilir() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com");
    }

    @And("cinsiyet olarak {string} seçer")
    public void kadinCinsiyetSecimiYapilir() {
        baseTest.selectGender(driver, "kadın");
    }

    @And("giriş yap ekranına gidilir")
    public void girisYapEkraninaGidilir() {
        baseTest.waitUntilClickable(driver, By.cssSelector("div.link.account-user")).click();
    }

    @When("kullanıcı hatalı bilgilerle giriş yapar")
    public void hataliBilgilerleGirisYapilir() {
        baseTest.waitUntilVisible(driver, By.id("login-email")).sendKeys("alper@test.com");
        baseTest.waitUntilVisible(driver, By.id("login-password-input")).sendKeys("87654321");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("hata mesajı görünmelidir")
    public void hataMesajiGorunmelidir() {
        WebElement errorText = baseTest.waitUntilVisible(driver, By.id("error-box-wrapper"));
        Assertions.assertTrue(errorText.isDisplayed());
    }

    @When("kullanıcı doğru bilgilerle giriş yapar")
    public void dogruBilgilerleGirisYapilir() {
        WebElement emailInput = baseTest.waitUntilVisible(driver, By.id("login-email"));
        WebElement passwordInput = baseTest.waitUntilVisible(driver, By.id("login-password-input"));

        driver.navigate().refresh();

        baseTest.waitUntilVisible(driver, By.id("login-email")).sendKeys("alpertopraktepe46@gmail.com");
        baseTest.waitUntilVisible(driver, By.id("login-password-input")).sendKeys("Alper1903bjk*");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("Hesabım menüsü görünmelidir")
    public void hesabimMenusuGorunmelidir() {
        WebElement hesabim = baseTest.waitUntilVisible(driver, By.xpath("//p[text()='Hesabım']"));
        assertTrue(hesabim.isDisplayed());
        driver.quit();
    }
}
