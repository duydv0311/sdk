package com.hitapps.sdk.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.hitapps.sdk.R;
import com.hitapps.sdk.adapter.AdsListAdapter;
import com.hitapps.sdk.model.ads.AdsApp;

import java.util.ArrayList;

/**
 * Created by Duy on 1/13/2017.
 */

public class DialogAdsAppList extends AppCompatActivity {

    protected RecyclerView rvAdsApp;
    protected AdsListAdapter adsListAdapter;
    protected ArrayList<AdsApp> appArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_ads_list);
        rvAdsApp = (RecyclerView) findViewById(R.id.activity_ads_list_rcv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DialogAdsAppList.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAdsApp.setLayoutManager(layoutManager);
        appArrayList = getIntent().getExtras().getParcelableArrayList("list");
        adsListAdapter = new AdsListAdapter(appArrayList, DialogAdsAppList.this);
        rvAdsApp.setAdapter(adsListAdapter);
    }
}
