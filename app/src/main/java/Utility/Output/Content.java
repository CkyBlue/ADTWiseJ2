package Utility.Output;

import java.util.ArrayList;

import Utility.Bases.SuperContent;

/*Note that clear() does not notify the printer since it is meant to be use by the printer and so that past logs aren't cleared
* unless new logs are added.*/

public class Content extends SuperContent<Feed> {
    private final ArrayList<String> logs = new ArrayList<>();

    public void log(String content) {
        this.logs.add(content);

        if (getFeed() != null){
            getFeed().logAdded();
        }
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    public void clear() {
        logs.clear();
    }
}
