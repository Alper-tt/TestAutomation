package com.trendyol.favorite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.trendyol.BaseTest;

import java.time.Duration;

public class FavoriteTest {

    WebDriver driver;
    BaseTest baseTest;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup(); // Driver'ı indirir
        driver = new ChromeDriver();             // Chrome başlatır
        System.out.println("Chrome driver başlatıldı");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        baseTest = new BaseTest();
    }

    @Test
    public void testIncorrectThenCorrectLogin() throws InterruptedException {
        driver.get("https://www.trendyol.com");
        System.out.println("Trendyol.com ziyaret edildi");
        baseTest.selectGender(driver,"kadın"); // dinamik cinsiyet seçim yapısı
        System.out.println("Cinsiyet seçildi");
        // Giriş yap'a tıkla

        WebElement girisYapButonu = baseTest.waitUntilClickable(driver,By.cssSelector("div.link.account-user"));
        girisYapButonu.click();
        System.out.println("Giris yap butonu tıklandı");



        System.out.println("Doğru bilgilerle giriş yapılıyor");
        WebElement email = baseTest.waitUntilVisible(driver, By.id("login-email"));
        WebElement password = baseTest.waitUntilVisible(driver, By.id("login-password-input"));
        email.sendKeys("curat.orc.v.n@gmail.com");
        System.out.println("Mail yazıldı");
        password.sendKeys("ETMv3ynRHJv80Gt");
        System.out.println("Şifre yazıldı");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Giriş yap butonu tıklandı");

        baseTest.waitUntilVisible(driver, By.xpath("//div[contains(@class,'account-user')]//p[contains(text(),'Hesabım')]"));

        driver.get("https://www.trendyol.com/trendyol-plus/aylik-trendyol-plus-p-927952329?boutiqueId=682261&merchantId=109");
        WebElement favButonu = baseTest.waitUntilClickable(driver, By.cssSelector("button[data-testid='favorite-button']"));
        favButonu.click();

        driver.get("https://www.trendyol.com/hesabim/favoriler");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement favoriUrun = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'product-card')]//span[contains(text(),'Aylık Trendyol Plus')]")
        ));
        Assertions.assertTrue(favoriUrun.isDisplayed(), "Ürün favorilerde mevcut");
        System.out.println("✅ Ürün favorilerde bulundu");

    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Tarayıcıyı kapat
    }
}
