package com.hitapps.sdk.model.ads;

import com.google.gson.annotations.SerializedName;
import com.hitapps.sdk.model.UserInfor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Duy on 1/11/2017.
 */

public class AdsNoti implements Serializable {
    @SerializedName("app")
    private ArrayList<AdsApp> appArrayList;
    @SerializedName("title_noti")
    private String title_noti;
    @SerializedName("desc_noti")
    private String desc_noti;
    @SerializedName("logo_noti")
    private String logo_noti;
    @SerializedName("banner_noti")
    private String banner_noti;
    @SerializedName("display_type")
    private String display_type;

    public AdsNoti() {
    }

    public ArrayList<AdsApp> getAppArrayList() {
        return appArrayList;
    }

    public void setAppArrayList(ArrayList<AdsApp> appArrayList) {
        this.appArrayList = appArrayList;
    }

    public String getTitle_noti() {
        return title_noti;
    }

    public void setTitle_noti(String title_noti) {
        this.title_noti = title_noti;
    }

    public String getDesc_noti() {
        return desc_noti;
    }

    public void setDesc_noti(String desc_noti) {
        this.desc_noti = desc_noti;
    }

    public String getLogo_noti() {
        return logo_noti;
    }

    public void setLogo_noti(String logo_noti) {
        this.logo_noti = logo_noti;
    }

    public String getBanner_noti() {
        return banner_noti;
    }

    public void setBanner_noti(String banner_noti) {
        this.banner_noti = banner_noti;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }
}
