package Utility.Diagram.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Utility.Bases.SuperContent;
import Utility.Diagram.Type;

/*TODO Adapt for Type SuperBases instead of just the Chain subclass*/

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Utility.Diagram.Units.Chain.Feed> diagramUnitFeeds = new HashMap<>();

    public void addDiagramUnit(String diagramName, Type diagramType, Utility.Diagram.Units.Chain.Feed diagramFeed) {


        Utility.Data.Variables.Stack.Content variablesStackContent = new Utility.Data.Variables.Stack.Content(key);
        Utility.Data.Variables.Stack.Feed variablesStackFeed = new Utility.Data.Variables.Stack.Feed();

        variablesStackFeed.setContent(variablesStackContent);
        this.variableStackFeedHashMap.put(key, variablesStackFeed);

        if (display) {
            displayingVarStacks.add(key);
            this.addUnit(variablesStackContent);
            if (getFeed() != null) {
                getFeed().contentAltered(Alteration.component_added, Component.VariablesStack, key);
            }
        }
    }

}

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
