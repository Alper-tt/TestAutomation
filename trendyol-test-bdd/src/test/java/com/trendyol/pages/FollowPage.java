package com.trendyol.pages;

import com.trendyol.utils.Methods;

public class FollowPage {

    Methods methods = new Methods();

    public void clickFollowButton() throws InterruptedException{
        Thread.sleep(2000);
        methods.click("follow_btn");
    }

    public void checkFollowStatus()  {
        methods.waitForTextInElement("follow_btn", "Takip Ediliyor", 10);
    }
}
