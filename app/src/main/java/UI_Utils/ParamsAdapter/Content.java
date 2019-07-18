package UI_Utils.ParamsAdapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import Utility.Bases.SuperContent;

public abstract class Content extends SuperContent<Feed> {
    @NonNull
    public abstract AbsListView.LayoutParams getRowParams();

    @NonNull
    public abstract LinearLayout.LayoutParams getChildParams(String key);

    public abstract void applyModifications(String key, View view);
}
