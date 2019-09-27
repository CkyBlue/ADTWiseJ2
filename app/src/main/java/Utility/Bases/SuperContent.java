package Utility.Bases;

import java.util.ArrayList;

/*TODO Generic bounds for Composition & Constituent*/

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

    private ArrayList<SuperFeed> units = new ArrayList<>();

    protected void constituentUnitModified() {
        if (this.getFeed() != null) {
            this.getFeed().constituentUnitModified();
        }
        this.alertComposingGroupOfChangeToConstituent();
    }

    //    @SuppressWarnings("unchecked")
    public void addUnitFeed(SuperFeed unit) {
        if (unit == null) {
            return;
        }

        try {
            unit.registerComposition(this);
            units.add(unit);

            alertComposingGroupOfChangeToConstituent();

        } catch (Exception e) {
            throw new IllegalStateException("The Unit does not accept this Composition class.");

        }
    }

    //    @SuppressWarnings("unchecked")
    public void removeUnit(SuperFeed unit) {
        if (unit == null) {
            return;
        }

        if (units.contains(unit)) {
            unit.registerComposition(null);
            units.remove(unit);

            alertComposingGroupOfChangeToConstituent();
        }
    }

    //    @SuppressWarnings("unchecked")
    public void clearUnits() {
        for (SuperFeed unit : units) {
            unit.registerComposition(null);
        }

        units.clear();
        alertComposingGroupOfChangeToConstituent();
    }

    public void alertComposingGroupOfChangeToConstituent() {
        if (getFeed() != null) {
            getFeed().alertComposingGroup();
        }
    }

    // --->

}
