package Utility.Data.Variables.Stack;

import Utility.Data.Variables.BasePrinter;

public abstract class Printer extends BasePrinter<Content, Feed> {
    public Utility.Data.Variables.Unit.Content getUnit() {
        if (getContent() != null) {
            return getContent().getUnit();
        }

        return null;
    }
}
