package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Process.Content;

public abstract class Return extends Command {
    public Return(String cmdId) {
        super(cmdId);
    }

    public void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();

        postExecute();
        setProcess(null);
    }
}
