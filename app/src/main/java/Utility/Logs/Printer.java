package Utility.Logs;

import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter<BaseContent, Feed> {
    public abstract void notifyOfNewLog();
}
