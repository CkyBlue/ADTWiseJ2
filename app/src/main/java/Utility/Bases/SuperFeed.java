package Utility.Bases;

import java.util.ArrayList;

public abstract class SuperFeed<Content extends SuperContent, Printer extends SuperPrinter> {
    private final ArrayList<Printer> printers = new ArrayList<>();
    private Content content;

    @SuppressWarnings("unchecked")
    public void setContent(Content content) {
        this.content = content;

        if (content != null) {
            try {
                this.content.setFeed(this);
            } catch (Exception e) {
                throw new IllegalStateException("The BaseContent class associated with this BaseFeed class does not recognize this" +
                        " BaseFeed class.");
            }
        }

        feedRebuilt();
    }

    public Content getContent() {
        return content;
    }

    public void addPrinter(Printer printer) {
        if (!this.printers.contains(printer) && printer != null) {
            this.printers.add(printer);
        }
    }

    public void removePrinter(Printer printer) {
        this.printers.remove(printer);
    }

    public ArrayList<Printer> getPrinters() {
        return printers;
    }

    public void refreshIntent() {
        for (Printer printer : getPrinters()){
            printer.notifyOfRefreshIntent();
        }
    }

    public void feedRebuilt() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfFeedRebuild();
        }
    }

    public void constituentUnitModified() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfChangeToConstituentUnit();
        }
    }

    // <--- SuperUnit methods

    private SuperContent composition;

    public void registerComposition(SuperContent composition) {
        this.composition = composition;
    }

    public void alertComposingGroup() {
        if (this.composition != null) {
            this.composition.constituentUnitModified();
        }
    }
    // --->
}
