package Utility.Algorithm.Commands;

public abstract class Function extends Command {
    public final Type type = Type.Function;
    private String functionEndCmd;

    public final void setFunctionEnd(String functionEndCmd) {
        this.functionEndCmd = functionEndCmd;
    }

    public Function(String name, String functionEndCmd) {
        super(name);
        this.functionEndCmd = functionEndCmd;
    }

    protected abstract void buildLocalData();

    @Override
    protected final void preExecute() {
        buildLocalData();
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

        getProcess().pushCommand(functionEndCmd);
        getProcess().pushCommand(getChainedTo());

        postExecute();
    }
}
