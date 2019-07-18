package Utility.Data.Nodes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Utility.Data.Type;

public class BluePrint {
    private final HashMap<String, Type> types  = new HashMap<>();
    private final List<String> keys = new LinkedList<>();

    public Type getType(String key) {
        if (keys.contains(key)) {
            return types.get(key);

        }
        return null;
    }

    public void addKey(String key, Type type){
        if (keys.contains(key)){
            throw new IllegalArgumentException("A key with the name '" + key + "' already exists.");
        }

        keys.add(key);
        types.put(key, type);
    }

    public List<String> getKeys() {
        return keys;
    }
}
