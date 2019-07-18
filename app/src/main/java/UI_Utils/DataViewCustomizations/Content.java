package UI_Utils.DataViewCustomizations;

import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private Utility.Colors.Chrome.Feed chromeFeed = new Utility.Colors.Chrome.Feed();
    private Utility.Colors.Chrome.Printer chromePrinter = new Utility.Colors.Chrome.Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().feedRebuilt();
            }
        }
    };

    private UI_Utils.ParamsAdapter.Feed paramsFeed = new UI_Utils.ParamsAdapter.Feed();
    private UI_Utils.ParamsAdapter.Printer paramsPrinter = new UI_Utils.ParamsAdapter.Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().feedRebuilt();
            }
        }
    };

    public Content(Utility.Colors.Chrome.Content chromeContent, UI_Utils.ParamsAdapter.Content paramsContent) {
        chromePrinter.setFeed(chromeFeed);
        paramsPrinter.setFeed(paramsFeed);

        setChromeContent(chromeContent);
        setParamsContent(paramsContent);
    }

    public Utility.Colors.Chrome.Content getChromeContent() {
        return chromePrinter.getContent();
    }

    public UI_Utils.ParamsAdapter.Content getParamsContent() {
        return paramsPrinter.getContent();
    }

    public void setChromeContent(Utility.Colors.Chrome.Content chromeContent) {
        this.chromeFeed.setContent(chromeContent);
    }

    public void setParamsContent(UI_Utils.ParamsAdapter.Content paramsContent) {
        this.paramsFeed.setContent(paramsContent);
    }
}
