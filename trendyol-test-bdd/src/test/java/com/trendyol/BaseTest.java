package com.trendyol;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    public void selectGender(WebDriver driver, String gender) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format("//span[text()='%s']/ancestor::a[contains(@class,'gender-button')]", gender.toUpperCase());
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            btn.click();
        } catch (TimeoutException e) {
            System.out.println(gender + " seçimi popup'ı çıkmadı.");
        }
    }

    public WebElement waitUntilVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
