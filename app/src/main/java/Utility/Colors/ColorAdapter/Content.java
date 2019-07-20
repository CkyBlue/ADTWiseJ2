package Utility.Colors.ColorAdapter;

import UI_Utils.CustomViews.DataView.Port;
import Utility.Bases.SuperContent;
import Utility.Colors.Components;

public abstract class Content extends SuperContent<Feed> {
    public abstract Components fetchComponents(Port port, String key, String content, int position);
}
