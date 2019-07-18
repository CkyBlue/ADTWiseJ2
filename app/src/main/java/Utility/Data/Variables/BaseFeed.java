package Utility.Data.Variables;

import Utility.Bases.SuperContent;
import Utility.Data.Alteration;

public abstract class BaseFeed<Content extends SuperContent, Printer extends BasePrinter> extends Utility.Bases.SuperFeed<Content, Printer> {
    public void contentAltered(Alteration alteration, String variableName) {
        for (Printer printer : getPrinters()){
            printer.notifyOfContentAlteration(alteration, variableName);
        }
    }
}