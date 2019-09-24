package Utility.Diagram;

import Utility.Bases.SuperFeed;

public class BaseFeed<Content extends BaseContent, Printer extends BasePrinter> extends SuperFeed<Content, Printer> {
    void diagramContentModified() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfDiagramModification();
        }
    }

    public Type getDiagramType() {
        return Content.diagramType;
    }
}