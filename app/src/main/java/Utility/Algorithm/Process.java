package Utility.Algorithm;

import com.example.ckyblue.adtwisei4.Logger;

import Utility.Algorithm.Commands.Command;
import Utility.Algorithm.Commands.Input;
import Utility.Algorithm.Tree.Content;
import Utility.Algorithm.Tree.Feed;
import Utility.Data.Type;
import Utility.Input.Receiver;

/*TODO Internal back-tracking abilities of Process object*/

public abstract class Process {
    private String TAG = getClass().getName();

    private final Feed algorithmTreeFeed = new Feed();

    private final Utility.Resources.Feed resourcesFeed = new Utility.Resources.Feed();
    private final Utility.Algorithm.CmdStack.Feed cmdStackFeed = new Utility.Algorithm.CmdStack.Feed();

    private final Receiver inputReceiver = new Receiver() {
        @Override
        public void receiveInput(Object input) {
            relayInput(input);
        }
    };

    public void setAlgorithmTree(Content treeContent) {
        this.resourcesFeed.setContent(new Utility.Resources.Content());
        this.cmdStackFeed.setContent(new Utility.Algorithm.CmdStack.Content());

        this.algorithmTreeFeed.setContent(treeContent);

        Command header = treeContent.getInitializer();
        if (header != null) {
            header.execute(this);
        }
    }

    public void loadAlgorithm(String key) {
        if (this.algorithmTreeFeed.getContent() == null) {
            throw new IllegalStateException("The Algorithm.Content reference is currently null.");

        } else if (!this.algorithmTreeFeed.getContent().getAlgorithmKeys().contains(key)) {
            throw new IllegalArgumentException("The Algorithm.key " + key +
                    " is not recignized within the current Algorithm.Content reference.");

        }

        this.cmdStackFeed.getContent().clear();
        this.cmdStackFeed.getContent().push(this.algorithmTreeFeed.getContent().getAlgorithmHeader(key));

        /*TODO Build algorithm options from tree and kickstart initializer Algorithm*/
    }

    public void pushCommand(Command cmd) {
        cmdStackFeed.getContent().push(cmd);
    }

    public Utility.Resources.Content getResources() {
        if (this.resourcesFeed.getContent() == null) {
            throw new IllegalStateException("The Process is attempting to execute with a null Resources.Content reference.");

        } else {
            return this.resourcesFeed.getContent();

        }
    }

    private Utility.Algorithm.CmdStack.Content getCmdStack() {
        if (this.cmdStackFeed.getContent() == null) {
            throw new IllegalStateException("The Process is attempting to execute with a null CmdStack.Content reference.");

        } else {
            return this.cmdStackFeed.getContent();

        }
    }

    public void log(String log) {
        getResources().getLogsFeed().getContent().log(log);
    }

    public void output(String output) {
        getResources().getOutputFeed().getContent().log(output);
    }

    public void input(Input input, Type inputType) {
//     TODO Use input parameter
        inputReceiver.deployReader(inputType);
    }

    private void relayInput(Object input) {
        /*TODO Build*/
    }

    public void onInputHandled() {
        this.inputReceiver.inputHandled();
    }

    public abstract void onCmdDispatched();

    public Utility.Data.Layer.Content getDataLayer() {
        return getResources().getDataLayerFeed().getContent();
    }

    public Utility.SourceCode.Layer.Content getSourceCodeLayer() {
        return getResources().getSourceCodeLayerFeed().getContent();
    }

    public void refreshIntent() {
        getResources().refreshIntent();
        getCmdStack().refreshIntent();
    }

    public void execute() {
        String cmdName = "null";

        if (!getCmdStack().isEmpty()) {
            Command currentCommand = this.getCmdStack().pop();
            cmdName = currentCommand.getName();

            Logger.log(TAG, "execute()  => " + cmdName);
            currentCommand.execute(this);

        } else {
            Command currentCommand = null;

            Logger.log(TAG, "execute()  => " + cmdName);
            onCmdDispatched();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("CmdStack : ");
        stringBuilder.append(getCmdStack().toString());
        stringBuilder.append("\n");

        stringBuilder.append("DataLayer: ");
        stringBuilder.append(getDataLayer().toString());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
