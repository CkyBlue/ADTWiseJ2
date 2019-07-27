package Utility.Cmds;

/*TODO Decouple algorithm and process*/

import java.util.HashMap;

import Utility.Resources.Content;
import Utility.Resources.Feed;

class Algorithm {
    private final HashMap<String, Command> commandsHashMap = new HashMap<>();

    private void addCommand(Command command) {

    }
}

/*TODO Internal back-tracking abilities of Process object*/

abstract class Process {
    private final Feed resourcesFeed = new Feed();
    private final Utility.Cmds.CmdStack.Feed cmdStackFeed = new Utility.Cmds.CmdStack.Feed();

    public Process(Content resources) {
        if (resources == null) {
            throw new IllegalArgumentException("The Resources.Content reference used by a Process can not be null.");
        }

        this.resourcesFeed.setContent(resources);
    }

    public void pushCommand(String key) {
    }

    private Content getResources() {
        if (this.resourcesFeed.getContent() == null) {
            throw new IllegalStateException("The command is attempting to execute with a null Resources.Content reference.");

        } else {
            return this.resourcesFeed.getContent();

        }
    }

    public abstract void onCmdDispatched();

    public void log(String log) {
        getResources().getLogsFeed().getContent().log(log);
    }

    public void output(String output) {
        getResources().getOutputFeed().getContent().log(output);
    }

    public Utility.Data.Layer.Content getDataLayer() {
        return getResources().getDataLayerFeed().getContent();
    }

    public Utility.SourceCode.Layer.Content getSourceCodeLayer() {
        return getResources().getSourceCodeLayerFeed().getContent();
    }
}

public abstract class Command {
    private String name;
    private String nextCmd;

    private Process process;
    private Algorithm algorithm;

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

    public void setNextCmd(String nextCmd) {
        this.nextCmd = nextCmd;
    }

    public Command(String name) {
        setName(name);
    }
}
