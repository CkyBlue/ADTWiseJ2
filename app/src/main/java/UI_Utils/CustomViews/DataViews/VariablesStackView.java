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
import java.util.Arrays;
import java.util.List;

import Utility.Data.Alteration;
import Utility.Data.Variables.Unit.Content;
import Utility.Port;

/*TODO Dispatcher with post execute notifyOfRefreshIntent on BaseContent objects (notifications should propagate from BaseContent to View)*/

public class VariablesStackView extends DataStackView {
    String TAG = getClass().getName();

    private List<String> listAdapterKeys = new ArrayList<String>();

    private Utility.Data.Variables.Stack.Printer variableStackPrinter = new Utility.Data.Variables.Stack.Printer() {
        private String TAG_Header = "VariableStackView.variableStackPrinter";
        private String TAG = TAG_Header;

        @Override
        public void notifyOfContentAlteration(Alteration alteration, String variableName) {
            Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + variableName + ")");

            VariablesStackView.this.notifyOfContentAlteration(alteration, variableName);
        }

        @Override
        public void notifyOfRefreshIntent() {
            TAG = TAG_Header;

            if (getContent() != null){
                TAG += "<" + getContent().getName() + ">";
            }

            Logger.log(TAG, "notifyOfRefreshIntent()");

            VariablesStackView.this.notifyOfRefreshIntent();
        }

        @Override
        public void notifyOfFeedRebuild() {

            if (getDataAdapter() != null) {
                ((VariablesAdapter) getDataAdapter()).setVariablesStackContent(getContent());

            }
            VariablesStackView.this.notifyOfFeedRebuild();
        }
    };

    public void setVariablesStackFeed(Utility.Data.Variables.Stack.Feed variablesStackFeed) {
        this.variableStackPrinter.setFeed(variablesStackFeed);
    }

    public Utility.Data.Variables.Stack.Feed getVariablesFeed() {
        return this.variableStackPrinter.getFeed();
    }

    public void rebuildView() {
        Logger.log(TAG, "rebuildView()");

        listAdapterKeys.clear();
        if (variableStackPrinter.getUnit() != null) {
            listAdapterKeys.addAll(Arrays.asList(variableStackPrinter.getUnit().getVariableNames()));
        }

        getHeaderRow().setLayoutParams(getParamsAdapter().getHeaderRowParams());

        TextView tv;
        for (Content.Column column : Content.Column.values()) {
            tv = getHeaderRow().findViewWithTag(column);

            Utilities.applyCustomizations(Port.header,
                    column.toString(),
                    column.toString(),
                    getHeaderRow().indexOfChild(tv),
                    tv,
                    getColorAdapter(),
                    getParamsAdapter());
        }

        refreshView();
    }

    public void refreshView() {
        Logger.log(TAG, "refreshView()");

        if (getDataAdapter() != null) {
            getDataAdapter().notifyDataSetChanged();
        }
    }

    public void notifyOfRefreshIntent() {
        Logger.log(TAG, "notifyOfRefreshIntent()");

        if (isNotified()) {
            refreshView();
            setNotified(false);
        }
    }

    public void notifyOfContentAlteration(Alteration alteration, String variableName) {
        Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + variableName + ")");

        if (alteration == Alteration.component_added) {
            listAdapterKeys.add(variableName);

        } else if (alteration == Alteration.component_removed) {
            listAdapterKeys.remove(variableName);

        }
        setNotified(true);
    }

    public void notifyOfFeedRebuild() {
        Logger.log(TAG, "notifyOfFeedRebuild()");

        rebuildView();
    }

    protected void init() {
        setDataAdapter(new VariablesAdapter(getContext(), -1, listAdapterKeys, this.variableStackPrinter.getContent()));
        getListView().setAdapter(getDataAdapter());

        getHeaderRow().removeAllViews();

        int count = 0;
        TextView textView;

        for (Content.Column column : Content.Column.values()) {
            textView = Utilities.createView(getContext(),
                    Port.header,
                    column.toString(),
                    column.toString(),
                    count,
                    getColorAdapter(),
                    getParamsAdapter());

            textView.setTag(column);
            getHeaderRow().addView(textView);
            count++;
        }
    }

    public VariablesStackView(Context context) {
        super(context);
        init();
    }

    public VariablesStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VariablesStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VariablesStackView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}

class VariablesAdapter extends DataAdapter<String> {
    private Utility.Data.Variables.Stack.Content variablesStackContent;

    public VariablesAdapter(Context context, int resource, List<String> objects, Utility.Data.Variables.Stack.Content variablesStack) {
        super(context, resource, objects);
        variablesStackContent = variablesStack;
    }

    public void setVariablesStackContent(Utility.Data.Variables.Stack.Content variablesStackContent) {
        this.variablesStackContent = variablesStackContent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout rowLinearLayout;

        String data;
        String varKey = getItem(position);

        if (convertView == null) {
            /*If no view available for recycling, create one*/

            rowLinearLayout = new LinearLayout(getContext());
            rowLinearLayout.setId(View.generateViewId());
            rowLinearLayout.setLayoutParams(getParamsAdapter().getBodyRowParams());

            data = variablesStackContent.getUnit().getStrEqv(varKey);

            rowLinearLayout.addView(Utilities.createView(getContext(),
                    Port.body, Content.Column.Identifier.toString(),
                    varKey, position, getColorAdapter(),
                    getParamsAdapter()));

            rowLinearLayout.addView(Utilities.createView(getContext(),
                    Port.body, Content.Column.Value.toString(),
                    data, position, getColorAdapter(),
                    getParamsAdapter()));

        } else {
            rowLinearLayout = (LinearLayout) convertView;
            rowLinearLayout.setLayoutParams(getParamsAdapter().getBodyRowParams());

            TextView childTextView;

            data = variablesStackContent.getUnit().getStrEqv(varKey);

            childTextView = (TextView) rowLinearLayout.getChildAt(0);
            childTextView.setText(varKey);

            Utilities.applyCustomizations(Port.body,
                    Content.Column.Identifier.toString(),
                    varKey, position, childTextView,
                    getColorAdapter(), getParamsAdapter());

            childTextView = (TextView) rowLinearLayout.getChildAt(1);
            childTextView.setText(data);

            Utilities.applyCustomizations(Port.body,
                    Content.Column.Value.toString(),
                    data, position, childTextView,
                    getColorAdapter(), getParamsAdapter());
        }

        return rowLinearLayout;
    }
}