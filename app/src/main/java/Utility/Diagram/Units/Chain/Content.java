package Utility.Diagram.Units.Chain;

import java.util.List;

import Utility.Data.Alteration;
import Utility.Data.Nodes.Stack.Printer;
import Utility.Diagram.BaseContent;
import Utility.Diagram.Type;

public abstract class Content<Feed extends Utility.Diagram.Units.Chain.Feed> extends BaseContent<Feed> {
    public static Type diagramType = Type.Chain;
    private boolean chainInitialized = false;

    private String mItemColKey;

    private List<String> mMiscPointerKeys;
    private String mRootPointerKey;

    private Printer mNodesStackPrinter = new Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, String elementKey, int index) {
            diagramContentModified();
        }

        @Override
        public void notifyOfFeedRebuild() {
            diagramContentModified();
        }

        @Override
        public void notifyOfRefreshIntent() {
        }
    };

    private Utility.Data.Variables.Stack.Printer mPointersStackPrinter = new Utility.Data.Variables.Stack.Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, String variableName) {
            if (mMiscPointerKeys != null) {
                if (mMiscPointerKeys.contains(variableName) || (variableName != null && variableName.equals(mRootPointerKey))) {
                    diagramContentModified();
                }
            }
        }

        @Override
        public void notifyOfFeedRebuild() {
            diagramContentModified();
        }

        @Override
        public void notifyOfRefreshIntent() {
        }
    };

    public boolean isChainInitialized() {
        return chainInitialized;
    }

    public void setChainInitialized(boolean isInitialized) {
        this.chainInitialized = isInitialized;
    }

    @Override
    protected void diagramContentModified() {
        if (chainInitialized) {
            super.diagramContentModified();
        }
    }

    public String getItemColKey() {
        return mItemColKey;
    }

    public List<String> getMiscPointerKeys() {
        return mMiscPointerKeys;
    }

    public String getRootPointerKey() {
        return mRootPointerKey;
    }

    public Printer getNodesStackPrinter() {
        return mNodesStackPrinter;
    }

    public Utility.Data.Variables.Stack.Printer getPPointersStackPrinter() {
        return mPointersStackPrinter;
    }

    public void setItemColKey(String mItemColKey) {
        this.mItemColKey = mItemColKey;
    }

    public void setMiscPointerKeys(List<String> mPointerKeys) {
        this.mMiscPointerKeys = mPointerKeys;
    }

    public void setRootPointerKey(String mRootPointerKey) {
        this.mRootPointerKey = mRootPointerKey;
    }
}