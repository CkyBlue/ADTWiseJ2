package UI_Utils.CustomViews.DataViews;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.TextView;

import UI_Utils.CustomViews.DataViews.ParamsAdapter.Themes;
import Utility.Colors.ColorAdapter.Content;
import Utility.Colors.Components;
import Utility.Port;
import Utility.Themes.Defaults;

public class Utilities {
    public static void applyCustomizations(Port port, String key, String content, int position,
                                           TextView textView, @NonNull Content colorAdapter,
                                           @NonNull UI_Utils.CustomViews.DataViews.ParamsAdapter.Content paramsAdapter) {

        Components components = colorAdapter.fetchComponents(port, key, content, position);

        if (textView == null){
            return;
        }

        if (components != null) {
            textView.setTextColor(Color.parseColor(components.getText_color()));
            textView.setBackgroundColor(Color.parseColor(components.getBg_color()));
        }

        textView.setLayoutParams(paramsAdapter.getChildParams(port, key));
        paramsAdapter.applyModifications(port, key, textView);

    }

    public static TextView createView(Context context, Port port, String key, String content,
                                      int position, @NonNull Content colorAdapter,
                                      @NonNull UI_Utils.CustomViews.DataViews.ParamsAdapter.Content paramsAdapter) {
        TextView tv = new TextView(context);

        applyCustomizations(port, key, content, position, tv, colorAdapter, paramsAdapter);

        tv.setGravity(Gravity.CENTER);
        tv.setText(content);

        return tv;
    }

    @NonNull
    public static Utility.Colors.ColorAdapter.Content getColorAdapter(UI_Utils.CustomViews.DataViews.Customizations.Content
                                                                              customizations) {
        if (customizations != null) {
            if (customizations.getColorAdapter() != null) {
                return customizations.getColorAdapter();
            }
        }

        return Defaults.colorAdapter;
    }

    @NonNull
    public static UI_Utils.CustomViews.DataViews.ParamsAdapter.Content getParamsAdapter(UI_Utils.CustomViews.DataViews.Customizations.Content
                                                                                               customizations) {
        if (customizations != null) {
            if (customizations.getParamsContent() != null) {
                return customizations.getParamsContent();
            }
        }

        return Themes.Default;
    }
}
