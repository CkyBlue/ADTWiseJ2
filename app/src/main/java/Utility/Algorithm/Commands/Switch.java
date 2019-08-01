package Utility.Algorithm.Commands;

import java.util.ArrayList;

import Utility.Algorithm.CasePort;

public abstract class Switch extends Block{
    public final Type type = Type.Switch;

    private ArrayList<CasePort> cases = new ArrayList<>();

    public Switch(String name) {
        super(name);
    }

    public final void addCase(CasePort casePort){
        this.cases.add(casePort);
    }

    @Override
    protected final void preExecute() {
        super.preExecute();
    }

    @Override
    protected final void postExecute() {
        super.postExecute();
    }

    @Override
    public final void execute() {
        preExecute();

        onExecution();
        getProcess().pushCommand(getBlockChainedTo());

        for (CasePort casePort : cases){
            if (casePort.evaluate(this)) {
                getProcess().pushCommand(casePort.getChainedTo());
                break;

            }
        }

        postExecute();
    }
}
