package Utility.Resources;

import Utility.Bases.SuperContent;

public class Content extends SuperContent<Feed> {
    private final Utility.Logs.Feed logsFeed = new Utility.Logs.Feed();
    private final Utility.Logs.Feed outputFeed = new Utility.Logs.Feed();

    private final Utility.SourceCode.Layer.Feed sourceCodeLayerFeed = new Utility.SourceCode.Layer.Feed();
    private final Utility.Data.Layer.Feed dataLayerFeed = new Utility.Data.Layer.Feed();

    public Content() {
        initContents();
    }

    private void initContents() {
        logsFeed.setContent(new Utility.Logs.Logger.Content());
        outputFeed.setContent(new Utility.Logs.Output.Content());

        sourceCodeLayerFeed.setContent(new Utility.SourceCode.Layer.Content());
        dataLayerFeed.setContent(new Utility.Data.Layer.Content());
    }

    public Utility.Logs.Feed getLogsFeed() {
        return logsFeed;
    }

    public Utility.Logs.Feed getOutputFeed() {
        return outputFeed;
    }

    public Utility.SourceCode.Layer.Feed getSourceCodeLayerFeed() {
        return sourceCodeLayerFeed;
    }

    public Utility.Data.Layer.Feed getDataLayerFeed() {
        return dataLayerFeed;
    }

    @Override
    public void refreshIntent() {
        this.logsFeed.getContent().refreshIntent();
        this.outputFeed.getContent().refreshIntent();
        this.dataLayerFeed.getContent().refreshIntent();
        this.sourceCodeLayerFeed.getContent().refreshIntent();

        super.refreshIntent();
    }
}
