package com.trendyol.pages;

import com.trendyol.BaseTest;
import com.trendyol.utils.Methods;

public class FavoritesPage {
    Methods methods = new Methods();

    public void clickAddFavorite() {
        methods.click("add_favorite_btn");
    }

    public void goToFavoritesPage() {
        methods.click("favorites_menu_link");
    }

    public void checkProductInFavorites() {
        methods.assertElementVisible("favorite_product_title");
    }
}
