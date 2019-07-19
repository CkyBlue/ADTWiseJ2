package Utility.Logs;

import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfNewLog();
}
