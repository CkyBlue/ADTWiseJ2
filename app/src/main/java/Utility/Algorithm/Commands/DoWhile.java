package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;

public abstract class DoWhile extends Block {
    private class EndWhile extends Command {
        public final Type type = Type.EndWhile;

        private EndWhile(String name) {
            super(name);
        }

        @Override
        protected final void onExecution() {
            onEnd();
        }

        @Override
        protected final void postExecute() {
            super.postExecute();
        }

        @Override
        protected final void preExecute() {
            super.preExecute();
        }
    }

    public final Type type = Type.DoWhile;

    public DoWhile(String name) {
        super(name);
    }

    @Override
    protected final void preExecute() {
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    protected abstract boolean evaluate();

    protected abstract void onEnd();

    @Override
    public final void execute(Process process) {
        setProcess(process);
        preExecute();

        onExecution();

        if (evaluate()) {
            getProcess().pushCommand(this);
            getProcess().pushCommand(getChainedTo());

        } else {
            getProcess().pushCommand(getBlockChainedTo());
            getProcess().pushCommand(new EndWhile("End While - " + getName()));

        }

        postExecute();
        setProcess(null);
    }
}