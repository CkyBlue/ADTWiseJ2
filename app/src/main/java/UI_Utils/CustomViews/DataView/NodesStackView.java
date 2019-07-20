package UI_Utils.CustomViews.DataView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;
import java.util.List;

import UI_Utils.CustomViews.DataView.Customizations.Feed;
import UI_Utils.CustomViews.DataView.Customizations.Printer;
import UI_Utils.CustomViews.DataView.ParamsAdapter.Themes;
import Utility.Data.Alteration;

import static UI_Utils.CustomViews.DataView.Utilities.applyCustomizations;

/*TODO Header port*/
/*TODO Build abstract superclass for StackViews for common default alts logic*/

public class NodesStackView extends LinearLayout {
    String TAG = "Info " + getClass().getName();

    public boolean notified = false;

    DataAdapter dataAdapter;
    private ListView listView;
    private LinearLayout headerRow;

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

            NodesStackView.this.dataAdapter.setCustomizations(getContent());
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

            NodesAdapter nodesAdapter = (NodesAdapter) dataAdapter;

            nodesAdapter.setNodesStackContent(getContent());
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

        this.headerRow.removeAllViews();

        if (nodesStackPrinter.getUnit() != null) {
            for (String key : nodesStackPrinter.getUnit().getBluePrint().getKeys()) {
                TextView tv = new TextView(this.getContext());

                applyCustomizations("Header", key, 0, tv, );

                tv.setGravity(Gravity.CENTER);
                tv.setText(content);
            }
        }

        return tv;

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

        dataAdapter.notifyDataSetChanged();
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
        setOrientation(VERTICAL);

        this.headerRow = new LinearLayout(getContext());

        if (this.customizationsPrinter.getColorAdapter() != null) {
            this.headerRow.setLayoutParams(this.customizationsPrinter.getParamsAdapter().getRowParams(Port.header));

        } else {
            this.headerRow.setLayoutParams(Themes.Default.getRowParams(Port.header));

        }


        this.addView(this.headerRow);

        dataAdapter = new NodesAdapter(getContext(), 0, listAdapterIndices, nodesStackPrinter.getContent());

        listView = new ListView(getContext());
        listView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        this.addView(listView);
        listView.setAdapter(dataAdapter);

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

abstract class StackView extends LinearLayout {
    public abstract void refreshView();

    public abstract void notifyOfRefreshIntent();

    public abstract void notifyOfContentAlteration(Alteration alteration, String elementKey, int index);

    public abstract void notifyOfFeedRebuild();

    public StackView(Context context) {
        super(context);
    }

    public StackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StackView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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