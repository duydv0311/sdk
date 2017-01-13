package com.hitapps.sdk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

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
    @SerializedName("installed_at")
    private String install_at;
    @SerializedName("installed_via")
    private String install_via;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("ip")
    private String ip;

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstall_at() {
        return install_at;
    }

    public void setInstall_at(String install_at) {
        this.install_at = install_at;
    }

    public String getInstall_via() {
        return install_via;
    }

    public void setInstall_via(String install_via) {
        this.install_via = install_via;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
