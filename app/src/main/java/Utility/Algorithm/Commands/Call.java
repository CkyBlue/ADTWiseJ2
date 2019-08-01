package Utility.Algorithm.Commands;

public abstract class Call extends Command {
    public final Type type = Type.Call;
    private String functionCmd;

    public Call(String name) {
        super(name);
    }

    public final void setFunction(String functionCmd) {
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


    @Override
    public final void execute() {
        preExecute();
        onExecution();

        getProcess().pushCommand(getChainedTo());
        getProcess().pushCommand(functionCmd);

        postExecute();
    }
}
