package Utility.Algorithm;

import Utility.Algorithm.Commands.Input;
import Utility.Data.Type;
import Utility.Input.Receiver;
import Utility.Resources.Content;
import Utility.Resources.Feed;

/*TODO Internal back-tracking abilities of Process object*/

public abstract class Process {
    private final Feed resourcesFeed = new Feed();
    private final Utility.Algorithm.CmdStack.Feed cmdStackFeed = new Utility.Algorithm.CmdStack.Feed();

    private final Receiver inputReceiver = new Receiver() {
        @Override
        public void receiveInput(Object input) {
            relayInput(input);
        }
    };

    public Process(Content resources) {
        if (resources == null) {
            throw new IllegalArgumentException("The Resources.Content reference used by a Process can not be null.");
        }

        this.resourcesFeed.setContent(resources);
        this.cmdStackFeed.setContent(new Utility.Algorithm.CmdStack.Content());
    }

    public void pushCommand(String key) {
        cmdStackFeed.getContent().push(key);
    }

    private Content getResources() {
        if (this.resourcesFeed.getContent() == null) {
            throw new IllegalStateException("The command is attempting to execute with a null Resources.Content reference.");

        } else {
            return this.resourcesFeed.getContent();

        }
    }

    public void log(String log) {
        getResources().getLogsFeed().getContent().log(log);
    }

    public void output(String output) {
        getResources().getOutputFeed().getContent().log(output);
    }

    public void input(Input input, Type inputType){
        /*TODO Use input parameter*/
        inputReceiver.deployReader(inputType);
    }

    private void relayInput(Object input){
        /*TODO Build*/
    }

    public void onInputHandled(){
        this.inputReceiver.inputHandled();
    }

    public abstract void onCmdDispatched();

    public Utility.Data.Layer.Content getDataLayer() {
        return getResources().getDataLayerFeed().getContent();
    }

    public Utility.SourceCode.Layer.Content getSourceCodeLayer() {
        return getResources().getSourceCodeLayerFeed().getContent();
    }
}
