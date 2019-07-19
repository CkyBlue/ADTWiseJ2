package UI_Utils.CustomViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;
import java.util.List;

import UI_Utils.DataAdapter;
import UI_Utils.DataViewCustomizations.Feed;
import UI_Utils.DataViewCustomizations.Printer;
import Utility.Data.Alteration;

public class NodesStackView extends FrameLayout {
    String TAG = "Info " + getClass().getName();

    public boolean notified = false;

    NodesAdapter nodesAdapter;
    private ListView listView;
    private List<Integer> listAdapterIndices = new ArrayList<>();

    private Printer customizationsPrinter = new Printer() {
        String TAG = "Info " + "NodesStackView.customizationsPrinter";

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");

            NodesStackView.this.notifyOfRefreshIntent();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");

            NodesStackView.this.nodesAdapter.setCustomizations(getContent());
            NodesStackView.this.notifyOfFeedRebuild();
        }
    };

    private Utility.Data.Nodes.Stack.Printer nodesStackPrinter = new Utility.Data.Nodes.Stack.Printer() {
        private String TAG = "Info " + "NodesStackView.nodesStackPrinter";

        @Override
        public void notifyOfContentAlteration(Alteration alteration, String elementKey, int index) {
            Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + elementKey + ", " + index + ")");

            NodesStackView.this.notifyOfContentAlteration(alteration, elementKey, index);
        }

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");

            NodesStackView.this.notifyOfRefreshIntent();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");

            NodesStackView.this.nodesAdapter.setNodesStackContent(getContent());
            NodesStackView.this.notifyOfFeedRebuild();
        }
    };

    public void setCustomizationsFeed(Feed customizationsFeed) {
        this.customizationsPrinter.setFeed(customizationsFeed);
    }

    public void setNodesStackFeed(Utility.Data.Nodes.Stack.Feed nodesStackFeed) {
        this.nodesStackPrinter.setFeed(nodesStackFeed);
    }

    public Feed getCustomizationsFeed() {
        return this.customizationsPrinter.getFeed();
    }

    public Utility.Data.Nodes.Stack.Feed getNodesFeed() {
        return this.nodesStackPrinter.getFeed();
    }

    public void rebuildView() {
        Logger.log(TAG, "rebuildView()");

        refreshView();
    }

    public void refreshView() {
        Logger.log(TAG, "refreshView()");

        listAdapterIndices.clear();
        if (nodesStackPrinter.getUnit() != null) {

            for (int i = 0; i < nodesStackPrinter.getUnit().getSize(); i++) {
                listAdapterIndices.add(i);

            }
        }

        nodesAdapter.notifyDataSetChanged();
    }

    public void notifyOfRefreshIntent() {
        Logger.log(TAG, "notifyOfRefreshIntent()");

        if (notified) {
            refreshView();
            notified = false;
        }
    }

    public void notifyOfContentAlteration(Alteration alteration, String elementKey, int index) {
        Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + elementKey + ", " + index + ")");

        notified = true;
    }

    public void notifyOfFeedRebuild() {
        Logger.log(TAG, "notifyOfFeedRebuild()");

        rebuildView();
    }

    private void init() {
        nodesAdapter = new NodesAdapter(getContext(), 0, listAdapterIndices, nodesStackPrinter.getContent());

        listView = new ListView(getContext());
        listView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        this.addView(listView);
        listView.setAdapter(nodesAdapter);

        rebuildView();
    }

    public NodesStackView(Context context) {
        super(context);
        init();
    }

    public NodesStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NodesStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NodesStackView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}

class NodesAdapter extends DataAdapter<Integer> {
    private Utility.Data.Nodes.Stack.Content nodesStackContent;

    public NodesAdapter(Context context, int resource, List<Integer> objects, Utility.Data.Nodes.Stack.Content nodesStack) {
        super(context, resource, objects);
        nodesStackContent = nodesStack;
    }

    public void setNodesStackContent(Utility.Data.Nodes.Stack.Content nodesStackContent) {
        this.nodesStackContent = nodesStackContent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout rowLinearLayout;

        String data;

        Integer index = getItem(position);
        if (index == null) {
            throw new IllegalStateException("The indices passed to NodesAdapter cannot contain a null value.");

        }

        if (convertView == null) {
            /*If no view available for recycling, create one*/

            rowLinearLayout = new LinearLayout(getContext());
            rowLinearLayout.setId(View.generateViewId());
            rowLinearLayout.setLayoutParams(getParamsAdapter().getRowParams());

            for (String elementKey : nodesStackContent.getBluePrint().getKeys()) {
                data = nodesStackContent.getUnit().getStrEqv(elementKey, index);

                rowLinearLayout.addView(createView(elementKey,
                        data, position));
            }

        } else {
            rowLinearLayout = (LinearLayout) convertView;
            rowLinearLayout.setLayoutParams(getParamsAdapter().getRowParams());

            TextView childTextView;

            int count = 0;
            for (String elementKey : nodesStackContent.getBluePrint().getKeys()) {
                data = nodesStackContent.getUnit().getStrEqv(elementKey, index);

                childTextView = (TextView) rowLinearLayout.getChildAt(count);
                childTextView.setText(data);
                applyCustomizationsToChildView(elementKey,
                        data, position, childTextView);

                count++;
            }
        }

        return rowLinearLayout;
    }
}