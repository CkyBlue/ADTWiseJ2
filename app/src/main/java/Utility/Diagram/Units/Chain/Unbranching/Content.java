package Utility.Diagram.Units.Chain.Unbranching;

import java.util.List;

import Utility.Diagram.Type;
import Utility.Diagram.Units.Chain.Feed;

public class Content extends Utility.Diagram.Units.Chain.Content<Feed> {
    public static Type diagramType = Type.UnbranchingChain;
    private String mPointerColKey;

    public void readChain(Utility.Data.Nodes.Stack.Feed nodesStackFeed,
                          String itemColKey, String pointerColKey,
                          Utility.Data.Variables.Stack.Feed pointerStackFeed,
                          String rootKey, List<String> pointerKeys) {
        // callBacks notifying of modifications are swallowed until flag is negated
        setChainInitialized(false);

        getNodesStackPrinter().setFeed(nodesStackFeed);
        getPPointersStackPrinter().setFeed(pointerStackFeed);

        setItemColKey(itemColKey);
        mPointerColKey = pointerColKey;

        setRootPointerKey(rootKey);
        setMiscPointerKeys(pointerKeys);

        setChainInitialized(true);
        diagramContentModified();
    }

    public String getPointerColKey() {
        return mPointerColKey;
    }
}