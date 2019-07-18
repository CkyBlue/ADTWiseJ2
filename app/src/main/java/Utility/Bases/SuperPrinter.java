package Utility.Bases;

public abstract class SuperPrinter<Content extends SuperContent, Feed extends SuperFeed> {
    private Feed feed;

    public abstract void notifyOfFeedRebuild();

    public abstract void notifyOfRefreshIntent();

    public Feed getFeed() {
        return feed;
    }

    @SuppressWarnings("unchecked")
    public Content getContent() {
        if (getFeed() == null) {
            return null;
        }

        try {
            return (Content) getFeed().getContent();

        } catch (Exception e) {
            throw new IllegalStateException("The Feed class associated with this Printer class does not return" +
                    " Content class compatible with Printer class.");
        }
    }

    @SuppressWarnings("unchecked")
    public void setFeed(Feed feed) {
        if (this.feed != null) {
            try {
                this.feed.removePrinter(this);
            } catch (Exception e) {
                throw new IllegalStateException("The Feed class associated with this Printer class does not recognize this" +
                        " Printer class.");
            }
        }
        if (feed != null) {
            feed.addPrinter(this);
        }
        this.feed = feed;

        notifyOfFeedRebuild();
    }
}
