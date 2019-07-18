package Utility.Data.Nodes;

import Utility.Bases.SuperContent;
import Utility.Data.Alteration;

public abstract class BasePrinter<Content extends SuperContent, Feed extends BaseFeed> extends Utility.Bases.SuperPrinter<Content, Feed> {
    public abstract void notifyOfContentAlteration(Alteration alteration, String elementKey, int index);
}
