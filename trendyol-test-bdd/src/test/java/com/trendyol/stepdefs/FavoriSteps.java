package com.trendyol.stepdefs;

import com.trendyol.BaseTest;
import com.trendyol.utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavoriSteps {

    WebDriver driver = DriverManager.getDriver();
    BaseTest baseTest = new BaseTest();

    @And("favoriye ekle butonuna tıklar")
    public void favoriyeEklenir() throws InterruptedException {
        Thread.sleep(2000);
        baseTest.waitUntilClickable(driver, By.cssSelector("button[data-testid='favorite-button']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                By.cssSelector("button[data-testid='favorite-button'] i"),
                "class",
                "i-heart-orange"
        ));
    }

    @Then("kullanıcı favoriler sayfasına gider")
    public void favorilerSayfasinaGidilir() {
        driver.get("https://www.trendyol.com/hesabim/favoriler");
    }

    @And("eklenen ürün favorilerde görünmelidir")
    public void urunFavorilerdeGorunurOlmali() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement favoriUrun = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'product-card')]//span[contains(text(),'Aylık Trendyol Plus')]")
        ));
        Assertions.assertTrue(favoriUrun.isDisplayed());
    }
}
