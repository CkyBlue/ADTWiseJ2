package Utility.Algorithm.Algorithm;

import java.util.HashMap;

import Utility.Algorithm.Commands.Command;
import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Command> commandsHashMap = new HashMap<>();

    private String head;
    private TreeContent tree;

    void setTree(TreeContent tree) {
        this.tree = tree;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public TreeContent getTree() {
        return tree;
    }

    public void addCommand(Command command) {
        if (!commandsHashMap.keySet().contains(command.getName())) {
            command.setAlgorithm(this);
            commandsHashMap.put(command.getName(), command);

        } else {
            throw new IllegalArgumentException("A Command with the name " + command.getName() + " has already been added to this algorithm.");

        }
    }
}