package com.snaptest.testing;

import org.openqa.selenium.By;

/**
 * A container for information about elements in the app.
 */
public class AppElement {
    public final String name;
    public final By androidLoc;
    public final boolean required;

    public AppElement(String name, By androidLoc, boolean required){
        this.name = name;
        this.androidLoc = androidLoc;
        this.required = required;
    }

    public By get(String deviceType){
        By loc;
        if (deviceType.equals( "Android")) {
            loc = this.androidLoc;
        } else {
            loc = null;
        }
        return loc;
    }
}
