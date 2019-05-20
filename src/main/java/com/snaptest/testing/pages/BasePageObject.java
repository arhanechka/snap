package com.snaptest.testing.pages;

import com.google.common.base.Function;
import com.snaptest.testing.AppElement;
import com.snaptest.testing.common.AppiumConfig;
import com.snaptest.testing.common.Data;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Class with base methods for managing elements.
 */

public abstract class BasePageObject {

    public AndroidDriver driver;
    protected Logger log;
    private Dimension winSize;
    protected static final AppElement alertDialogRightBtn = new AppElement(
            "Alert Dialog, OK Button",
            By.id("com.android.packageinstaller:id/permission_allow_button"),
            false);

    public BasePageObject(AndroidDriver driver){
        this.driver = driver;
        this.winSize = this.driver.manage().window().getSize();
        if (log == null) {
            log = Logger.getLogger(this.getClass().getName());
        }
    }

    public BasePageObject(BasePageObject page){
        this(page.driver);
        log = Logger.getLogger(page.getClass().getName());
    }

    public abstract boolean pageIsDisplayed();

    public void waitToBeVisible(final AppElement element, int timeout){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(this.driver)
                    .withTimeout(timeout, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .ignoreAll(Arrays.asList(ElementNotVisibleException.class, NoSuchElementException.class, StaleElementReferenceException.class, WebDriverException.class));
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver input){
                    return input.findElement(element.androidLoc);
                }
            });
        } catch (TimeoutException e) {
            log.info("Element" + element.name + "is not wisible");
        }
    }

    public void waitToBeVisible(final AppElement element){
        waitToBeVisible(element, 10);
    }

    protected boolean allRequiredElementDisplayed(){
        return this.getMissingRequiredElements(getElements()).size() == 0;
    }

    public List<AppElement> getElements(){
        List<AppElement> elements = new ArrayList<AppElement>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType().getSimpleName().equals("AppElement")) {
                try {
                    field.setAccessible(true);
                    AppElement req = (AppElement) field.get(AppElement.class);
                    if (req.required)
                        elements.add((AppElement) field.get(AppElement.class));
                    field.setAccessible(false);
                } catch (IllegalAccessException ignored) {
                    System.out.println(ignored.toString());
                }
            }
        }
        return elements;
    }

    public ArrayList<AppElement> getMissingRequiredElements(List<AppElement> requiredElements){
        ArrayList<AppElement> elements = new ArrayList<AppElement>(requiredElements);
        for (AppElement ele : requiredElements) {
            if (this.isElementPresent(ele)) {
                elements.remove(ele);
            } else {
                log.info("Missed element: " + ele.name);
            }
        }
        return elements;
    }

    public boolean isElementPresent(AppElement element){
        boolean elementFound;
        try {
            this.waitToBeVisible(element, 1);
            this.find(element.androidLoc);
            elementFound = true;
        } catch (Exception e) {
            elementFound = false;
        }
        return elementFound;
    }

    public WebElement find(By element){
        return this.driver.findElement(element);
    }

    public void tap(AppElement element){
        log.info("Tapping element '" + element.name + "'...");
        this.find(element.androidLoc).click();
    }

    public void enterText(AppElement element, String text){
        log.info("Entering text '" + text + "' to element '" + element.name + "'...");
        this.find(element.androidLoc).clear();
        if (AppiumConfig.getPlatformName().equals(Data.ANDROID)) {
            this.tap(element);
            this.find(element.androidLoc).sendKeys(text);
        } else {
            this.find(element.androidLoc).sendKeys(text);
        }
    }

    public void waitToBeClickable(By element, int timeout){
        WebDriverWait wait = new WebDriverWait(this.driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public String getText(AppElement element){
        WebElement text = this.find(element.androidLoc);
        return text.getText();
    }

    public void hideKeyboard(){
        try {
            this.driver.hideKeyboard();
        } catch (Exception e) {

        }
    }

    public String getInputValue(WebElement element){
        String value;

        if (AppiumConfig.getPlatformName().equals(Data.ANDROID)) {
            value = element.getText();
        } else {
            value = element.getAttribute("value");
        }
        return value;
    }

    public boolean isTextPresent(AppElement element, String text){
        boolean isFound;
        try {
            isFound = (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d){
                    return getInputValue(element).equals(text);
                }
            });
        }
        catch (Exception e){
            isFound = false;
        }
        return isFound;
    }

    public String getInputValue(AppElement input){
        try {
            return getInputValue(find(input.androidLoc));
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public void pressDeviceBack(){
        this.driver.navigate().back();
    }

    public void handleAlert(int count){
        try {
            int numAlerts = 0;
            while (numAlerts < count) {
                this.waitToBeClickable(alertDialogRightBtn.androidLoc, 20);
                this.tap(alertDialogRightBtn);
                numAlerts++;
            }
        } catch (TimeoutException ignored) {

        }
    }
}