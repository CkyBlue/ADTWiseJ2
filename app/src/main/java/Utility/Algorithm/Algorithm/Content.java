package Utility.Algorithm.Algorithm;

import java.util.HashMap;

import Utility.Algorithm.Commands.Command;
import Utility.Bases.SuperContent;

/*TODO Algorithm class is unnecessary*/

public class Content extends SuperContent<Feed> {
    private final HashMap<String, Command> commandsHashMap = new HashMap<>();

    private Command head;
    private TreeContent tree;

    void setTree(TreeContent tree) {
        this.tree = tree;
    }

    public Command getHead() {
        return head;
    }

    public void setHead(Command head) {
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