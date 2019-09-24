package Utility.SourceCode.Layer;

import Utility.Bases.SuperFeed;

public class Feed extends SuperFeed<Content, Printer> {
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
