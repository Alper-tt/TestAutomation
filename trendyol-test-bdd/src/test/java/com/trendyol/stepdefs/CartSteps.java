package com.trendyol.stepdefs;

import com.trendyol.pages.CartPage;
import io.cucumber.java.en.*;


public class CartSteps {
    CartPage cartPage = new CartPage();
    String cartUrl = "https://www.trendyol.com/sepet-oneri/trendyol-plus/aylik-trendyol-plus-p-927952329?boutiqueId=682261&merchantId=109";


    @And("sepete ekle butonuna tıklar")
    public void sepeteEkleButonunaTiklar() {
        cartPage.addCart();
    }

    @Then("kullanıcı sepet sayfasına yönlendirilir")
    public void kullaniciSepetSayfasinaYonlendirilir() {
        cartPage.waitForUrlToBe(cartUrl,10);
    }
}