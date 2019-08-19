package Utility.Bases;

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
}
