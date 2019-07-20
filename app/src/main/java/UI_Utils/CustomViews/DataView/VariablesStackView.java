package UI_Utils.CustomViews.DataView;

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
import java.util.Arrays;
import java.util.List;

import UI_Utils.CustomViews.DataView.Customizations.Feed;
import UI_Utils.CustomViews.DataView.Customizations.Printer;
import Utility.Data.Alteration;

/*TODO Dispatcher with post execute notifyOfRefreshIntent*/

public class VariablesStackView extends LinearLayout {
    String TAG = "Info " + getClass().getName();

    public boolean notified = false;

    VariablesAdapter variablesAdapter;
    private ListView listView;
    private List<String> listAdapterKeys = new ArrayList<String>();

    private Printer customizationsPrinter = new Printer() {
        String TAG = "Info " + "VariableStackView.customizationsPrinter";

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");

            VariablesStackView.this.notifyOfRefreshIntent();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");

            VariablesStackView.this.variablesAdapter.setCustomizations(getContent());
            VariablesStackView.this.notifyOfFeedRebuild();
        }
    };

    private Utilities.Data.Variables.Stack.Printer variableStackPrinter = new Utilities.Data.Variables.Stack.Printer() {
        private String TAG = "Info " + "VariableStackView.variableStackPrinter";

        @Override
        public void notifyOfContentAlteration(Alteration alteration, String variableName) {
            Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + variableName + ")");

            VariablesStackView.this.notifyOfContentAlteration(alteration, variableName);
        }

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");

            VariablesStackView.this.notifyOfRefreshIntent();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");

            VariablesStackView.this.variablesAdapter.setVariablesStackContent(getContent());
            VariablesStackView.this.notifyOfFeedRebuild();
        }
    };

    public void setCustomizationsFeed(Feed customizationsFeed) {
        this.customizationsPrinter.setFeed(customizationsFeed);
    }

    public void setVariablesStackFeed(Utilities.Data.Variables.Stack.Feed variablesStackFeed) {
        this.variableStackPrinter.setFeed(variablesStackFeed);
    }

    public Feed getCustomizationsFeed() {
        return this.customizationsPrinter.getFeed();
    }

    public Utilities.Data.Variables.Stack.Feed getVariablesFeed() {
        return this.variableStackPrinter.getFeed();
    }

    public void rebuildView() {
        Logger.log(TAG, "rebuildView()");

        listAdapterKeys.clear();
        if (variableStackPrinter.getUnit() != null) {
            listAdapterKeys.addAll(Arrays.asList(variableStackPrinter.getUnit().getVariableNames()));
        }

        refreshView();
    }

    public void refreshView() {
        Logger.log(TAG, "refreshView()");

        variablesAdapter.notifyDataSetChanged();
    }

    public void notifyOfRefreshIntent() {
        Logger.log(TAG, "notifyOfRefreshIntent()");

        if (notified) {
            refreshView();
            notified = false;
        }
    }

    public void notifyOfContentAlteration(Alteration alteration, String variableName) {
        Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + variableName + ")");

        if (alteration == Alteration.component_added) {
            listAdapterKeys.add(variableName);

        } else if (alteration == Alteration.component_removed) {
            listAdapterKeys.remove(variableName);

        }
        notified = true;
    }

    public void notifyOfFeedRebuild() {
        Logger.log(TAG, "notifyOfFeedRebuild()");

        rebuildView();
    }

    private void init() {
        variablesAdapter = new VariablesAdapter(getContext(), 0, listAdapterKeys, variableStackPrinter.getContent());

        listView = new ListView(getContext());
        listView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        this.addView(listView);
        listView.setAdapter(variablesAdapter);

        rebuildView();
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
    private Utilities.Data.Variables.Stack.Content variablesStackContent;

    public VariablesAdapter(Context context, int resource, List<String> objects, Utilities.Data.Variables.Stack.Content variablesStack) {
        super(context, resource, objects);
        variablesStackContent = variablesStack;
    }

    public void setVariablesStackContent(Utilities.Data.Variables.Stack.Content variablesStackContent) {
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
            rowLinearLayout.setLayoutParams(getParamsAdapter().getRowParams());

            data = variablesStackContent.getUnit().getStrEqv(varKey);
            rowLinearLayout.addView(createView(Utilities.Data.Variables.Unit.Content.Column.identifier.toString(),
                    varKey, position));
            rowLinearLayout.addView(createView(Utilities.Data.Variables.Unit.Content.Column.value.toString(),
                    data, position));

        } else {
            rowLinearLayout = (LinearLayout) convertView;
            rowLinearLayout.setLayoutParams(getParamsAdapter().getRowParams());

            TextView childTextView;

            data = variablesStackContent.getUnit().getStrEqv(varKey);

            childTextView = (TextView) rowLinearLayout.getChildAt(0);
            childTextView.setText(varKey);
            applyCustomizationsToChildView(Utilities.Data.Variables.Unit.Content.Column.identifier.toString(),
                    varKey, position, childTextView);


            childTextView = (TextView) rowLinearLayout.getChildAt(1);
            childTextView.setText(data);
            applyCustomizationsToChildView(Utilities.Data.Variables.Unit.Content.Column.value.toString(),
                    data, position, childTextView);
        }

        return rowLinearLayout;
    }
}