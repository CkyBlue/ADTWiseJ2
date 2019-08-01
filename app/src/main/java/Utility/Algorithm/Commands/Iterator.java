package Utility.Algorithm.Commands;

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
    public final void execute() {
        preExecute();
        onExecution();

        if (evaluate()) {

            getProcess().pushCommand(getName());
            getProcess().pushCommand(getChainedTo());

        } else {
            getProcess().pushCommand(getBlockChainedTo());

        }

        postExecute();
    }
}