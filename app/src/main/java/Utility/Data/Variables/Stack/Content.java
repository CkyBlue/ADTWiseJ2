package Utility.Data.Variables.Stack;

import java.util.ListIterator;
import java.util.Stack;

import Utility.Bases.SuperContent;
import Utility.Data.Alteration;

public class Content extends SuperContent<Feed> {
    private String name;

    private final Utility.Data.Variables.Unit.Feed unitFeed = new Utility.Data.Variables.Unit.Feed();
    private final Utility.Data.Variables.Unit.Printer unitPrinter = new Utility.Data.Variables.Unit.Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, String variableName) {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().contentAltered(alteration, variableName);
            }
        }

        @Override
        public void notifyOfRefreshIntent() {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().refreshIntent();
            }
        }

        @Override
        public void notifyOfFeedRebuild() {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().feedRebuilt();
            }
        }
    };

    private final Stack<Utility.Data.Variables.Unit.Content> variablesUnits = new Stack<>();

    public Utility.Data.Variables.Unit.Content getUnit() {
        if (!variablesUnits.isEmpty()) {
            return variablesUnits.peek();
        }

        throw new IllegalStateException("The stack is empty.");
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return variablesUnits.size();
    }

    public void push() {
        Utility.Data.Variables.Unit.Content unitContent = new Utility.Data.Variables.Unit.Content();

        variablesUnits.push(unitContent);
        this.unitFeed.setContent(unitContent);
    }

    public boolean pop() {
        if (variablesUnits.size() > 1) {
            variablesUnits.pop();
            this.unitFeed.setContent(getUnit());

            return true;
        }
        return false;
    }

    public Content(String name) {
        this.name = name;
        this.unitPrinter.setFeed(unitFeed);
        push();
    }

    @Override
    public void refreshIntent() {
        getUnit().refreshIntent();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getName());
        stringBuilder.append(": {\n");

        int count = getSize();
        ListIterator<Utility.Data.Variables.Unit.Content> listIterator = variablesUnits.listIterator(getSize());

        while (listIterator.hasPrevious()) {
            stringBuilder.append("Layer ");
            stringBuilder.append(count--);
            stringBuilder.append(": ");
            stringBuilder.append(listIterator.previous().toString());
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}