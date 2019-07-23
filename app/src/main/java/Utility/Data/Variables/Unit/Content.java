package Utility.Data.Variables.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Utility.Data.Alteration;
import Utility.Data.Type;
import Utility.Bases.SuperContent;

import static Utility.Data.Utilities.getTypeDefault;
import static Utility.Data.Utilities.validateAsNonNullData;

/*TODO Test Content, then delegated Printer of Stack*/

public class Content extends SuperContent<Feed> {
    public enum Column implements Utility.Key {
        Identifier,
        Value;
    }

    private final List<String> variable_names = new ArrayList<String>();

    private final HashMap<String, Object> variable_values = new HashMap<>();

    private final HashMap<String, Type> variable_types = new HashMap<>();

    public String[] getVariableNames() {
        return variable_names.toArray(new String[0]);
    }

    public void clear() {
        variable_names.clear();
        variable_types.clear();
        variable_values.clear();

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
    }

    public void declareVariable(String variableName, Type variableType) {
        if (variable_names.contains(variableName)) {
            throw new IllegalArgumentException("A variable with the name '" + variableName + "' already exists.");

        }

        variable_types.put(variableName, variableType);
        variable_names.add(variableName);

        Object initialValue = getTypeDefault(variableType);
        validateAsNonNullData(initialValue);

        variable_values.put(variableName, initialValue);
        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_added, variableName);
        }

    }

    public void removeVariable(String variableName) {
        variable_names.remove(variableName);
        variable_types.remove(variableName);
        variable_names.remove(variableName);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_removed, variableName);
        }
    }

    public void set(String variableName, String value) {
        value = value == null ? "" : value;
        internalSet(variableName, Type.STRING, value);
    }

    public void set(String variableName, int value) {
        internalSet(variableName, Type.INTEGER, value);
    }

    public void set(String variableName, float value) {
        internalSet(variableName, Type.FLOAT, value);
    }

    public void set(String variableName, boolean value) {
        internalSet(variableName, Type.BOOLEAN, value);
    }

    public String getStr(String variableName) {
        return (String) internalGet(variableName, Type.STRING);
    }

    public int getInt(String variableName) {
        return (Integer) internalGet(variableName, Type.INTEGER);
    }

    public boolean getBool(String variableName) {
        return (Boolean) internalGet(variableName, Type.BOOLEAN);
    }

    public float getFloat(String variableName) {
        return (Float) internalGet(variableName, Type.FLOAT);
    }

    public Type getType(String variableName) {
        validateVariableExists(variableName);
        return this.variable_types.get(variableName);
    }

    public String getStrEqv(String variableName) {
        Type type = getType(variableName);

        Object value = internalGet(variableName, type);
        validateAsNonNullData(value);

        return String.valueOf(value);
    }

    private void validateVariableType(String variableName, Type type) {
        Type realType = variable_types.get(variableName);

        if (realType == null) {
            throw new IllegalStateException("'" + variableName + "' has null type associated with it..");

        } else if (realType != type) {
            throw new IllegalArgumentException("'" + variableName + "' is associated with type " + realType +
                    " but you are attempting to use it for a " + type + " type value.");
        }
    }

    private void validateVariableExists(String variableName) {
        if (!variable_names.contains(variableName)) {
            throw new IllegalArgumentException("'" + variableName + "' is not a valid key.");

        }
    }

    private void validateAddressAndType(String variableName, Type type){
        validateVariableExists(variableName);
        validateVariableType(variableName, type);
    }

    private Object internalGet(String variableName, Type type) {
        validateAddressAndType(variableName, type);
        return variable_values.get(variableName);
    }

    private void internalSet(String variableName, Type type, Object value) {
        validateAddressAndType(variableName, type);
        validateAsNonNullData(value);

        variable_values.put(variableName, value);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_value_updated, variableName);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (String variableName : getVariableNames()) {
            stringBuilder.append("[");

            stringBuilder.append(variableName);
            stringBuilder.append(" : ( Type = ");
            stringBuilder.append(getType(variableName));
            stringBuilder.append(", Value = ");
            stringBuilder.append(getStrEqv(variableName));
            stringBuilder.append(")");


            stringBuilder.append("], ");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}