package com.snaptest.testing.pages;

import com.snaptest.testing.AppElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePageObject {

    private static final AppElement userNameField = new AppElement(
            "User Name Field",
            By.id("com.snapchat.android:id/username_or_email_field"),
            true);
    private static final AppElement passwordField = new AppElement(
            "Password Field",
            By.id("com.snapchat.android:id/password_field"),
            true);
    private static final AppElement loginButton = new AppElement(
            "Login Button",
            By.xpath("//*[@resource-id='com.snapchat.android:id/nav_button']"),
            true);
    private static final AppElement turnOnCameraButton = new AppElement(
            "Turn On Camera Button",
            By.id("com.snapchat.android:id/turn_on_button"),
            false);

    public LoginPage(AndroidDriver driver){
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed(){
        waitToBeVisible(userNameField, 20);
        return allRequiredElementDisplayed();
    }

    public void tapLoginButton() {
        waitToBeVisible(loginButton);
       tap(loginButton);
    }

    public void tapTurnOnCameraButton() {
        waitToBeVisible(turnOnCameraButton);
        tap(turnOnCameraButton);
    }

    public void setUsernameFiels(String text) {
        waitToBeVisible(userNameField);
        enterText(userNameField, text);
    }

    public void setPasswordFiels(String text) {
        waitToBeVisible(passwordField);
        enterText(passwordField, text);
    }
}
