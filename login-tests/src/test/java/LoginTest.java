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

import java.time.Duration;

public class LoginTest {

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
        Thread.sleep(2000);
        baseTest.selectGender(driver,"kadın"); // dinamik cinsiyet seçim yapısı
        System.out.println("Cinsiyet seçildi");
        // Giriş yap'a tıkla
        // Cinsiyet seçimi sonrası onboarding pop-up varsa kapat
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            WebElement onboardingKapatBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.i-cancel")));
            onboardingKapatBtn.click();
            System.out.println("Onboarding popup kapatıldı.");
        } catch (Exception e) {
            System.out.println("Onboarding popup görünmedi.");
        }

        Thread.sleep(2000);
        WebElement girisYapButonu = driver.findElement(By.cssSelector("div.link.account-user"));
        girisYapButonu.click();
        System.out.println("Giris yap butonu tıklandı");



        // Yanlış bilgilerle giriş yap
        System.out.println("Yanlış bilgilerle giriş yapılıyor");
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("login-email"));
        WebElement password = driver.findElement(By.id("login-password-input"));
        email.sendKeys("alper@test.com");
        System.out.println("Mail yazıldı");
        password.sendKeys("87654321");
        System.out.println("Şifre yazıldı");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Giriş yap butonu tıklandı");

        Thread.sleep(2000);
        // Hata mesajını kontrol et
        try {
            WebElement errorText = driver.findElement(By.id("error-box-wrapper"));
            Assertions.assertTrue(errorText.isDisplayed());
            System.out.println("Başarılı, hata mesajı gösterildi");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Thread.sleep(2000);

        driver.navigate().refresh();

        System.out.println("Sayfa yenilendi");

        Thread.sleep(1000);

        System.out.println("Doğru bilgilerle giriş yapılıyor");
        email = driver.findElement(By.id("login-email"));
        password = driver.findElement(By.id("login-password-input"));
        email.sendKeys("alpertopraktepe46@gmail.com");
        System.out.println("Mail yazıldı");
        password.sendKeys("Alper1903bjk");
        System.out.println("Şifre yazıldı");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println("Giriş yap butonu tıklandı");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://www.trendyol.com/butik/liste/1/kadin?from=login"));

        Assertions.assertEquals("https://www.trendyol.com/butik/liste/1/kadin?from=login", driver.getCurrentUrl(), "Yönlendirme başarıyla yapıldı...");
        Thread.sleep(2000);

    }

    @AfterEach
    public void tearDown() {
        driver.quit(); // Tarayıcıyı kapat
    }
}
