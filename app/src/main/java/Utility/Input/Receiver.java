package Utility.Input;

import Utility.Data.Type;

public abstract class Receiver {
    private Reader reader;

    public boolean isReaderActive() {
        return reader != null && reader.isReaderActive();
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        if (this.reader != null) {
            if (isReaderActive()) {
                throw new IllegalStateException("The current reader is still active.");

            }

            this.reader.setReceiver(null);
        }

        if (reader != null) {
            reader.setReceiver(this);
        }

        this.reader = reader;
    }

    public void deployReader(Type type) {
        if (this.reader == null) {
            throw new IllegalStateException("Attempting to deploy reader when no reader is set.");

        } else if (isReaderActive()) {
            throw new IllegalStateException("Attempting to deploy reader when reader is already active.");

        } else {
            this.reader.deployReader(type);
        }
    }

    public abstract void receiveInput(Object input);

    public void inputHandled() {
        if (this.reader != null && isReaderActive()) {
            this.reader.closeReader();

        } else {
            throw new IllegalStateException("No active reader.");
        }
    }

}
