package com.trendyol.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.hooks.Hooks;
import com.trendyol.model.ElementModel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.time.Duration;
import java.util.List;

public class Methods {

    private List<ElementModel> elements;
    private WebDriver driver;
    private WebDriverWait wait;

    public Methods() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("elements.json");
            elements = mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("elements.json okunamadı!", e);
        }

        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By getBy(String key) {
        ElementModel element = elements.stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Key bulunamadı: " + key));

        return switch (element.getType()) {
            case "id" -> By.id(element.getValue());
            case "css" -> By.cssSelector(element.getValue());
            case "xpath" -> By.xpath(element.getValue());
            case "name" -> By.name(element.getValue());
            default -> throw new RuntimeException("Tanımsız locator tipi: " + element.getType());
        };
    }

    public WebElement findElement(String key) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
    }

    public void click(String key) {
        wait.until(ExpectedConditions.elementToBeClickable(getBy(key))).click();
    }

    public void sendKeys(String key, String value) {
        WebElement el = findElement(key);
        el.clear();
        el.sendKeys(value);
    }

    public String getText(String key) {
        return findElement(key).getText();
    }

    public boolean isDisplayed(String key) {
        try {
            return findElement(key).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void waitForUrlToBe(String expectedUrl, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }


    public void waitForElement(String key) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(key)));
    }

    public void assertElementVisible(String key) {
        if (!isDisplayed(key)) {
            throw new AssertionError("Element görünür değil: " + key);
        }
    }
}
