package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;

public abstract class EndWhile extends Command {
    public final Type type = Type.EndWhile;

    public EndWhile(String cmdId) {
        super(cmdId);
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    @Override
    protected final void preExecute() {
        super.preExecute();
    }
}
