package Utility.Colors.ColorAdapter;

import Utility.Bases.SuperContent;
import Utility.Colors.Components;

public abstract class Content extends SuperContent<Feed> {
    public abstract Components fetchComponents(String key, String content, int position);
}
