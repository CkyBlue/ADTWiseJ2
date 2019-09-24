package Utility.Algorithm.Process;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.Set;

import Utility.Algorithm.SubCommands.Input;
import Utility.Algorithm.Tree.Feed;
import Utility.Bases.SuperContent;
import Utility.Data.Type;
import Utility.Input.Receiver;

/*TODO Internal back-tracking abilities of BaseContent object*/

public class Content extends SuperContent<Utility.Algorithm.Process.Feed> {
    private String TAG = getClass().getName();
    public final static String Initializer = "Initializer";

    private final Feed algorithmTreeFeed = new Feed();
    private Command cmdInOperation;

    private final Utility.Resources.Feed resourcesFeed = new Utility.Resources.Feed();
    private final Utility.Algorithm.CmdStack.Feed cmdStackFeed = new Utility.Algorithm.CmdStack.Feed();

    private final Receiver inputReceiver = new Receiver() {
        @Override
        public void receiveInput(Object input) {
            relayInput(input);
        }
    };

    public String getIdOfCmdInOperation() {
        if (cmdInOperation == null) {
            return "null";
        }

        return cmdInOperation.getCmdId();
    }

    private boolean algorithmInOperation = false;
    private boolean algorithmTreeLoaded = false;

    private Input activeInputCmd = null;

    public boolean isAlgorithmInOperation() {
        return algorithmInOperation;
    }

    public Utility.Resources.Feed getResourcesFeed() {
        return resourcesFeed;
    }

    public Utility.Algorithm.CmdStack.Feed getCmdStackFeed() {
        return cmdStackFeed;
    }

    public Receiver getInputReceiver() {
        return inputReceiver;
    }

    public boolean isAlgorithmTreeLoaded() {
        return algorithmTreeLoaded;
    }

    public void setAlgorithmTree(Utility.Algorithm.Tree.Content treeContent) {
        if (algorithmInOperation) {
            throw new IllegalStateException("An algorithm is currently in operation. A new tree can only be loaded when no" +
                    " algorithms are running.");
        }

        getCmdStack().clear();
        algorithmTreeLoaded = treeContent != null;

        this.algorithmTreeFeed.setContent(treeContent);

        if (getFeed() != null) {
            getFeed().newAlgorithmTree();
        }

        if (treeContent != null) {
            loadAlgorithmHeader(Content.Initializer, treeContent.getInitializer());
        }
    }

    public void loadAlgorithm(String key) {
        if (!this.algorithmTreeFeed.getContent().getAlgorithmKeys().contains(key)) {
            throw new IllegalArgumentException("The Algorithm.key " + key +
                    " is not recignized within the current Algorithm.BaseContent reference.");

        }

        loadAlgorithmHeader(key, this.algorithmTreeFeed.getContent().getAlgorithmHeader(key));
    }

    private void loadAlgorithmHeader(String key, Command header) {
        if (this.algorithmTreeFeed.getContent() == null) {
            throw new IllegalStateException("The Algorithm.BaseContent reference is currently null.");

        } else if (algorithmInOperation) {
            throw new IllegalStateException("An algorithm is currently active already..");

        }

        this.cmdStackFeed.getContent().clear();
        this.cmdStackFeed.getContent().push(header);

        // Clearing the previous output before the newAlgorithmLoaded call back
        this.getResources().getOutputFeed().getContent().clear();

        if (header != null) {
            this.algorithmInOperation = true;

            if (getFeed() != null) {
                getFeed().newAlgorithmLoaded(key);
            }

            /*The algorithm's first command is automatically executed for convenience in setting up display elements
             * in the case of the initializer, the whole initialization can be handled there to remove the need for walking
             * through any initialization process*/
            execute();
        }
    }

    public void pushCommand(Command cmd) {
        cmdStackFeed.getContent().push(cmd);
    }

    public Utility.Resources.Content getResources() {
        if (this.resourcesFeed.getContent() == null) {
            throw new IllegalStateException("Resources is null.");

        } else {
            return this.resourcesFeed.getContent();

        }
    }

    private Utility.Algorithm.CmdStack.Content getCmdStack() {
        if (this.cmdStackFeed.getContent() == null) {
            throw new IllegalStateException("The BaseContent is attempting to execute with a null CmdStack.BaseContent reference.");

        } else {
            return this.cmdStackFeed.getContent();

        }
    }

    public void log(String log, boolean newLine) {
        getResources().getLogsFeed().getContent().log(log, newLine);
    }

    public void output(String output) {
        getResources().getOutputFeed().getContent().log(output);
    }

    public void clearOutput() {
        getResources().getOutputFeed().getContent().clear();
    }

    public void input(Input input, Type inputType) {
        activeInputCmd = input;
        inputReceiver.deployReader(inputType);

        if (getFeed() != null) {
            getFeed().inputReaderInOperation();
        }
    }

    private void relayInput(Object input) {
        activeInputCmd.receiveInput(input);
        activeInputCmd = null;

        if (getFeed() != null) {
            getFeed().inputReceived();
        }
    }

    public void onInputHandled() {
        this.inputReceiver.inputHandled();

        if (getFeed() != null) {
            getFeed().inputHandled();
        }
    }

    void onCmdDispatched() {
        if (getCmdStack().isEmpty()) {
            onAlgorithmEnded();
        }

        if (getFeed() != null) {
            getFeed().cmdDispatched();
        }
    }

    public Set<String> getAlgorithmKeys() {
        if (algorithmTreeFeed.getContent() != null) {
            return algorithmTreeFeed.getContent().getAlgorithmKeys();
        }

        return null;
    }

    public Content() {
        this.resourcesFeed.setContent(new Utility.Resources.Content());
        this.cmdStackFeed.setContent(new Utility.Algorithm.CmdStack.Content());
    }

    public void refreshIntent() {
        getResources().refreshIntent();
        getCmdStack().refreshIntent();

        if (getFeed() != null) {
            getFeed().refreshIntent();
        }
    }

    public void execute() {
        cmdInOperation = null;

        if (!getCmdStack().isEmpty()) {
            if (inputReceiver.isReaderActive()) {
                throw new IllegalStateException("Input Receiver is expecting input.");
            }
            cmdInOperation = this.getCmdStack().pop();

            if (getFeed() != null) {
                getFeed().newCmdInOperation();
            }

            cmdInOperation.execute(this);

        } else {
            onCmdDispatched();
        }
    }

    private void onAlgorithmEnded() {
        algorithmInOperation = false;
        Logger.log(TAG, "onAlgorithmEnded() called");


        if (getFeed() != null) {
            getFeed().algorithmTerminated();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("CmdStack : ");
        stringBuilder.append(getCmdStack().toString());
        stringBuilder.append("\n");

        stringBuilder.append("DataLayer: ");
        stringBuilder.append(getResources().getDataLayerFeed().getContent().toString());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
