package Utility.Cmds;

/*TODO Custom class to encapsulate Logger.log()*/
/*TODO Decouple algorithm and process*/

import java.util.HashMap;

class Algorithm {
    private final HashMap<String, Command> commandsHashMap = new HashMap<>();

    private void addCommand(Command command) {

    }
}

class Process {
    public void getResources() {
    }

    public void pushCommand(String key) {
    }

    public void output(String output) {
    }

    public void log(String log) {
    }

    public void onCmdDispatched() {

    }
}

public abstract class Command {
    private String name;

    private Process process;
    private Algorithm algorithm;

    private String nextCmd;
    private StringBuilder logs;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    private Process getProcess() {
        if (this.process == null) {
            throw new IllegalStateException("The command is attempting to execute without a Process.");

        } else {
            return this.process;

        }
    }

    protected void preExecute() {
        logs = new StringBuilder();
    }

    protected void postExecute() {
        getProcess().onCmdDispatched();
    }

    protected void execution() {
    }

    public void execute() {
        preExecute();
        execution();
        getProcess().pushCommand(nextCmd);
        postExecute();
    }

    public void output(String output) {
        getProcess().output(output);
    }

    public void log(String log) {
        logs.append(log);
        logs.append("/n/n");

    }

    public void getVariablesStack() {
    }

    public void getNodesStack() {
    }

    public void getSourceCodeUnit() {
    }

    public void setNextCmd(String nextCmd) {
        this.nextCmd = nextCmd;
    }

    public Command(String name) {
        setName(name);
    }
}
