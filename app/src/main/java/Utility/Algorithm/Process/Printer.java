package Utility.Algorithm.Process;

import Utility.Bases.SuperPrinter;

public abstract class Printer extends SuperPrinter<Content, Feed> {
    public abstract void notifyOfNewAlgorithmTree();

    public abstract void notifyOfCmdDispatched();

    public abstract void notifyOfNewCmdInOperation();

    public abstract void notifyOfNewAlgorithmLoaded(String algorithmKey);

    public abstract void notifyOfAlgorithmTerminated();

    public abstract void notifyOfInputReaderInOperation();

    public abstract void notifyOfInputReceived();

    public abstract void notifyOfInputHandled();
}
