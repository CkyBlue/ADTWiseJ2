package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;

public abstract class Call extends Command {
    public final Type type = Type.Call;
    private Command functionCmd;

    public Call(String name) {
        super(name);
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

    public final void execute(Process process) {
        setProcess(process);
        preExecute();

        onExecution();
        getProcess().pushCommand(getChainedTo());
        getProcess().pushCommand(functionCmd);

        postExecute();
        setProcess(null);
    }
}
