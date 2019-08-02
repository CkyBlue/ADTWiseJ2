package Utility.Algorithm.Algorithm;

import Utility.Bases.SuperPrinter;

public abstract class TreePrinter extends SuperPrinter<TreeContent, TreeFeed> {
    public abstract void notifyOfContentAlteration(Alteration alteration, String algorithmName);
}
