package Utility.Diagram.Layer;

import Utility.Bases.SuperPrinter;
import Utility.Diagram.Type;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfContentAlteration(Alteration alteration, Type diagramType, String diagramKey);
}
