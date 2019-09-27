package Utility.Diagram;

import Utility.Bases.SuperContent;

public class BaseContent<Feed extends BaseFeed> extends SuperContent<Feed> {
    public static Type diagramType = Type.Diagram;

    protected void diagramContentModified() {
        if (getFeed() != null) {
            getFeed().diagramContentModified();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }
}