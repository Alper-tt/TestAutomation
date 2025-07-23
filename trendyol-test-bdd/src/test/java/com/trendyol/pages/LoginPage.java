package com.trendyol.pages;

import com.trendyol.utils.Methods;

public class LoginPage {

    Methods methods = new Methods();

    public void addCart(String email) {
        methods.sendKeys("login_email_field", email);
    }

    public void enterPassword(String password) {
        methods.sendKeys("login_pw_field", password);
    }

    public void clickLoginButton() {
        methods.click("submit_btn");
    }

    public boolean isLoginErrorDisplayed() {
        return methods.isDisplayed("login_error_box");
    }

    public boolean isLoginSuccess() {
        return methods.isDisplayed("hesabim_text");
    }
}
