package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;

public abstract class Iterator extends Block {
    public final Type type = Type.Iterator;

    public Iterator(String name) {
        super(name);
    }

    @Override
    protected final void preExecute() {
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    protected abstract boolean evaluate();

    @Override
    public final void execute(Process process) {
        setProcess(process);
        preExecute();
        onExecution();

        if (evaluate()) {
            getProcess().pushCommand(this);
            getProcess().pushCommand(getChainedTo());

        } else {
            getProcess().pushCommand(getBlockChainedTo());

        }

        postExecute();
        setProcess(null);
    }
}