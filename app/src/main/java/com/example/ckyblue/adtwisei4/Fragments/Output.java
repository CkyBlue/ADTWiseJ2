package com.example.ckyblue.adtwisei4.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ckyblue.adtwisei4.R;

import UI_Utils.CustomViews.LogView;
import Utility.Logs.Feed;
import Utility.Themes.Defaults;

public class Output extends Fragment {
    String TAG = getClass().getName();

    private View rootView;
    private LogView logView;
    private Feed feed;

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public LogView getLogView() {
        return logView;
    }

    public void setLogView(LogView logView) {
        this.logView = logView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "onCreateView()");
        setRootView(inflater.inflate(R.layout.fragment_output, container, false));

        setLogView(new LogView(getContext()));
        getLogView().setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        getLogView().setFeed(this.feed);

        ((LinearLayout) getRootView().findViewById(R.id.container)).addView(logView);
        initUI();
        return rootView;
    }

    @Override
    public void onResume() {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "onResume()");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    public void initUI() {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "initUI()");

        getLogView().setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
        getLogView().setTextColor(Color.parseColor(
                Defaults.defaultLightText().getHexARGB()
        ));
        getLogView().setPadding(5, 5, 5, 5);


        getRootView().findViewById(R.id.outerHorizontalScroll).setBackgroundColor(Color.parseColor(
                Defaults.defaultBgColorDark().getHexARGB()
        ));
    }

    public void setFeed(Feed outputFeed) {
        this.feed = outputFeed;
        if (this.logView != null) {
            this.logView.setFeed(outputFeed);
        }
    }
}
