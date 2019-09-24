package Utility.Diagram.Layer;

import Utility.Bases.SuperFeed;
import Utility.Diagram.Type;

public class Feed extends SuperFeed<Content, Printer> {
    public Type

    void hideUnit(String unitKey) {
        for (Printer printer : getPrinters()) {
            printer.notifyOfHideIntent(unitKey);
        }
    }

    void showUnit(String unitKey) {
        for (Printer printer : getPrinters()) {
            printer.notifyOfShowIntent(unitKey);
        }
    }
}
