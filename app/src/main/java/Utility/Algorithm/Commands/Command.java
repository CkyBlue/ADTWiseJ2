package Utility.Algorithm.Commands;

import Utility.Algorithm.Algorithm.Content;
import Utility.Algorithm.Process;
import Utility.Data.Nodes.BluePrint;

public abstract class Command {
    private String name;
    private Command chainedTo;

    private Process process;
    private Content algorithm;

    public Command getChainedTo() {
        return chainedTo;
    }

    public void chainTo(Command chainedTo) {
        this.chainedTo = chainedTo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setProcess(Process process) {
        this.process = process;
    }

    public void setAlgorithm(Content algorithm) {
        this.algorithm = algorithm;
    }

    final Process getProcess() {
        if (this.process == null) {
            throw new IllegalStateException("The command is attempting to execute without a Process.");

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

    public void execute(Process process) {
        setProcess(process);
        preExecute();

        onExecution();
        getProcess().pushCommand(chainedTo);

        postExecute();
        setProcess(null);
    }

    public Command(String name) {
        setName(name);
    }

    public void log(String log) {
        getProcess().log(log);
    }

    public void output(String output) {
        getProcess().output(output);
    }

    public Utility.Data.Layer.Content getDataLayer() {
        return getProcess().getDataLayer();
    }

    public Utility.Data.Nodes.Unit.Content nodes(String key){
        return getDataLayer().getNodesStackFeed(key).getContent().getUnit();
    }

    public Utility.Data.Variables.Unit.Content variables(String key){
        return getDataLayer().getVariablesStackFeed(key).getContent().getUnit();
    }

    public void pushNodes(String key){
        getDataLayer().getNodesStackFeed(key).getContent().push();
    }

    public void pushVariables(String key){
        getDataLayer().getVariablesStackFeed(key).getContent().push();
    }

    public void popNodes(String key){
        getDataLayer().getNodesStackFeed(key).getContent().pop();
    }

    public void popVariables(String key){
        getDataLayer().getVariablesStackFeed(key).getContent().pop();
    }

    public void buildNodesStack(String key, BluePrint bluePrint, int size){
        getDataLayer().buildNodesStack(key, bluePrint, size);
    }

    public void buildVariablesStack(String key){
        getDataLayer().buildVariablesStack(key);
    }

    public Utility.SourceCode.Layer.Content getSourceCodeLayer() {
        return getProcess().getSourceCodeLayer();
    }

    @Override
    public String toString() {
        return name;
    }
}
