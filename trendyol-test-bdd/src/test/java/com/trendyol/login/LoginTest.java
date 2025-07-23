package com.trendyol.login;

import com.trendyol.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    BaseTest baseTest;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup(); // Driver'ı indirir
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--load-extension=/Users/alper/downloads/nopecha-extension");
        driver = new ChromeDriver(options);             // Chrome başlatır
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
        // Cinsiyet seçimi sonrası onboarding pop-up varsa kapat
        //try {
        //    WebElement onboardingKapatBtn = baseTest.waitUntilClickable(driver, By.cssSelector("span.i-cancel"));
        //    onboardingKapatBtn.click();
        //    System.out.println("Onboarding popup kapatıldı.");
        //} catch (Exception e) {
        //    System.out.println("Onboarding popup görünmedi.");
        //}

        WebElement girisYapButonu = baseTest.waitUntilClickable(driver,By.cssSelector("div.link.account-user"));
        girisYapButonu.click();
        System.out.println("Giris yap butonu tıklandı");



        // Yanlış bilgilerle giriş yap
        System.out.println("Yanlış bilgilerle giriş yapılıyor");
        WebElement email = baseTest.waitUntilVisible(driver, By.id("login-email"));
        WebElement password = baseTest.waitUntilVisible(driver, By.id("login-password-input"));
        email.sendKeys("alper@test.com");
        System.out.println("Mail yazıldı");
        password.sendKeys("87654321");
        System.out.println("Şifre yazıldı");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Giriş yap butonu tıklandı");

        // Hata mesajını kontrol et
        try {
            WebElement errorText = driver.findElement(By.id("error-box-wrapper"));
            Assertions.assertTrue(errorText.isDisplayed());
            System.out.println("Başarılı, hata mesajı gösterildi");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        driver.navigate().refresh();

        System.out.println("Sayfa yenilendi");


        System.out.println("Doğru bilgilerle giriş yapılıyor");
        email = baseTest.waitUntilVisible(driver, By.id("login-email"));
        password = baseTest.waitUntilVisible(driver, By.id("login-password-input"));
        email.sendKeys("alpertopraktepe46@gmail.com");
        System.out.println("Mail yazıldı");
        password.sendKeys("dogruSifre");
        System.out.println("Şifre yazıldı");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Giriş yap butonu tıklandı");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://www.trendyol.com/butik/liste/1/kadin?from=login"));

        Assertions.assertEquals("https://www.trendyol.com/butik/liste/1/kadin?from=login", driver.getCurrentUrl(), "Yönlendirme başarıyla yapıldı...");

    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Tarayıcıyı kapat
    }
}
