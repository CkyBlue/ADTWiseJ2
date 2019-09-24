package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Process.Content;

public abstract class IfOrElse extends Block {
    public static final Type type = Type.IfOrElse;
    private Command elseChainedTo;
    private EndIf endsTo;

    public EndIf getEndsTo() {
        return endsTo;
    }

    public void endTo(EndIf endIf) {
        this.endsTo = endIf;
    }

    public IfOrElse(String cmdId) {
        super(cmdId);
    }

    @Override
    protected final void preExecute() {
        if (getEndsTo() == null) {
            throw new IllegalStateException("IfOrElse attempting to execute without a EndIf set.");
        }
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    public void elseChainTo(Command elseChainTo) {
        this.elseChainedTo = elseChainTo;
    }

    protected abstract boolean evaluate();

    @Override
    public final void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();

        getProcess().pushCommand(getBlockChainedTo());
        getProcess().pushCommand(getEndsTo());

        if (evaluate()) {
            getProcess().pushCommand(getChainedTo());

        } else {
            getProcess().pushCommand(elseChainedTo);

        }

        postExecute();
        setProcess(null);
    }
}