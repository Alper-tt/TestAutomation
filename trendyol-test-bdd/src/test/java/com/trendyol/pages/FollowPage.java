package com.trendyol.pages;

import com.trendyol.utils.Methods;

public class FollowPage {

    Methods methods = new Methods();

    public void clickFollowButton(){
        methods.click("follow_btn");
    }

    public void checkFollowStatus() throws InterruptedException {
        Thread.sleep(2000);
        methods.waitForTextInElement("follow_btn", "Takip Ediliyor", 10);
    }
}
