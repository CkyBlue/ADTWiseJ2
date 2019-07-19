package Utility.Data.Layer;

import java.util.HashMap;
import java.util.Set;

import Utility.Bases.SuperContent;
import Utility.Data.Alteration;
import Utility.Data.Nodes.BluePrint;

import static Utility.Data.Layer.Component.NodesStack;
import static Utility.Data.Layer.Component.VariablesStack;

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Utility.Data.Nodes.Stack.Content> nodesStackHashMap = new HashMap<>();
    private final HashMap<String, Utility.Data.Variables.Stack.Content> variableStackHashMap = new HashMap<>();

    public void buildNodesStack(String key, BluePrint bluePrint, int length) {
        validateNoStackExists(VariablesStack, key);

        Utility.Data.Nodes.Stack.Content nodesStack = new Utility.Data.Nodes.Stack.Content(key, bluePrint, length);
        this.nodesStackHashMap.put(key, nodesStack);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_added, NodesStack, key);

        }
    }

    public void buildVariablesStack(String key) {
        validateNoStackExists(NodesStack, key);

        Utility.Data.Variables.Stack.Content variablesStack = new Utility.Data.Variables.Stack.Content(key);
        this.variableStackHashMap.put(key, variablesStack);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_added, Component.VariablesStack, key);

        }
    }

    public void removeStack(Component component, String componentKey) {
        validateStackExists(component, componentKey);

        if (component == NodesStack) {
            this.nodesStackHashMap.remove(componentKey);

        } else if (component == VariablesStack) {
            this.variableStackHashMap.remove(componentKey);

        }

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_removed, component, componentKey);

        }
    }

    public Utility.Data.Nodes.Stack.Content getNodesStack(String key) {
        validateStackExists(NodesStack, key);
        return  this.nodesStackHashMap.get(key);

    }

    public Utility.Data.Variables.Stack.Content getVariablesStack(String key) {
        validateStackExists(VariablesStack, key);
        return  this.variableStackHashMap.get(key);

    }

    public Set<String> getKeys(Component component) {
        switch (component) {
            case NodesStack: {
                return this.nodesStackHashMap.keySet();

            }
            case VariablesStack: {
                return this.variableStackHashMap.keySet();

            }
        }

        throw new IllegalStateException("Component is not recognized.");
    }

    public void clear() {
        this.variableStackHashMap.clear();
        this.nodesStackHashMap.clear();

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
    }

    private void validateStackExists(Component component, String componentKey){
        if (!stackExists(component, componentKey)) {
            throw new IllegalArgumentException("A " + component + " with key " + componentKey + " doesn't exist.");

        }
    }

    private void validateNoStackExists(Component component, String componentKey){
        if (stackExists(component, componentKey)) {
            throw new IllegalArgumentException("A " + component + " with key " + componentKey + " already exists.");

        }
    }

    private boolean stackExists(Component component, String componentKey) {
        Object stack = null;

        switch (component) {
            case NodesStack: {
                stack = this.nodesStackHashMap.get(componentKey);
                break;

            }
            case VariablesStack: {
                stack = this.variableStackHashMap.get(componentKey);
                break;

            }
        }

        boolean keyExists = getKeys(component).contains(componentKey);

        if (keyExists && stack == null){
            throw new IllegalStateException(component + " object for key " + componentKey + " is null.");
        }

        return keyExists;
    }
}
