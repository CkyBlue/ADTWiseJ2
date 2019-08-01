package Utility.Algorithm.Tree;

import java.util.HashMap;
import java.util.Set;

import Utility.Algorithm.Commands.Command;
import Utility.Bases.SuperContent;

/*
class OpenAlgorithms{
    private final Set<String> openAlgorithms = new HashSet<>();
    public void hideAlgorithm(String key) {
        if (!algorithms.keySet().contains(key)) {
            throw new IllegalArgumentException("Algorithm object with key " + key + " doesn't exists.");

        } else if (!openAlgorithms.contains(key))
            throw new IllegalArgumentException("Algorithm object with key " + key + " is already hidden.");

        openAlgorithms.remove(key);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.algorithm_hidden, key);
        }
    }

    public void showAlgorithm(String key) {
        if (!algorithms.keySet().contains(key)) {
            throw new IllegalArgumentException("Algorithm object with key " + key + " doesn't exists.");

        } else if (openAlgorithms.contains(key))
            throw new IllegalArgumentException("Algorithm object with key " + key + " is already being shown.");

        openAlgorithms.add(key);

        if (getFeed() != null) {
            getFeed().contentAltered(Alteration.algorithm_unhidden, key);
        }
    }
}*/

public class Content extends SuperContent<Feed> {
    private Command header;

    private final HashMap<String, Utility.Algorithm.Algorithm.Content> algorithms = new HashMap<>();

    public Command getHeader() {
        return header;
    }

    public void setHeader(Command header) {
        this.header = header;
    }

    public void addAlgorithm(String key, Utility.Algorithm.Algorithm.Content algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm object can not be null.");

        } else if (algorithms.keySet().contains(key)) {
            throw new IllegalArgumentException("Algorithm object with key " + key + " already exists.");

        }

        algorithm.setTree(this);
        algorithms.put(key, algorithm);
    }

    public Utility.Algorithm.Algorithm.Content getAlgorithm(String key) {
        if (!algorithms.keySet().contains(key)) {
            throw new IllegalArgumentException("Algorithm object with key " + key + " doesn't exist.");

        }

        return algorithms.get(key);
    }

    public Set<String> getAlgorithmKeys() {
        return this.algorithms.keySet();
    }
}
