package com.trendyol.pages;

import com.trendyol.utils.Methods;

public class CartPage {
    Methods methods = new Methods();

    public void addCart() {
        methods.click("add_cart_btn");
    }

    public void waitForUrlToBe(String expectedUrl, int timeoutInSeconds) {
        methods.waitForUrlToBe(expectedUrl, timeoutInSeconds);
    }

}
