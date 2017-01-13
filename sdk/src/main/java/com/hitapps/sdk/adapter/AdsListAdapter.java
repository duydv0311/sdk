package com.hitapps.sdk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hitapps.sdk.R;
import com.hitapps.sdk.helper.AppHelper;
import com.hitapps.sdk.model.ads.AdsApp;


import java.util.List;

/**
 * Created by LeAnh on 12/27/2016.
 */

public class AdsListAdapter extends RecyclerView.Adapter<AdsListAdapter.AdsListViewHolder> {

    private List<AdsApp> arrAdsObjects;
    private Context context;

    public AdsListAdapter(List<AdsApp> arrAdsObjects, Context context) {
        this.arrAdsObjects = arrAdsObjects;
        this.context = context;
    }

    @Override
    public AdsListViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new AdsListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdsListViewHolder holder, int position) {
        final AdsApp adsObject = arrAdsObjects.get(position);
        Glide.with(context).load(adsObject.getLogo_app())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgLogo);
        if (adsObject.getBanner_app() != null) {
            holder.imgBanner.setVisibility(View.VISIBLE);
            Glide.with(context).load(adsObject.getBanner_app())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imgBanner);
            holder.imgBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppHelper.openMarket(context,adsObject.getPackage_name());
                }
            });
        }
        holder.tvTitle.setText(adsObject.getTitle_app());
        holder.tvDescription.setText(adsObject.getDescription_app());
        holder.btnAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppHelper.openMarket(context,adsObject.getPackage_name());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAdsObjects.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class AdsListViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imgLogo;
        protected ImageView imgBanner;
        protected TextView tvTitle;
        protected TextView tvDescription;
        protected Button btnAds;

        public AdsListViewHolder(View itemView) {
            super(itemView);
            imgBanner = (ImageView) itemView.findViewById(R.id.item_cardview_img_banner);
            imgLogo = (ImageView) itemView.findViewById(R.id.item_cardview_img_logo);
            tvTitle = (TextView) itemView.findViewById(R.id.item_cardview_tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.item_cardview_tv_description);
            btnAds = (Button) itemView.findViewById(R.id.item_cardview_btnAds);
        }
    }
}