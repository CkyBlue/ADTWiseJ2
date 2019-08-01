package Utility.Algorithm.Commands;

abstract class Block extends Command {
    public Type type = Type.Command;

    private String blockChainedTo;

    public Block(String name) {
        super(name);
    }

    public String getBlockChainedTo() {
        return blockChainedTo;
    }

    public void blockChainTo(String blockChainedTo) {
        this.blockChainedTo = blockChainedTo;
    }

    @Override
    public void execute() {
        preExecute();

        onExecution();

        getProcess().pushCommand(getBlockChainedTo());
        getProcess().pushCommand(getChainedTo());

        postExecute();
    }
}
