package com.trendyol.stepdefs;

import com.trendyol.pages.FavoritesPage;
import io.cucumber.java.en.*;

import java.time.Duration;

public class FavoriSteps {

    FavoritesPage favoritesPage = new FavoritesPage();

    @And("favoriye ekle butonuna tıklar")
    public void favoriyeEklenir() {
        favoritesPage.clickAddFavorite();
    }

    @Then("kullanıcı favoriler sayfasına gider")
    public void favorilerSayfasinaGidilir() {
        favoritesPage.goToFavoritesPage();
    }

    @And("eklenen ürün favorilerde görünmelidir")
    public void urunFavorilerdeGorunurOlmali() {
        favoritesPage.checkProductInFavorites();
    }
}
