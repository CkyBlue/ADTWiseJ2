package UI_Utils.CustomViews.DataViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import UI_Utils.CustomViews.DataViews.Customizations.Content;

import java.util.List;

public abstract class DataAdapter<T> extends ArrayAdapter<T> {
    private Content customizations;
    private boolean notified = false;

    public DataAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    @NonNull
    public UI_Utils.CustomViews.DataViews.ParamsAdapter.Content getParamsAdapter() {
        return Utilities.getParamsAdapter(getCustomizations());
    }

    @NonNull
    public Utility.Colors.ColorAdapter.Content getColorAdapter() {
        return Utilities.getColorAdapter(getCustomizations());
    }

    public Content getCustomizations() {
        return customizations;
    }

    public void setCustomizations(Content customizations) {
        this.customizations = customizations;
    }

    public abstract View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent);
}
