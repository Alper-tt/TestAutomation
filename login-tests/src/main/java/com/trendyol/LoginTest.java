package com.trendyol;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup(); // Driver'ı indirir
        driver = new ChromeDriver();             // Chrome başlatır
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testIncorrectLogin() {
        driver.get("https://www.trendyol.com");

        // Giriş yap'a tıkla
        WebElement girisYapButonu = driver.findElement(By.cssSelector("div.account-user > span"));
        girisYapButonu.click();

        // Yanlış bilgilerle giriş yap
        WebElement email = driver.findElement(By.id("login-email"));
        WebElement password = driver.findElement(By.id("login-password"));
        email.sendKeys("alper@test.com");
        password.sendKeys("87654321");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Hata mesajını kontrol et
        WebElement errorText = driver.findElement(By.cssSelector(".error-message"));
        Assertions.assertTrue(errorText.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Tarayıcıyı kapat
    }
}
