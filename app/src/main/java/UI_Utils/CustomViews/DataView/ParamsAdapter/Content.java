package UI_Utils.CustomViews.DataView.ParamsAdapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import UI_Utils.CustomViews.DataView.Port;
import Utility.Bases.SuperContent;

public abstract class Content extends SuperContent<Feed> {
    @NonNull
    public abstract AbsListView.LayoutParams getRowParams(Port port);

    @NonNull
    public abstract LinearLayout.LayoutParams getChildParams(Port port, String key);

    public abstract void applyModifications(Port port, String key, View view);
}
