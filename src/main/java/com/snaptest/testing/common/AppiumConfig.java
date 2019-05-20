package com.snaptest.testing.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Class for getting parameters from properties.
 */

public class AppiumConfig {
    private static String appiumUrl;
    private static String testDeviceName;
    private static String packageName;
    private static String platformName;
    private static String platformVersion;
    private static String appActivity;
    private static String appPath;

    public static String getAppPath(){
        if (appPath == null) {
            initProperties();
        }
        String path = (System.getProperty("user.dir") + appPath).replace("\\", "//");

        return path;
    }


    public static String getAppiumUrl(){
        if (appiumUrl == null) {
            initProperties();
        }
        return appiumUrl;
    }

    public static String getTestDeviceName(){
        if (testDeviceName == null) {
            initProperties();
        }
        return testDeviceName;
    }

    public static String getPackageName(){
        if (packageName == null) {
            initProperties();
        }
        return packageName;
    }

    public static String getPlatformName(){
        if (platformName == null) {
            initProperties();
        }

        return platformName;
    }

    public static String getPlatformVersion(){
        if (platformVersion == null) {
            initProperties();
        }

        return platformVersion;
    }

    public static String getAppActivity(){
        if (appActivity == null) {
            initProperties();
        }

        return appActivity;
    }

    private static void initProperties(){
        try {
            File file;
            if ((new File("AppiumConfigLocal.properties").exists())) {
                file = new File("AppiumConfigLocal.properties");
            } else {
                file = new File("AppiumConfig.properties");
            }

            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            platformName = properties.getProperty("platform_name");
            platformVersion = properties.getProperty("platform_version");
            testDeviceName = properties.getProperty("device_name");
            packageName = properties.getProperty("appPackage");
            appActivity = properties.getProperty("appActivity");

            appiumUrl = properties.getProperty("appium_url");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}