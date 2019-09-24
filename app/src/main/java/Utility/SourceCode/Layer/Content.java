package Utility.SourceCode.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Utility.SourceCode.Unit.Feed> sourceCodeUnits = new HashMap<>();
    private final ArrayList<String> displayingUnits = new ArrayList<>();

    public void buildUnits(Set<String> keys) {
        this.sourceCodeUnits.clear();
        displayingUnits.clear();

        this.clearUnits();

        Utility.SourceCode.Unit.Feed feed;
        Utility.SourceCode.Unit.Content content;

        if (keys != null) {
            for (String key : keys) {
                content = new Utility.SourceCode.Unit.Content();

                feed = new Utility.SourceCode.Unit.Feed();
                feed.setContent(content);

                this.sourceCodeUnits.put(key, feed);
                this.displayingUnits.add(key);
                this.addUnit(content);
            }
        }

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
    }

    public ArrayList<String> getDisplayingUnits() {
        return displayingUnits;
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

    public void hide(String key) {
        validateUnitExists(key);

        if (displayingUnits.contains(key)) {
            displayingUnits.remove(key);
            removeUnit(getUnitFeed(key).getContent());

            if (getFeed() != null) {
                getFeed().hideUnit(key);
            }
        }
    }

    public void show(String key) {
        validateUnitExists(key);

        if (!displayingUnits.contains(key)) {
            displayingUnits.add(key);
            addUnit(getUnitFeed(key).getContent());

            if (getFeed() != null) {
                getFeed().showUnit(key);
            }
        }
    }

    @Override
    public void refreshIntent() {
        for (Utility.SourceCode.Unit.Feed unit : sourceCodeUnits.values()) {
            unit.getContent().refreshIntent();
        }

        super.refreshIntent();
    }

    private void validateUnitExists(String key) {
        if (!getUnitKeys().contains(key)) {
            throw new IllegalArgumentException("No SourceCode.Unit BaseFeed or BaseContent object with key " + key + " exists.");
        }
    }
}
