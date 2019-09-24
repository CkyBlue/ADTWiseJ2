package Utility.Bases;

import java.util.ArrayList;

public class SuperContent <Feed extends SuperFeed> {
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void refreshIntent(){
        if (this.getFeed() != null) {
            this.getFeed().refreshIntent();
        }
    }

    void setFeed(Feed feed) {
        this.feed = feed;
    }

    // <--- SuperComposition methods

    private ArrayList<SuperContent> units = new ArrayList<>();

    protected void notifyOfChangeToUnit() {
        if (this.getFeed() != null) {
            this.getFeed().changeToUnit();
        }
        this.unitDelta();
    }

    ;

    //    @SuppressWarnings("unchecked")
    public void addUnit(SuperContent unit) {
        try {
            unit.registerComposition(this);
            units.add(unit);

            unitDelta();

        } catch (Exception e) {
            throw new IllegalStateException("The Unit does not accept this Composition class.");

        }
    }

    //    @SuppressWarnings("unchecked")
    public void removeUnit(SuperContent unit) {
        if (units.contains(unit)) {
            unit.registerComposition(null);
            units.remove(unit);

            unitDelta();
        }
    }

    //    @SuppressWarnings("unchecked")
    public void clearUnits() {
        for (SuperContent unit : units) {
            unit.registerComposition(null);
        }

        units.clear();
        unitDelta();
    }

    // --->

    // <--- SuperUnit methods

    private SuperContent composition;

    private void registerComposition(SuperContent composition) {
        this.composition = composition;
    }

    public void unitDelta() {
        if (this.composition != null) {
            this.composition.notifyOfChangeToUnit();
        }
    }

    ;

    // --->
}
