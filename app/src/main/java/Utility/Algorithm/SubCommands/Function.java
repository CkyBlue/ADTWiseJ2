package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Process.Content;

/*TODO An implementation of return*/

public abstract class Function extends Command {
    public static final Type type = Type.Function;

    private EndFunction endsTo;
    private String identifier;

    public EndFunction getEndsTo() {
        return endsTo;
    }

    public void endTo(EndFunction endFunc) {
        this.endsTo = endFunc;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Function(String cmdId) {
        super(cmdId);
    }

    protected abstract void buildLocalData();

    @Override
    protected final void preExecute() {
        if (getEndsTo() == null) {
            throw new IllegalStateException("Function attempting to execute without a EndFunc set.");
        }

        buildLocalData();
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    public void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();
        getProcess().pushCommand(getEndsTo());
        getProcess().pushCommand(getChainedTo());

        postExecute();
        setProcess(null);
    }
}
