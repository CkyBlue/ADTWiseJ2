package Utility.Algorithm.Tree;

import java.util.HashMap;
import java.util.Set;

import Utility.Algorithm.Commands.Command;
import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private Command initializer;

    private final HashMap<String, Utility.Algorithm.Commands.Command> algorithmHeaders = new HashMap<>();

    public Command getInitializer() {
        return initializer;
    }

    public void setInitializer(Command initializer) {
        this.initializer = initializer;
    }

    public void addAlgorithmHeader(String key, Utility.Algorithm.Commands.Command algorithmHeader) {
        if (algorithmHeader == null) {
            throw new IllegalArgumentException("Algorithm header can not be null.");

        } else if (algorithmHeaders.keySet().contains(key)) {
            throw new IllegalArgumentException("Algorithm header with key " + key + " already exists.");

        }

        algorithmHeaders.put(key, algorithmHeader);
    }

    public Utility.Algorithm.Commands.Command getAlgorithmHeader(String key) {
        if (!algorithmHeaders.keySet().contains(key)) {
            throw new IllegalArgumentException("Algorithm header with key " + key + " doesn't exist.");

        }

        return algorithmHeaders.get(key);
    }

    public Set<String> getAlgorithmKeys() {
        return this.algorithmHeaders.keySet();
    }
}
