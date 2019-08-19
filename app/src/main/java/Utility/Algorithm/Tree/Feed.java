package Utility.Algorithm.Tree;

import Utility.Bases.SuperFeed;
import Utility.Data.Alteration;

/*TODO Remove Content, Feed, Printer architecture for AlgorithmTree which is suppposed to behave as a static data pool*/

public class Feed extends SuperFeed<Content, Printer> {
    public void contentAltered(Alteration alteration, String algorithmName) {
        for (Printer printer : getPrinters()) {
            printer.notifyOfContentAlteration(alteration, algorithmName);
        }
    }
}
