package Utility.Diagram.Layer;

import Utility.Bases.SuperFeed;
import Utility.Diagram.Type;

public class Feed extends SuperFeed<Content, Printer> {
    public void contentAltered(Alteration alteration, Type diagramType, String diagramKey) {
        for (Printer printer : getPrinters()) {
            printer.notifyOfContentAlteration(alteration, diagramType, diagramKey);
        }
    }
}
