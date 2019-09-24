package Utility.Data.Layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Utility.Bases.SuperContent;
import Utility.Bases.SuperFeed;
import Utility.Data.Alteration;
import Utility.Data.Nodes.BluePrint;

import static Utility.Data.Layer.Component.NodesStack;
import static Utility.Data.Layer.Component.VariablesStack;

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Utility.Data.Nodes.Stack.Feed> nodesStackFeedHashMap = new HashMap<>();
    private final HashMap<String, Utility.Data.Variables.Stack.Feed> variableStackFeedHashMap = new HashMap<>();

    private final ArrayList<String> displayingVarStacks = new ArrayList<>();

    public void buildNodesStack(String key, BluePrint bluePrint, int length) {
        validateNoStackExists(NodesStack, key);

        Utility.Data.Nodes.Stack.Content nodesStackContent = new Utility.Data.Nodes.Stack.Content(key, bluePrint, length);
        Utility.Data.Nodes.Stack.Feed nodesStackFeed = new Utility.Data.Nodes.Stack.Feed();

        nodesStackFeed.setContent(nodesStackContent);
        this.addUnit(nodesStackContent);
        this.nodesStackFeedHashMap.put(key, nodesStackFeed);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_added, NodesStack, key);
        }
    }

    public ArrayList<String> getDisplayingVarStacks() {
        return displayingVarStacks;
    }

    public void buildVariablesStack(String key, boolean display) {
        validateNoStackExists(VariablesStack, key);

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

    public void removeStack(Component component, String componentKey) {
        validateStackExists(component, componentKey);
        SuperFeed removedFeed = null;

        if (component == NodesStack) {
            removedFeed = this.nodesStackFeedHashMap.remove(componentKey);


        } else if (component == VariablesStack) {
            removedFeed = this.variableStackFeedHashMap.remove(componentKey);
        }

        if (removedFeed == null) {
            throw new IllegalStateException("Removed " + component + " feed for key " + componentKey + " is null.");
        }

        if (component == VariablesStack && displayingVarStacks.contains(componentKey)) {
            displayingVarStacks.remove(componentKey);
            removeUnit(removedFeed.getContent());
            if (getFeed() != null) {
                getFeed().contentAltered(Alteration.component_removed, component, componentKey);
            }

        } else if (component == NodesStack) {
            removeUnit(removedFeed.getContent());
            if (getFeed() != null) {
                getFeed().contentAltered(Alteration.component_removed, component, componentKey);
            }

        }
    }

    public Utility.Data.Nodes.Stack.Feed getNodesStackFeed(String key) {
        validateStackExists(NodesStack, key);
        return this.nodesStackFeedHashMap.get(key);

    }

    public Utility.Data.Variables.Stack.Feed getVariablesStackFeed(String key) {
        validateStackExists(VariablesStack, key);
        return this.variableStackFeedHashMap.get(key);

    }

    public Set<String> getKeys(Component component) {
        switch (component) {
            case NodesStack: {
                return this.nodesStackFeedHashMap.keySet();

            }
            case VariablesStack: {
                return this.variableStackFeedHashMap.keySet();

            }
        }

        throw new IllegalStateException("Component is not recognized.");
    }

    private void validateStackExists(Component component, String componentKey) {
        if (!stackExists(component, componentKey)) {
            throw new IllegalArgumentException("A " + component + " with key " + componentKey + " doesn't exist.");

        }
    }

    private void validateNoStackExists(Component component, String componentKey) {
        if (stackExists(component, componentKey)) {
            throw new IllegalArgumentException("A " + component + " with key " + componentKey + " already exists.");

        }
    }

    private boolean stackExists(Component component, String componentKey) {
        Object stack = null;

        switch (component) {
            case NodesStack: {
                stack = this.nodesStackFeedHashMap.get(componentKey);
                break;

            }
            case VariablesStack: {
                stack = this.variableStackFeedHashMap.get(componentKey);
                break;

            }
        }

        boolean keyExists = getKeys(component).contains(componentKey);

        if (keyExists && stack == null) {
            throw new IllegalStateException(component + " object for key " + componentKey + " is null.");
        }

        return keyExists;
    }

    public void clear() {
        this.clearUnits();
        this.variableStackFeedHashMap.clear();
        this.nodesStackFeedHashMap.clear();

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
    }

    @Override
    public void refreshIntent() {
        for (Utility.Data.Nodes.Stack.Feed nodesStack : nodesStackFeedHashMap.values()) {
            nodesStack.getContent().refreshIntent();
        }

        for (Utility.Data.Variables.Stack.Feed variablesStack : variableStackFeedHashMap.values()) {
            variablesStack.getContent().refreshIntent();
        }

        super.refreshIntent();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("DataLayer : [\n");

        stringBuilder.append("Nodes : {\n");
        for (String nodesStackKeys : nodesStackFeedHashMap.keySet()) {
            stringBuilder.append(nodesStackFeedHashMap.get(nodesStackKeys).getContent().toString());
            stringBuilder.append("\n");

        }
        stringBuilder.append("}");

        stringBuilder.append("\tVariables : {\n");
        for (String variableStackKeys : variableStackFeedHashMap.keySet()) {
            stringBuilder.append(variableStackFeedHashMap.get(variableStackKeys).getContent().toString());
            stringBuilder.append("\n");

        }
        stringBuilder.append("}\n");
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
