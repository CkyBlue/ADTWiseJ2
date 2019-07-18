package Utility.Data.Nodes.Stack;

import Utility.Data.Nodes.BasePrinter;

public abstract class Printer extends BasePrinter<Content, Feed> {
    public Utility.Data.Nodes.Unit.Content getUnit() {
        if (getContent() != null) {
            return getContent().getUnit();
        }

        return null;
    }
}
