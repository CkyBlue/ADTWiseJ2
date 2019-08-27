package com.example.ckyblue.adtwisei4.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.ckyblue.adtwisei4.R;

import Utility.Themes.Defaults;

public class Logger extends Output {
    @Override
    public void initUI() {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "initUI()");

        Typeface typeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);
        getLogView().setTypeface(typeface, Typeface.ITALIC);

        getLogView ().setTextColor(Color.parseColor(
                Utility.Colors.Color.gray.createValues().setTintFactor(-.75f).getHexARGB()
        ));
        getLogView().setTextSize(16f);
        getLogView().setLetterSpacing(0.2f);
        getLogView().setPadding(5, 5, 5, 5);


        getRootView().findViewById(R.id.outerHorizontalScroll).setBackgroundColor(Color.parseColor(
                Defaults.defaultBgColorLight().getHexARGB()
        ));
    }
}
