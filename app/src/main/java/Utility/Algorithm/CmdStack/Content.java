package Utility.Algorithm.CmdStack;

import java.util.ListIterator;
import java.util.Stack;

import Utility.Algorithm.Commands.Command;
import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private final Stack<Command> cmds = new Stack<>();

    public void push(Command cmd) {
        if (cmd != null) {
            cmds.push(cmd);
        }
    }

    public Command peek() {
        if (!isEmpty()) {
            return cmds.peek();
        }
        return null;
    }

    public Command pop() {
        if (!isEmpty()) {
            return cmds.pop();
        }
        return null;
    }

    public void clear() {
        this.cmds.clear();
    }

    public boolean isEmpty() {
        return cmds.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        int count = cmds.size();
        ListIterator<Command> listIterator = cmds.listIterator(cmds.size());

        while (listIterator.hasPrevious()) {
            stringBuilder.append("Cmd ");
            stringBuilder.append(count--);
            stringBuilder.append(": ");
            stringBuilder.append(listIterator.previous().toString());
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
