package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Content;

public abstract class DoWhile extends Block {
    public final Type type = Type.DoWhile;
    private EndWhile endsTo;

    public EndWhile getEndsTo() {
        return endsTo;
    }

    public void endTo(EndWhile endWhile) {
        this.endsTo = endWhile;
    }

    public DoWhile(String cmdId) {
        super(cmdId);
    }

    @Override
    protected final void preExecute() {
        if (getEndsTo() == null) {
            throw new IllegalStateException("DoWhile attempting to execute without a EndWhile set.");
        }
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    protected abstract boolean evaluate();

    @Override
    public final void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();

        if (evaluate()) {
            getProcess().pushCommand(this);
            getProcess().pushCommand(getChainedTo());

        } else {
            getProcess().pushCommand(getBlockChainedTo());
            getProcess().pushCommand(getEndsTo());

        }

        postExecute();
        setProcess(null);
    }
}