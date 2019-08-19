package Utility.Algorithm.SubCommands;

import Utility.Algorithm.Process.Command;
import Utility.Algorithm.Process.Content;

abstract class Block extends Command {
    private Command blockChainedTo;

    public Block(String cmdId) {
        super(cmdId);
    }

    public Command getBlockChainedTo() {
        return blockChainedTo;
    }

    public void blockChainTo(Command blockChainedTo) {
        this.blockChainedTo = blockChainedTo;
    }

    @Override
    public void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();

        getProcess().pushCommand(getBlockChainedTo());
        getProcess().pushCommand(getChainedTo());

        postExecute();
        setProcess(null);
    }
}
