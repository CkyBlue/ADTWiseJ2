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

        return null;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return variablesUnits.size();
    }

    public void push() {
        Utility.Data.Variables.Unit.Content unitContent = new Utility.Data.Variables.Unit.Content();

        this.removeUnit(getUnit());
        this.addUnit(unitContent);

        variablesUnits.push(unitContent);
        this.unitFeed.setContent(unitContent);

        unitDelta();
    }

    public boolean pop() {
        if (variablesUnits.size() > 1) {
            this.removeUnit(variablesUnits.pop());
            this.addUnit(getUnit());

            this.unitFeed.setContent(getUnit());
            unitDelta();

            return true;
        }
        return false;
    }

    public Content(String stackName) {
        this.name = stackName;
        this.unitPrinter.setFeed(unitFeed);
        push();
    }

    @Override
    public void refreshIntent() {
        getUnit().refreshIntent();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName());
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