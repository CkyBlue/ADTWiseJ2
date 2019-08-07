package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;

/*TODO An implementation of return*/

public abstract class Function extends Command {
    private class EndFunction extends Command {
        public final Type type = Type.EndFunction;

        private EndFunction(String name) {
            super(name);
        }

        @Override
        protected final void onExecution() {
            onEnd();
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
    }

    public final Type type = Type.Function;

    public Function(String name) {
        super(name);
    }

    protected abstract void buildLocalData();

    protected abstract void destroyLocalData();

    protected abstract void onEnd();

    @Override
    protected final void preExecute() {
        buildLocalData();
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    public void execute(Process process) {
        setProcess(process);
        preExecute();

        onExecution();
        getProcess().pushCommand(new EndFunction("End Function - " + getName()));
        getProcess().pushCommand(getChainedTo());

        postExecute();
        setProcess(null);
    }
}
