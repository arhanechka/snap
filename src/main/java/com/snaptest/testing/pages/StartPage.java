package com.snaptest.testing.pages;

import com.snaptest.testing.AppElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class StartPage extends BasePageObject {

    private static final AppElement logInTab = new AppElement(
            "Log In tab",
            By.id("com.snapchat.android:id/login_and_signup_page_fragment_login_button"),
            true);

    public StartPage(AndroidDriver driver){
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed(){
        waitToBeVisible(logInTab, 20);
        return allRequiredElementDisplayed();
    }

    public void tapLogin() {
        waitToBeVisible(logInTab);
        tap(logInTab);
    }
}
