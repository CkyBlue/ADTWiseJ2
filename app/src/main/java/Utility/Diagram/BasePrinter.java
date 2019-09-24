package Utility.Diagram;

import Utility.Bases.SuperPrinter;

public abstract class BasePrinter<Content extends BaseContent, Feed extends BaseFeed> extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfDiagramModification();
}