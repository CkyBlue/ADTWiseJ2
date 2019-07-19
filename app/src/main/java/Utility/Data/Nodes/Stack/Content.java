package Utility.Data.Nodes.Stack;

import java.util.ListIterator;
import java.util.Stack;

import Utility.Bases.SuperContent;
import Utility.Data.Alteration;
import Utility.Data.Nodes.BluePrint;

public class Content extends SuperContent<Feed> {
    private String name;
    private final int defaultSize;
    private final BluePrint bluePrint;

    private final Utility.Data.Nodes.Unit.Feed unitFeed = new Utility.Data.Nodes.Unit.Feed();
    private final Utility.Data.Nodes.Unit.Printer unitPrinter = new Utility.Data.Nodes.Unit.Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, String elementKey, int index) {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().contentAltered(alteration, elementKey, index);
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

    private final Stack<Utility.Data.Nodes.Unit.Content> nodeUnits = new Stack<>();

    public Utility.Data.Nodes.Unit.Content getUnit() {
        if (!nodeUnits.isEmpty()) {
            return nodeUnits.peek();
        }

        throw new IllegalStateException("The stack is empty.");
    }

    public BluePrint getBluePrint() {
        return bluePrint;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return nodeUnits.size();
    }

    public void push() {
        Utility.Data.Nodes.Unit.Content unitContent = new Utility.Data.Nodes.Unit.Content(this.bluePrint, this.defaultSize);

        nodeUnits.push(unitContent);
        this.unitFeed.setContent(unitContent);
    }

    public boolean pop() {
        if (nodeUnits.size() > 1) {
            nodeUnits.pop();
            this.unitFeed.setContent(getUnit());

            return true;
        }
        return false;
    }

    public Content(String name, BluePrint bluePrint, int defaultSize) {
        this.name = name;
        this.defaultSize = defaultSize;
        this.bluePrint = bluePrint;

        this.unitPrinter.setFeed(unitFeed);
        push();
    }

    public void refreshIntent() {
        if (this.getFeed() != null) {
            this.getFeed().refreshIntent();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getName());
        stringBuilder.append(": {\n");

        int count = getSize();
        ListIterator<Utility.Data.Nodes.Unit.Content> listIterator = nodeUnits.listIterator(getSize());

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