package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Process.Content;

public abstract class Call extends Command {
    public static final Type type = Type.Call;
    private Command functionCmd;

    public Call(String cmdId) {
        super(cmdId);
    }

    public final void setFunction(Command functionCmd) {
        this.functionCmd = functionCmd;
    }

    protected abstract void buildArgs();

    protected abstract void passArgs();

    @Override
    protected final void preExecute() {
        buildArgs();
        passArgs();

        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    public final void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();
        getProcess().pushCommand(getChainedTo());
        getProcess().pushCommand(functionCmd);

        postExecute();
        setProcess(null);
    }
}
