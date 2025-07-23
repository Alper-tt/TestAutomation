package com.trendyol.stepdefs;

import com.trendyol.BaseTest;
import com.trendyol.utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FollowSteps {

    WebDriver driver = DriverManager.getDriver();
    BaseTest baseTest = new BaseTest();

    @And("takip et butonuna tıklar")
    public void takipEtButonunaTiklar() {
        baseTest.waitUntilClickable(driver,
                By.cssSelector("button.follow-and-win-button-follow-and-win-button")).click();
    }

    @Then("mağaza başarıyla takip edilmiş olmalı")
    public void takip_edildigi_kontrol_edilir() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean takipEdildi = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector("button.follow-and-win-button-follow-and-win-button"),
                "Takip Ediliyor"
        ));
        Assertions.assertTrue(takipEdildi, "Takip işlemi başarıyla gerçekleştirildi.");
    }
}
