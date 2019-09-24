package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;

public abstract class EndFunction extends Command {
    public static final Type type = Type.EndFunction;

    public EndFunction(String cmdId) {
        super(cmdId);
    }

    @Override
    protected final void postExecute() {
        destroyLocalData();
        super.postExecute();
    }

    protected abstract void destroyLocalData();

    @Override
    protected final void preExecute() {
        super.preExecute();
    }
}