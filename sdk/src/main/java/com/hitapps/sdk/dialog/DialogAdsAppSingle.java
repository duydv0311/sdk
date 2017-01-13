package com.hitapps.sdk.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.hitapps.sdk.R;
import com.hitapps.sdk.model.ads.AdsApp;

/**
 * Created by Duy on 1/13/2017.
 */

public class DialogAdsAppSingle extends AppCompatActivity {

    protected AdsApp adsAppDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ads_single);
        adsAppDialog = getIntent().getExtras().getParcelable("dialog");
        Log.e("sssssss", "onCreate: " + adsAppDialog.getPackage_name());
    }
}
