package Utility.Bases;

public class SuperContent <Feed extends SuperFeed> {
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
