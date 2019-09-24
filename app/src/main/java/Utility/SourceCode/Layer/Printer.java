package Utility.SourceCode.Layer;

import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfHideIntent(String unitkey);

    public abstract void notifyOfShowIntent(String unitkey);
}
