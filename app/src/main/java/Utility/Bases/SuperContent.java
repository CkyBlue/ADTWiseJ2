package Utility.Bases;

import java.util.ArrayList;

public class SuperContent <Feed extends SuperFeed> {
    private Feed feed;
    private SuperContent composition;
    private ArrayList<SuperContent> units;

    public Feed getFeed() {
        return feed;
    }

    public void refreshIntent(){
        if (this.getFeed() != null) {
            this.getFeed().refreshIntent();
        }
    }

    protected void registerComposition(SuperContent composition) {
        this.composition = composition;
    }

    ;

    protected void addUnit(SuperContent content) {
    }

    ;

    protected void removeUnit(SuperContent content) {
    }

    ;

    void setFeed(Feed feed) {
        this.feed = feed;
    }
}
