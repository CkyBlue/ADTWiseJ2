package Utility.Input;

import Utility.Data.Type;

public abstract class Reader {
    private Receiver receiver;
    private boolean readerActive;

    public void returnInput(Object input) {
        if (readerActive) {
            if (this.receiver != null) {
                this.receiver.receiveInput(input);
            }

        } else {
            throw new IllegalStateException("Reader is not active when attempting to returnInput.");

        }
    }

    public Receiver getReceiver() {

        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    protected abstract void buildReader(Type type);

    void deployReader(Type type) {
        if (!readerActive) {
            buildReader(type);
            readerActive = true;

        } else {
            throw new IllegalStateException("Attempting to deploy reader when reader is already active.");

        }
    }

    public boolean isReaderActive() {
        return readerActive;
    }

    protected abstract void destroyReader();

    void closeReader() {
        if (readerActive) {
            destroyReader();
            readerActive = false;

        } else {
            throw new IllegalStateException("Attempting to close reader when reader is already inactive.");

        }
    }
}

