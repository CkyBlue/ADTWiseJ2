package Implementations;

import java.util.HashMap;

import Utility.Algorithm.Tree.Content;

public class Implementations {
    public static HashMap<String, Content> algorithmTrees = new HashMap<>();

    static {
        algorithmTrees.put("Queue", Queue.algorithmTree);
    }
}
