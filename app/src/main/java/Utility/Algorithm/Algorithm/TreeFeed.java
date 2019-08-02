package Utility.Algorithm.Algorithm;

import Utility.Bases.SuperFeed;

public class TreeFeed extends SuperFeed<TreeContent, TreePrinter> {
    public void contentAltered(Alteration alteration, String variableName) {
        for (TreePrinter printer : getPrinters()){
            printer.notifyOfContentAlteration(alteration, variableName);
        }
    }
}
