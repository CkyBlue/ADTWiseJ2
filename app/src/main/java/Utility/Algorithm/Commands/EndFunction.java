package Utility.Algorithm.Commands;

abstract class EndFunction extends Command {
    public final Type type = Type.EndFunction;

    public EndFunction(String name) {
        super(name);
    }

    @Override
    protected final void postExecute() {
        destroyLocalData();
        super.postExecute();
    }
    @Override
    protected final void preExecute() {
        super.preExecute();
    }

    protected abstract void destroyLocalData();
}
