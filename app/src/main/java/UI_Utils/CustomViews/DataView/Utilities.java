package UI_Utils.CustomViews.DataView;

import android.graphics.Color;
import android.widget.TextView;

import Utility.Colors.ColorAdapter.Content;
import Utility.Colors.Components;

public class Utilities {
    public static void applyCustomizations(String key,
                                           String content,
                                           int position,
                                           TextView view,
                                           Content colorAdapter,
                                           UI_Utils.CustomViews.DataView.ParamsAdapter.Content paramsAdapter){

        Components components = colorAdapter.fetchComponents(key, content, position);

        if (components != null) {
            view.setTextColor(Color.parseColor(components.getText_color()));
            view.setBackgroundColor(Color.parseColor(components.getBg_color()));
        }

        view.setLayoutParams(paramsAdapter.getChildParams(key));
        paramsAdapter.applyModifications(key, view);

    }
}
