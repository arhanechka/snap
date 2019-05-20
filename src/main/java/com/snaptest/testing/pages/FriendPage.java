package com.snaptest.testing.pages;

import com.snaptest.testing.AppElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class FriendPage extends BasePageObject {

    private static final AppElement menuView = new AppElement(
            "Menu View",
            By.id("com.snapchat.android:id/profile_header_status_icon"),
            true);
    private static final AppElement alertDialogRemove = new AppElement(
            "Alert Dialog Remove",
            By.id("com.snapchat.android:id/dialog_content"),
            false);
    private static final AppElement removeFriendField = new AppElement(
            "Remove Friend Field",
            By.xpath("//*[@text='Remove Friend']"),
            false);
    private static final AppElement removeFriendButton = new AppElement(
            "Remove Friend Button",
            By.xpath("//*[@text='Remove']"),
            false);
    private static final AppElement addFriendButton = new AppElement(
            "Add Friend Button",
            By.id("com.snapchat.android:id/add_friend_button"),
            false);

    public FriendPage(AndroidDriver driver){
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed(){
        waitToBeVisible(menuView);
        return allRequiredElementDisplayed();
    }

    public void tapMenuView(){
        waitToBeVisible(menuView);
        tap(menuView);
    }

    public void tapRemoveFriendField(){
        waitToBeVisible(removeFriendField);
        tap(removeFriendField);
    }

    public boolean isAlertDialogPresent(){
        return isElementPresent(alertDialogRemove);
    }

    public void tapRemoveFriend(){
        waitToBeVisible(removeFriendButton);
        tap(removeFriendButton);
    }

    public boolean isFriendAdded(){
        return isElementPresent(menuView) && !isElementPresent(addFriendButton);
    }
}
