package Utility.Algorithm.Process;

import Utility.Bases.SuperFeed;

public class Feed extends SuperFeed<Content, Printer> {
    void newAlgorithmTree() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfNewAlgorithmTree();
        }
    }

    void cmdDispatched() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfCmdDispatched();
        }
    }

    void newCmdInOperation() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfNewCmdInOperation();
        }
    }

    void algorithmTerminated() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfAlgorithmTerminated();
        }
    }

    void newAlgorithmLoaded() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfNewAlgorithmLoaded();
        }
    }

    void inputReaderInOperation() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfInputReaderInOperation();
        }
    }

    void inputReceived() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfInputReceived();
        }
    }

    void inputHandled() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfInputHandled();
        }
    }
}
