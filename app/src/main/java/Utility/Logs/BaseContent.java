package Utility.Logs;

import java.util.ArrayList;

import Utility.Bases.SuperContent;
import Utility.Logs.Feed;

public abstract class BaseContent extends SuperContent<Feed> {
    protected final ArrayList<String> logs = new ArrayList<>();

    public void log(String content) {
        log(content, true);

    }

    public void log(String content, boolean newLine){
        String suffix = newLine? System.lineSeparator() : "";

        this.logs.add(content + suffix);

        if (getFeed() != null){
            getFeed().logAdded();
        }
    }

    public ArrayList<String> getLogs() {
        return new ArrayList<>(this.logs);
    }

    public void clear() {
        logs.clear();

        if (getFeed() != null){
            getFeed().feedRebuilt();
        }
    }
}
