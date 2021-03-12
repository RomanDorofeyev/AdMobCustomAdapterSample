package com.example.admobcustomadapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

public class MyBannerAdapter implements CustomEventBanner, MediationAdapter {

    @Override
    public void requestBannerAd(Context context,
                                final CustomEventBannerListener customEventBannerListener,
                                String serverParameters,
                                AdSize adSize,
                                MediationAdRequest mediationAdRequest,
                                Bundle bundle) {

        customEventBannerListener.onAdLoaded(
                simulateRequestAdFromThirdParty(context));

    }

    private RelativeLayout simulateRequestAdFromThirdParty(Context context) {
        RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350);
        RelativeLayout adView = new RelativeLayout(context);
        adView.setLayoutParams(adViewLayoutParams);
        adView.setBackgroundColor(Color.LTGRAY);

        TextView textView = new TextView(context);
        textView.setText("My Demo Ad");
        textView.setTextSize(30);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(30, 10, 30, 10);
        adView.addView(textView);
        return adView;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
