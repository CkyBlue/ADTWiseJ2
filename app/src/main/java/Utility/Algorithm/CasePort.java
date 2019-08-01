package Utility.Algorithm;

import Utility.Algorithm.Commands.Command;

public abstract class CasePort {
    private String chainedTo;
    private Command command;

    protected final Command getCommand() {
        if (this.command == null) {
            throw new IllegalStateException("The CasePort doesn't have a Command reference.");

        } else {
            return this.command;

        }
    }

    public final boolean evaluate(Command command) {
        this.command = command;
        return this.condition();
    }

    protected abstract boolean condition();

    public final String getChainedTo() {
        return chainedTo;
    }

    public final void chainTo(String chainTo) {
        this.chainedTo = chainTo;
    }
}
