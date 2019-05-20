package com.snaptest.testing.pages;

import com.snaptest.testing.AppElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainPage extends BasePageObject {

    private static final AppElement addFriendItem = new AppElement(
            "Add Friend Item",
            By.id("com.snapchat.android:id/hova_header_add_friend_icon"),
            false);
    private static final AppElement searchField = new AppElement(
            "SearchField",
            By.id("com.snapchat.android:id/neon_header_title"),
            true);
    private static final AppElement friendItem = new AppElement(
            "Friend Item",
            By.id("com.snapchat.android:id/item"),
            false);
    private static final AppElement friendNameField = new AppElement(
            "Friend Name Field",
            By.id("com.snapchat.android:id/name"),
            false);
    private static final AppElement friendSecondaryField = new AppElement(
            "Friend Secondary ield",
            By.id("com.snapchat.android:id/secondary_text"),
            false);
    private static final AppElement addFriendButton = new AppElement(
            "Add Friend Button",
            By.id("com.snapchat.android:id/add_button"),
            false);
    private static final AppElement addFriendButtonText = new AppElement(
            "Add Friend Button Text",
            By.id("com.snapchat.android:id/loading_spinner_button_text"),
            false);
    private static final AppElement avatarIcon = new AppElement(
            "Avatar Icon",
            By.id("com.snapchat.android:id/avatar_icon"),
            false);
    private final String addedFrienfText = "Snap";


    public MainPage(AndroidDriver driver){
        super(driver);
    }

    @Override
    public boolean pageIsDisplayed(){
        waitToBeVisible(addFriendItem);
        return allRequiredElementDisplayed();
    }

    public void tapAddAFriend(){
        waitToBeVisible(addFriendButton, 20);
        tap(addFriendButton);
    }

    public void searchAFriend(String text){
        waitToBeVisible(searchField, 20);
        tap(searchField);
        enterText(searchField, text);
    }

    public boolean isFriendPresent(String text){
        return isElementPresent(friendItem) && isFieldContainsText(text);
    }

    private boolean isFieldContainsText(String text){
        return getInputValue(friendNameField).toLowerCase().contains(text) || getInputValue(friendSecondaryField).toLowerCase().contains(text);
    }

    public void tapAddAFriendItem(){
        waitToBeVisible(addFriendItem, 20);
        tap(addFriendItem);
    }

    public void tapAvatarItem(){
        waitToBeVisible(avatarIcon, 20);
        tap(avatarIcon);
    }

    public boolean isFriendAdded(){
      return isTextPresent(addFriendButtonText, addedFrienfText);
    }
}

