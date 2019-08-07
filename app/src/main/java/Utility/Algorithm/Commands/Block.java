package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;

abstract class Block extends Command {
    private Command blockChainedTo;

    public Block(String name) {
        super(name);
    }

    public Command getBlockChainedTo() {
        return blockChainedTo;
    }

    public void blockChainTo(Command blockChainedTo) {
        this.blockChainedTo = blockChainedTo;
    }

    @Override
    public void execute(Process process) {
        setProcess(process);
        preExecute();

        onExecution();

        getProcess().pushCommand(getBlockChainedTo());
        getProcess().pushCommand(getChainedTo());

        postExecute();
        setProcess(null);
    }
}
