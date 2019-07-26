package com.example.ckyblue.adtwisei4;

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

import UI_Utils.CustomViews.LogView;
import Utility.Colors.Values;
import Utility.Logs.Feed;
import Utility.Themes.Defaults;

public class LoggerFragment extends OutputFragment {
    @Override
    public void initUI() {
        Logger.log(TAG, "initUI()");

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
