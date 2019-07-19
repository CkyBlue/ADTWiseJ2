package Utility.Data.Layer;

import Utility.Bases.SuperFeed;
import Utility.Data.Alteration;

public class Feed extends SuperFeed<Content, Printer> {
    public void contentAltered(Alteration alteration, Component component, String componentKey) {

        for (Printer printer : getPrinters()){
            printer.notifyOfContentAlteration(alteration, component, componentKey);
        }
    }
}
