package UI_Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import UI_Utils.DataViewCustomizations.Content;
import UI_Utils.ParamsAdapter.Themes;

import java.util.List;

import Utility.Colors.Chrome.Components;
import Utility.Themes.Defaults;

public abstract class DataAdapter<T> extends ArrayAdapter<T> {
    private Content customizations;
    private boolean notified = false;

    public DataAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    public Content getCustomizations() {
        return customizations;
    }

    public void setCustomizations(Content customizations) {
        this.customizations = customizations;
    }

    @NonNull
    public Utility.Colors.Chrome.Content getChromeContent() {
        if (getCustomizations() != null) {
            if (getCustomizations().getChromeContent() != null) {
                return getCustomizations().getChromeContent();
            }
        }

        return Defaults.chrome;
    }

    @NonNull
    public UI_Utils.ParamsAdapter.Content getParamsContent() {
        if (getCustomizations() != null) {
            if (getCustomizations().getParamsContent() != null) {
                return getCustomizations().getParamsContent();
            }
        }

        return Themes.Default;
    }

    protected void applyCustomizationsToChildView(String key, String content, int position, TextView view) {
        Utility.Colors.Chrome.Content chromeContent = getChromeContent();
        UI_Utils.ParamsAdapter.Content paramsContent = getParamsContent();

        Components components = getChromeContent().fetchComponents(content, position);
        view.setTextColor(Color.parseColor(components.getText_color()));
        view.setBackgroundColor(Color.parseColor(components.getBg_color()));

        view.setLayoutParams(getParamsContent().getChildParams(key));
        getParamsContent().applyModifications(key, view);
    }

    protected TextView createView(String key, String content, int position) {
        TextView tv = new TextView(this.getContext());

        applyCustomizationsToChildView(key, content, position, tv);

        tv.setGravity(Gravity.CENTER);
        tv.setText(content);

        return tv;
    }

    public abstract View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent);
}
