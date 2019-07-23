package Utility.Data.Layer;

import Utility.Bases.SuperPrinter;
import Utility.Data.Alteration;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfContentAlteration(Alteration alteration, Component component, String componentKey);

    @Override
    public void setFeed(Feed feed) {
        super.setFeed(feed);
    }
}
