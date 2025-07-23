package com.trendyol.stepdefs;

import com.trendyol.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @When("kullanıcı hatalı bilgilerle giriş yapar")
    public void kullaniciHataliGirisYapar() {
        loginPage.addCart("hatali@eposta.com");
        loginPage.enterPassword("yanlisSifre");
        loginPage.clickLoginButton();
    }

    @Then("hata mesajı görünmelidir")
    public void hataMesajiGorunmeli() {
        assert loginPage.isLoginErrorDisplayed();
    }

    @When("kullanıcı doğru bilgilerle giriş yapar")
    public void kullaniciDogruGirisYapar() {
        loginPage.addCart("alpertopraktepe46@gmail.com");
        loginPage.enterPassword("dogruSifre");
        loginPage.clickLoginButton();
    }

    @Then("Hesabım menüsü görünmelidir")
    public void hesabimGorunmeli() {
        assert loginPage.isLoginSuccess();
    }
}
