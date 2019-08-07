package Utility.Algorithm.Commands;

import Utility.Algorithm.Process;

public abstract class IfOrElse extends Block {
    private class EndIf extends Command {
        public final Type type = Type.EndIf;

        private EndIf(String name) {
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

    public final Type type = Type.IfOrElse;
    private Command elseChainedTo;

    public IfOrElse(String name) {
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

    protected void elseChainTo(Command elseChainTo) {
        this.elseChainedTo = elseChainTo;
    }

    protected abstract boolean evaluate();

    protected abstract void onEnd();

    @Override
    public final void execute(Process process) {
        setProcess(process);
        preExecute();

        onExecution();

        getProcess().pushCommand(getBlockChainedTo());
        getProcess().pushCommand(new EndIf("End If - " + getName()));

        if (evaluate()) {
            getProcess().pushCommand(getChainedTo());

        } else {
            getProcess().pushCommand(elseChainedTo);

        }

        postExecute();
        setProcess(null);
    }
}