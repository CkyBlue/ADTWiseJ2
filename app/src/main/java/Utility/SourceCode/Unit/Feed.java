package Utility.SourceCode.Unit;

import Utility.SourceCode.FormattingKey;
import Utility.Bases.SuperFeed;

public class Feed extends SuperFeed <Content, Printer>{
    void maskChanged() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfMaskChange();
        }
    }

    void scopeChanged() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfScopeChange();
        }
    }

    void formattingChanged(FormattingKey key) {
        for (Printer printer : getPrinters()) {
            printer.notifyOfFormattingChange(key);
        }
    }
}
