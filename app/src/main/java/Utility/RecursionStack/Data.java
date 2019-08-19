package Utility.RecursionStack;

import java.util.Stack;

public class Data {
    private final Stack<String> callStack = new Stack<>();

    private Controller controller;

    void setController(Controller controller) {
        this.controller = controller;
    }

    public void pushToStack(String functionIdentifier) {
        callStack.push(functionIdentifier);
        dataChanged(Controller.Alteration.pushed_to_atack);
    }

    public void popFromtack() {
        callStack.pop();
        dataChanged(Controller.Alteration.popped_from_stack);
    }

    public int getSize() {
        return callStack.size();
    }

    public String getIdentifier(int index) {
        return callStack.get(index);
    }

    private void dataChanged(Controller.Alteration alterationType) {
        if (this.controller != null) {
            this.controller.notifyOfChangeToData(alterationType);
        }
    }
}
