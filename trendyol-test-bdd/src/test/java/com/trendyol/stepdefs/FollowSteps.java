package com.trendyol.stepdefs;

import com.trendyol.BaseTest;
import com.trendyol.pages.FollowPage;
import com.trendyol.utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FollowSteps {


    FollowPage followPage = new FollowPage();

    @And("takip et butonuna tıklar")
    public void takipEtButonunaTiklar() {
        followPage.clickFollowButton();
    }

    @Then("mağaza başarıyla takip edilmiş olmalı")
    public void takipDurumuKontrolEdilir() throws InterruptedException {
        followPage.checkFollowStatus();
    }
}
