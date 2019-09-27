package Utility.Data.Nodes.Unit;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Utility.Bases.SuperContent;
import Utility.Data.Alteration;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;
import Utility.Data.Utilities;

import static Utility.Data.Utilities.validateAsNonNullData;

public class Content extends SuperContent<Feed> {
    public enum Column implements Utility.Key {
        Index, Pointer;
    }

    private int size = 0;
    private BluePrint bluePrint;
    private final HashMap<String, ArrayList> node_values = new HashMap<>();

    public Content(BluePrint bluePrint, int length) {
        init(bluePrint, length);
    }

    public BluePrint getBluePrint() {
        return bluePrint;
    }

    public List<String> getKeys() {
        return getBluePrint().getKeys();
    }

    @SuppressWarnings("unchecked")
    public void addNode(int index) {
        for (String element_key : getKeys()) {
            ArrayList arrayList = node_values.get(element_key);

            if (arrayList == null) {
                throw new IllegalStateException("The ArrayLists used for storing the values within nodes can not be null.");

            } else {
                if (index == getSize()) {
                    Logger.log("addNode", "index == getSize()");
                    arrayList.add(Utilities.getTypeDefault(getBluePrint().getType(element_key)));

                } else if (index < getSize() && index >= 0) {
                    Logger.log("addNode", "index < getSize() && index >= 0");
                    arrayList.add(index, Utilities.getTypeDefault(getBluePrint().getType(element_key)));

                } else {
                    throw new IllegalArgumentException("The index must be such that: 0  < index <= current size of the Nodes object.");
                }
            }
        }

        size++;

        if (getFeed() != null) {
            for (String element_key : getKeys()) {
                getFeed().contentAltered(Alteration.component_added, element_key, index);
            }
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public void addNode() {
        addNode(getSize());
    }

    public int getSize() {
        return size;
    }

    public void removeNode(int index) {
        validateIndexExists(index);

        for (String element_key : getKeys()) {
            ArrayList arrayList = node_values.get(element_key);

            if (arrayList == null) {
                throw new IllegalStateException("The ArrayLists used for storing the values within nodes can not be null.");

            } else {
                arrayList.remove(index);

            }
        }

        size--;

        if (getFeed() != null) {
            for (String element_key : getKeys()) {
                getFeed().contentAltered(Alteration.component_removed, element_key, index);
            }
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public void clear() {
        size = 0;

        this.node_values.clear();
        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    @SuppressWarnings("unchecked")
    private void init(BluePrint bluePrint, int length) {
        if (bluePrint == null) {
            throw new IllegalArgumentException("BluePrint object cannot be null.");
        }

        this.bluePrint = bluePrint;

        this.node_values.clear();
        for (String element_key : this.bluePrint.getKeys()) {
            switch (this.bluePrint.getType(element_key)) {
                case INTEGER: {
                    this.node_values.put(element_key, new ArrayList<Integer>());
                    break;
                }
                case BOOLEAN: {
                    this.node_values.put(element_key, new ArrayList<Boolean>());
                    break;
                }
                case FLOAT: {
                    this.node_values.put(element_key, new ArrayList<Float>());
                    break;
                }
                case STRING: {
                    this.node_values.put(element_key, new ArrayList<String>());
                    break;
                }
            }

            ArrayList arrayList = node_values.get(element_key);

            if (arrayList == null) {
                throw new IllegalStateException("The ArrayLists used for storing the values within nodes can not be null.");

            } else {
                for (int i = 0; i < length; i++) {
                    arrayList.add(Utilities.getTypeDefault(getBluePrint().getType(element_key)));
                }

            }
        }
        size = length;

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    public void set(String elementKey, int index, String value) {
        value = value == null ? "" : value;
        internalSet(elementKey, index, Type.STRING, value);
    }

    public void set(String elementKey, int index, int value) {
        internalSet(elementKey, index, Type.INTEGER, value);
    }

    public void set(String elementKey, int index, float value) {
        internalSet(elementKey, index, Type.FLOAT, value);
    }

    public void set(String elementKey, int index, boolean value) {
        internalSet(elementKey, index, Type.BOOLEAN, value);
    }

    public String getStr(String elementKey, int index) {
        return (String) internalGet(elementKey, index, Type.STRING);
    }

    public int getInt(String elementKey, int index) {
        return (Integer) internalGet(elementKey, index, Type.INTEGER);
    }

    public boolean getBool(String elementKey, int index) {
        return (Boolean) internalGet(elementKey, index, Type.BOOLEAN);
    }

    public float getFloat(String elementKey, int index) {
        return (Float) internalGet(elementKey, index, Type.FLOAT);
    }

    public Type getType(String elementKey) {
        validateElementKeyExists(elementKey);
        return getBluePrint().getType(elementKey);
    }

    public String getStrEqv(String elementKey, int index) {
        Type type = getType(elementKey);

        Object value = internalGet(elementKey, index, type);
        validateAsNonNullData(value);

        return String.valueOf(value);
    }

    private Object internalGet(String elementKey, int index, Type type) {
        validateAddressAndType(elementKey, index, type);

        ArrayList arrayList = node_values.get(elementKey);
        if (arrayList == null) {
            throw new IllegalStateException("The ArrayLists used for storing the values within nodes can not be null.");

        } else {
            return arrayList.get(index);

        }
    }

    @SuppressWarnings("unchecked")
    private void internalSet(String elementKey, int index, Type type, Object value) {
        validateAddressAndType(elementKey, index, type);
        validateAsNonNullData(value);

        ArrayList arrayList = node_values.get(elementKey);
        if (arrayList == null) {
            throw new IllegalStateException("The ArrayLists used for storing the values within nodes can not be null.");

        } else {
            arrayList.set(index, value);

        }

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.component_value_updated, elementKey, index);
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    private void validateAddressAndType(String elementKey, int index, Type type) {
        validateElementKeyExists(elementKey);
        validateIndexExists(index);
        validateDataType(elementKey, type);
    }

    private void validateDataType(String elementKey, Type type) {
        Type realType = getBluePrint().getType(elementKey);

        if (realType == null) {
            throw new IllegalStateException("'" + elementKey + "' has null type associated with it..");

        } else if (realType != type) {
            throw new IllegalArgumentException("'" + elementKey + "' is associated with type " + realType +
                    " but you are attempting to use it for a " + type + " type value.");
        }
    }

    private void validateElementKeyExists(String elementKey) {
        if (!getKeys().contains(elementKey)) {
            throw new IllegalArgumentException("'" + elementKey + "' is not a valid key.");

        }
    }

    private void validateIndexExists(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException(index + " is not a valid index.");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int index = 0; index < getSize(); index++) {
            stringBuilder.append(index);
            stringBuilder.append(" : [");

            for (String elementKey : getKeys()) {

                stringBuilder.append(elementKey);
                stringBuilder.append(" = ");
                stringBuilder.append(getStrEqv(elementKey, index));
                stringBuilder.append(",");

            }
            stringBuilder.append("], \n");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
