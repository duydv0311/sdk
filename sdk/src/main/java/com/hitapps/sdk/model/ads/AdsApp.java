package com.hitapps.sdk.model.ads;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Duy on 1/11/2017.
 */

public class AdsApp implements Parcelable {
    @SerializedName("title")
    private String title_app;
    @SerializedName("desc")
    private String description_app;
    @SerializedName("logo")
    private String logo_app;
    @SerializedName("banner")
    private String banner_app;
    @SerializedName("link")
    private String link;
    @SerializedName("package")
    private String package_name;
    @SerializedName("display_type")
    private String display_type;

    public AdsApp() {
    }

    protected AdsApp(Parcel in) {
        title_app = in.readString();
        description_app = in.readString();
        logo_app = in.readString();
        banner_app = in.readString();
        link = in.readString();
        package_name = in.readString();
        display_type = in.readString();
    }

    public static final Creator<AdsApp> CREATOR = new Creator<AdsApp>() {
        @Override
        public AdsApp createFromParcel(Parcel in) {
            return new AdsApp(in);
        }

        @Override
        public AdsApp[] newArray(int size) {
            return new AdsApp[size];
        }
    };

    public String getTitle_app() {
        return title_app;
    }

    public void setTitle_app(String title_app) {
        this.title_app = title_app;
    }

    public String getDescription_app() {
        return description_app;
    }

    public void setDescription_app(String description_app) {
        this.description_app = description_app;
    }

    public String getLogo_app() {
        return logo_app;
    }

    public void setLogo_app(String logo_app) {
        this.logo_app = logo_app;
    }

    public String getBanner_app() {
        return banner_app;
    }

    public void setBanner_app(String banner_app) {
        this.banner_app = banner_app;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title_app);
        parcel.writeString(description_app);
        parcel.writeString(logo_app);
        parcel.writeString(banner_app);
        parcel.writeString(link);
        parcel.writeString(package_name);
        parcel.writeString(display_type);
    }
}
