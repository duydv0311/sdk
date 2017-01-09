package com.hitapps.sdk.model;

import java.io.Serializable;

/**
 * Created by Duy on 12/29/2016.
 */

public class AppAttribute implements Serializable {
    private int idApp;
    private String packageName;
    private String isInstall;
    private String isShowing;

    public AppAttribute(String packageName, String isInstall, String isShowing) {
        this.packageName = packageName;
        this.isInstall = isInstall;
        this.isShowing = isShowing;
    }

    public AppAttribute(int idApp, String packageName, String isInstall, String isShowing) {
        this.idApp = idApp;
        this.packageName = packageName;
        this.isInstall = isInstall;
        this.isShowing = isShowing;
    }

    public AppAttribute() {
    }

    public int getIdApp() {
        return idApp;
    }

    public void setIdApp(int idApp) {
        this.idApp = idApp;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIsInstall() {
        return isInstall;
    }

    public void setIsInstall(String isInstall) {
        this.isInstall = isInstall;
    }

    public String getIsShowing() {
        return isShowing;
    }

    public void setIsShowing(String isShowing) {
        this.isShowing = isShowing;
    }
}
