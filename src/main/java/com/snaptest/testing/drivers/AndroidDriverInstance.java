package com.snaptest.testing.drivers;

import com.snaptest.testing.common.AppiumConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * AndroidDriver initialisation.
 */

public class AndroidDriverInstance {
    protected static AndroidDriver driver;

    public static AndroidDriver getInstance(){
        try {
            driver = new AndroidDriver(new URL(AppiumConfig.getAppiumUrl()), getCapabilities());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static DesiredCapabilities getCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", AppiumConfig.getPlatformName());
        capabilities.setCapability("deviceName", AppiumConfig.getTestDeviceName());
        capabilities.setCapability("platformVersion", AppiumConfig.getPlatformVersion());
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appPackage", AppiumConfig.getPackageName());
        capabilities.setCapability("appActivity", AppiumConfig.getAppActivity());
        capabilities.setCapability("appPath", AppiumConfig.getAppPath());
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("newCommandTimeout", 5000);
        return capabilities;
    }
}
