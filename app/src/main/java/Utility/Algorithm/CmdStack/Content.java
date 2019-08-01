package Utility.Algorithm.CmdStack;

import java.util.Stack;

import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private final Stack<String> cmdNames = new Stack<>();

    public void push(String cmdName) {
        if (cmdName != null) {
            cmdNames.push(cmdName);
        }
    }

    public String peek() {
        if (!isEmpty()) {
            return cmdNames.peek();
        }
        return null;
    }

    public String pop() {
        if (!isEmpty()) {
            return cmdNames.pop();
        }
        return null;
    }

    public boolean isEmpty(){
        return cmdNames.isEmpty();
    }
}
