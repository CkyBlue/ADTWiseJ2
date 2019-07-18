package Utility.Colors.Chrome;

import Utility.Bases.SuperContent;
import Utility.SourceCode.Unit.Feed;

public abstract class Content extends SuperContent<Feed> {
    /*Returns a Component object which carries textColor, BGColor & Opacity information based on text content and position*/

    public abstract Components fetchComponents(String content, int position);
}