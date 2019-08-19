package Utility.Algorithm.Process;

import java.util.Arrays;
import java.util.HashSet;

import Utility.Algorithm.SubCommands.Type;
import Utility.Data.Layer.Component;
import Utility.Data.Nodes.BluePrint;

public abstract class Command {
    public Type type = Type.Command;

    private String cmdId;
    private Command chainedTo;

    private Content process;

    protected Command getChainedTo() {
        return chainedTo;
    }

    public void chainTo(Command chainedTo) {
        this.chainedTo = chainedTo;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getCmdId() {
        return cmdId;
    }

    protected void setProcess(Content process) {
        this.process = process;
    }

    protected final Content getProcess() {
        if (this.process == null) {
            throw new IllegalStateException("The command is attempting to execute without a Content.");

        } else {
            return this.process;

        }
    }

    protected void preExecute() {
    }

    protected void postExecute() {
        getProcess().onCmdDispatched();
    }

    protected abstract void onExecution();

    public void execute(Content process) {
        setProcess(process);
        preExecute();

        onExecution();
        getProcess().pushCommand(chainedTo);

        postExecute();
        setProcess(null);
    }

    public Command(String cmdId) {
        setCmdId(cmdId);
    }

    public void log(String log) {
        getProcess().log(log);
    }

    public void output(String output) {
        getProcess().output(output);
    }

    public Utility.Data.Layer.Content getDataLayer() {
        return getProcess().getResources().getDataLayerFeed().getContent();
    }

    public Utility.Data.Nodes.Unit.Content nodes(String key) {
        return getDataLayer().getNodesStackFeed(key).getContent().getUnit();
    }

    public Utility.Data.Variables.Unit.Content variables(String key) {
        return getDataLayer().getVariablesStackFeed(key).getContent().getUnit();
    }

    public void pushNodesStack(String key) {
        getDataLayer().getNodesStackFeed(key).getContent().push();
    }

    public void pushVarStack(String key) {
        getDataLayer().getVariablesStackFeed(key).getContent().push();
    }

    public void popNodesStack(String key) {
        getDataLayer().getNodesStackFeed(key).getContent().pop();
    }

    public void popVarStack(String key) {
        getDataLayer().getVariablesStackFeed(key).getContent().pop();
    }

    public void buildNodesStack(String key, BluePrint bluePrint, int size) {
        getDataLayer().buildNodesStack(key, bluePrint, size);
    }

    public void buildVarStack(String key) {
        getDataLayer().buildVariablesStack(key);
    }

    public void removeNodesStack(String key) {
        getDataLayer().removeStack(Component.NodesStack, key);
    }

    public void removeVarStack(String key) {
        getDataLayer().removeStack(Component.VariablesStack, key);
    }

    public void buildSourceCodeUnits(String[] unitKeys) {
        getSourceCodeLayer().buildUnits(new HashSet<>(Arrays.asList(unitKeys)));
    }

    public Utility.SourceCode.Unit.Content getSourceCodeUnit(String unitKey) {
        return getSourceCodeLayer().getUnitFeed(unitKey).getContent();
    }

    public Utility.SourceCode.Layer.Content getSourceCodeLayer() {
        return getProcess().getResources().getSourceCodeLayerFeed().getContent();
    }

    @Override
    public String toString() {
        return cmdId;
    }
}
