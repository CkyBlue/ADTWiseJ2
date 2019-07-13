package Utility.SourceCode.Layer;

import java.util.HashMap;
import java.util.Set;

import Utility.SuperContent;
import Utility.SuperFeed;
import Utility.SuperPrinter;

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Utility.SourceCode.Unit.Feed> sourceCodeUnits = new HashMap<>();

    public void buildUnits(Set<String> keys) {
        this.sourceCodeUnits.clear();
        Utility.SourceCode.Unit.Feed feed;
        Utility.SourceCode.Unit.Content content;

        if (keys != null) {
            for (String key : keys) {
                content = new Utility.SourceCode.Unit.Content();

                feed = new Utility.SourceCode.Unit.Feed();
                feed.setContent(content);

                this.sourceCodeUnits.put(key, feed);
            }
        }

        if (getFeed() != null){
            getFeed().feedRebuilt();
        }
    }

    public Utility.SourceCode.Unit.Feed getUnitFeed(String key) {
        validateUnitExists(key);

        Utility.SourceCode.Unit.Feed feed = this.sourceCodeUnits.get(key);
        if (feed != null) {
            return feed;
        }

        return null;
    }

    public Set<String> getUnitKeys() {
        return this.sourceCodeUnits.keySet();
    }

    public void clear() {
        buildUnits(null);
    }

    private void validateUnitExists(String key) {
        if (!getUnitKeys().contains(key)) {
            throw new IllegalArgumentException("No SourceCode.Unit Feed or Content object with key " + key + " exists.");
        }
    }
}