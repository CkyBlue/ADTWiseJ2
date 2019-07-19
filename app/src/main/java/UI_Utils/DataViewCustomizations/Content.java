package UI_Utils.DataViewCustomizations;

import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private Utility.Colors.ColorAdapter.Feed colorFeed = new Utility.Colors.ColorAdapter.Feed();
    private Utility.Colors.ColorAdapter.Printer colorPrinter = new Utility.Colors.ColorAdapter.Printer()
    {
        @Override
        public void notifyOfFeedRebuild() {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().feedRebuilt();
            }
        }

        @Override
        public void notifyOfRefreshIntent() {
            if (Content.this.getFeed() != null) {
                Content.this.getFeed().refreshIntent();
            }
        }
    };

    private UI_Utils.ParamsAdapter.Feed paramsFeed = new UI_Utils.ParamsAdapter.Feed();
    private UI_Utils.ParamsAdapter.Printer paramsPrinter = new UI_Utils.ParamsAdapter.Printer() {
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

    public Content(Utility.Colors.ColorAdapter.Content colorAdapter, UI_Utils.ParamsAdapter.Content paramsAdapter) {
        colorPrinter.setFeed(colorFeed);
        paramsPrinter.setFeed(paramsFeed);

        setColorAdapter(colorAdapter);
        setParamsAdapter(paramsAdapter);
    }

    public Utility.Colors.ColorAdapter.Content getColorAdapter() {
        return colorPrinter.getContent();
    }

    public UI_Utils.ParamsAdapter.Content getParamsContent() {
        return paramsPrinter.getContent();
    }

    public void setColorAdapter(Utility.Colors.ColorAdapter.Content colorAdapter) {
        this.colorFeed.setContent(colorAdapter);
    }

    public void setParamsAdapter(UI_Utils.ParamsAdapter.Content paramsAdapter) {
        this.paramsFeed.setContent(paramsAdapter);
    }
}
