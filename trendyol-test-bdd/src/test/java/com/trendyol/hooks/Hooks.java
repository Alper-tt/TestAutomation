package com.trendyol.hooks;

import com.trendyol.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Tarayıcı başlatılıyor...");
        DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        System.out.println("Tarayıcı kapatılıyor...");
        DriverManager.quitDriver();
    }
}
