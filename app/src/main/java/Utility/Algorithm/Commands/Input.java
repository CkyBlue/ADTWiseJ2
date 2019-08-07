package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;
import Utility.Data.Type;

public abstract class Input extends Command {
    public final Utility.Algorithm.Commands.Type type = Utility.Algorithm.Commands.Type.Input;

    private String inputContent;
    private Type inputType;

    protected final Object getInputContent() {
        return this.inputContent;
    }

    public final Type getType() {
        return inputType;
    }

    public void execute(Process process) {
        setProcess(process);
        preExecute();


        deployReader();
    }

    public final  void receiveInput(String input) {
        this.inputContent = input;

        onExecution();
        getProcess().pushCommand(getChainedTo());

        postExecute();
        setProcess(null);
    }

    @Override
    protected final void preExecute() {
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        inputHandled();
        super.postExecute();
    }

    public Input(String name, Type inputType) {
        super(name);
        this.inputType = inputType;
    }

    private void deployReader() {
        this.getProcess().input(this, inputType);
    }

    private void inputHandled() {
        this.getProcess().onInputHandled();
    }
}
