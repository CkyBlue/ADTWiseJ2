package UI_Utils.CustomViews.DataView.ParamsAdapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import Utility.Port;
import Utility.Bases.SuperContent;

public abstract class Content extends SuperContent<Feed> {
    @NonNull
    public abstract AbsListView.LayoutParams getBodyRowParams();

    @NonNull
    public abstract LinearLayout.LayoutParams getHeaderRowParams();

    @NonNull
    public abstract LinearLayout.LayoutParams getChildParams(Port port, String key);

    public abstract void applyModifications(Port port, String key, View view);
}
