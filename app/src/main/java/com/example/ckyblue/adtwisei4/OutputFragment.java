package com.example.ckyblue.adtwisei4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import UI_Utils.CustomViews.LogView;
import Utility.Logs.Feed;
import Utility.Themes.Defaults;

public class OutputFragment extends Fragment {
    String TAG = getClass().getName();

    View rootView;
    LogView logView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.log(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.log(TAG, "onCreateView()");
        rootView = inflater.inflate(R.layout.fragment_output, container, false);
        initUI();
        return rootView;
    }

    @Override
    public void onResume() {
        Logger.log(TAG, "onResume()");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.log(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    public void initUI() {
        Logger.log(TAG, "initUI()");

        this.logView = new LogView(getContext());
        this.logView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        this.logView.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
        this.logView.setTextColor(Color.parseColor(
                Defaults.defaultLightText().getHexARGB()
        ));
        this.logView.setPadding(5, 5, 5, 5);

        ((LinearLayout) rootView.findViewById(R.id.container)).addView(logView);

        rootView.findViewById(R.id.outerHorizontalScroll).setBackgroundColor(Color.parseColor(
                Defaults.defaultBgColorDark().getHexARGB()
        ));
    }

    public void setFeed(Feed outputFeed) {
        this.logView.setFeed(outputFeed);
    }
}
