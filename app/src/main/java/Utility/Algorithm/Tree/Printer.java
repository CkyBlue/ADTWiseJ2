package Utility.Algorithm.Tree;

import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfContentAlteration(Alteration alteration, String algorithmName);
}
