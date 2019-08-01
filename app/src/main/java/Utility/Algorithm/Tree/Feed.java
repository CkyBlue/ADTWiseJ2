package Utility.Algorithm.Tree;

import Utility.Bases.SuperFeed;

public class Feed extends SuperFeed<Content, Printer> {
    public void contentAltered(Alteration alteration, String variableName) {
        for (Printer printer : getPrinters()){
            printer.notifyOfContentAlteration(alteration, variableName);
        }
    }
}
