package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;

public abstract class EndIf extends Command {
    public final Type type = Type.EndIf;

    public EndIf(String cmdId) {
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