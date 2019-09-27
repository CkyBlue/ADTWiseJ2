package UI_Utils.CustomViews.DataViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;
import java.util.List;

import Utility.Data.Alteration;
import Utility.Data.Nodes.Unit.Content;
import Utility.Port;

public class NodesStackView extends DataStackView {
    String TAG = getClass().getName();

    // ListAdapterIndices is the ArrayList object fed into the Adapter's constructor
    private List<Integer> listAdapterIndices = new ArrayList<>();
    private Utility.Data.Nodes.Stack.Printer nodesStackPrinter = new Utility.Data.Nodes.Stack.Printer() {
        private String TAG_Header = "NodesStackView.nodesStackPrinter";
        private String TAG = TAG_Header;

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
            TAG = TAG_Header;

            if (getContent() != null) {
                TAG += "<" + getContent().getName() + ">";
            }

            Logger.log(TAG, "notifyOfFeedRebuild()");

            NodesAdapter nodesAdapter = (NodesAdapter) getDataAdapter();

            nodesAdapter.setNodesStackContent(getContent());
            NodesStackView.this.notifyOfFeedRebuild();
        }
    };

    public void setNodesStackFeed(Utility.Data.Nodes.Stack.Feed nodesStackFeed) {
        this.nodesStackPrinter.setFeed(nodesStackFeed);
    }

    public Utility.Data.Nodes.Stack.Feed getNodesFeed() {
        return this.nodesStackPrinter.getFeed();
    }

    @Override
    public void rebuildView() {
        Logger.log(TAG, "rebuildView()");

        getHeaderRow().removeAllViews();
        getHeaderRow().setLayoutParams(getParamsAdapter().getHeaderRowParams());

        TextView childView;

        if (nodesStackPrinter.getUnit() != null) {
            childView = Utilities.createView(getContext(), Port.header, Content.Column.Index.toString(),
                    Content.Column.Index.toString(), 0, getColorAdapter(), getParamsAdapter());
            getHeaderRow().addView(childView);

            for (String key : nodesStackPrinter.getUnit().getBluePrint().getKeys()) {
                childView = Utilities.createView(getContext(), Port.header, key, key, 0, getColorAdapter(), getParamsAdapter());
                getHeaderRow().addView(childView);

            }
        }

        refreshView();
    }

    @Override
    public void refreshView() {
        Logger.log(TAG, "refreshView()");

        listAdapterIndices.clear();
        if (nodesStackPrinter.getUnit() != null) {

            for (int i = 0; i < nodesStackPrinter.getUnit().getSize(); i++) {
                listAdapterIndices.add(i);

            }
        }

        if (getDataAdapter() != null) {
            getDataAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void notifyOfRefreshIntent() {
        Logger.log(TAG, "notifyOfRefreshIntent()");

        if (isNotified()) {
            if (nodesStackPrinter.getContent() != null) {
                nodesStackPrinter.getContent().alertComposingGroupOfChangeToConstituent();
            }

            refreshView();
            setNotified(false);
        }
    }

    @Override
    public void notifyOfFeedRebuild() {
        Logger.log(TAG, "notifyOfFeedRebuild()");

        rebuildView();
    }

    public void notifyOfContentAlteration(Alteration alteration, String elementKey, int index) {
        Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + elementKey + ", " + index + ")");

        setNotified(true);
    }

    private void init() {
        setDataAdapter(new NodesAdapter(getContext(), -1, listAdapterIndices, this.nodesStackPrinter.getContent()));
        getListView().setAdapter(getDataAdapter());

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
            rowLinearLayout.setLayoutParams(getParamsAdapter().getBodyRowParams());

            rowLinearLayout.addView(Utilities.createView(getContext(), Port.body, Content.Column.Index.toString(),
                    String.valueOf(index), position, getColorAdapter(), getParamsAdapter()));

            for (String elementKey : nodesStackContent.getUnit().getKeys()) {
                data = nodesStackContent.getUnit().getStrEqv(elementKey, index);

                rowLinearLayout.addView(Utilities.createView(getContext(), Port.body, elementKey,
                        data, position, getColorAdapter(), getParamsAdapter()));
            }

        } else {
            rowLinearLayout = (LinearLayout) convertView;
            rowLinearLayout.setLayoutParams(getParamsAdapter().getBodyRowParams());

            TextView childTextView;

            int count = 0;

            // 0th child is the Index child
            childTextView = (TextView) rowLinearLayout.getChildAt(count);
            childTextView.setText(String.valueOf(index));
            Utilities.applyCustomizations(Port.body, Content.Column.Index.toString(),
                    String.valueOf(index), position, childTextView, getColorAdapter(), getParamsAdapter());
            count++;

            for (String elementKey : nodesStackContent.getBluePrint().getKeys()) {
                data = nodesStackContent.getUnit().getStrEqv(elementKey, index);

                childTextView = (TextView) rowLinearLayout.getChildAt(count);
                childTextView.setText(data);
                Utilities.applyCustomizations(Port.body, elementKey,
                        data, position, childTextView, getColorAdapter(), getParamsAdapter());

                count++;
            }
        }

        return rowLinearLayout;
    }
}