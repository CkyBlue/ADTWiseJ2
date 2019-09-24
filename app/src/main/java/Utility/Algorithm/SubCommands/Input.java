package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Process.Content;
import Utility.Data.Type;

public abstract class Input extends Command {
    public static final Utility.Algorithm.SubCommands.Type type = Utility.Algorithm.SubCommands.Type.Input;

    private Object inputContent;
    private Type inputType;

    protected final Object getInputContent() {
        return this.inputContent;
    }

    public final Type getType() {
        return inputType;
    }

    public void execute(Content process) {
        setProcess(process);
        preExecute();


        deployReader();
    }

    public final void receiveInput(Object input) {
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

    public Input(String cmdId, Type inputType) {
        super(cmdId);
        this.inputType = inputType;
    }

    private void deployReader() {
        this.getProcess().input(this, inputType);
    }

    private void inputHandled() {
        this.getProcess().onInputHandled();
    }
}
