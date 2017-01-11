package com.hitapps.sdk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Duy on 1/11/2017.
 */

public class UserInfor implements Serializable {
    @SerializedName("device_id")
    private String android_id;
    @SerializedName("imei")
    private String imei;
    @SerializedName("reg_id")
    private String reg_id;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_no")
    private String phone_no;
    @SerializedName("carrier")
    private String carrier;
    @SerializedName("country")
    private String country_name;
    @SerializedName("package")
    private String package_name;
    @SerializedName("version")
    private String version;
    @SerializedName("install_at")
    private String install_at;
    @SerializedName("install_via")
    private String install_via;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("ip")
    private String ip;


}
