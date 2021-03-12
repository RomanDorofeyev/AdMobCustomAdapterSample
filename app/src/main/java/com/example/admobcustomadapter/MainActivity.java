package com.example.admobcustomadapter;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final int AD_POSITION = 12;
    public static final int INITIAL_LIST_SIZE = 30;
    public static final String AD_UNIT_ID = "ca-app-pub-1186601170998955/3882553856";
    public static final String TEST_LOG = "test_log";

    private RecyclerView recyclerView;
    private ArrayList<String> items;
    private AdView adView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        setupRecyclerView();
        initAdView();
        progressBar = findViewById(R.id.progress_bar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> requestAd());
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        final LinearLayoutManager linearLM = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLM);

        items = new ArrayList<>();
        for (int i = 0; i < INITIAL_LIST_SIZE; i++) {
            items.add("Item  " + i);
        }
        RVAdapter adapter = new RVAdapter(items, AD_POSITION, adView);
        recyclerView.setAdapter(adapter);
    }


    private void initAdView() {
        MobileAds.initialize(this, initializationStatus -> {
            adView = new AdView(MainActivity.this);
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            adView.setLayoutParams(params);
            adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
            adView.setAdUnitId(AD_UNIT_ID);
            setupRecyclerView();
        });
    }

    private void requestAd() {
        progressBar.setVisibility(View.VISIBLE);

        adView.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                Log.d(TEST_LOG, "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(TEST_LOG, "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                Log.d(TEST_LOG, "onAdOpened");
            }

            @Override
            public void onAdLoaded() {
                Log.d(TEST_LOG, "onLoaded");
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAdClicked() {
                Log.d(TEST_LOG, "onAdClicked");
            }

            @Override
            public void onAdImpression() {
                Log.d(TEST_LOG, "onAdImpression");
            }
        });

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }
}