package UI_Utils.CustomViews.DataViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ckyblue.adtwisei4.Logger;

import UI_Utils.CustomViews.DataViews.Customizations.Content;
import UI_Utils.CustomViews.DataViews.Customizations.Feed;
import UI_Utils.CustomViews.DataViews.Customizations.Printer;
import UI_Utils.CustomViews.NonScrollableListView;

public abstract class DataStackView extends LinearLayout {
    private boolean notified = false;

    private ListView listView;
    private LinearLayout headerRow;

    private DataAdapter dataAdapter;

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public ListView getListView() {
        return listView;
    }

    public LinearLayout getHeaderRow() {
        return headerRow;
    }

    public DataAdapter getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    private Printer customizationsPrinter = new Printer() {
        String TAG = "DataStackView.customizationsPrinter";

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");

            DataStackView.this.notifyOfRefreshIntent();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");

            DataStackView.this.dataAdapter.setCustomizations(getContent());
            DataStackView.this.notifyOfFeedRebuild();
        }
    };

    public void setCustomizationsFeed(Feed customizationsFeed) {
        this.customizationsPrinter.setFeed(customizationsFeed);
    }

    public Feed getCustomizationsFeed() {
        return this.customizationsPrinter.getFeed();
    }

    public Content getCustomizationsContent() {
        if (getCustomizationsFeed() != null) {
            return getCustomizationsFeed().getContent();
        }

        return null;
    }

    @NonNull
    public UI_Utils.CustomViews.DataViews.ParamsAdapter.Content getParamsAdapter() {
        return Utilities.getParamsAdapter(getCustomizationsContent());
    }

    @NonNull
    public Utility.Colors.ColorAdapter.Content getColorAdapter() {
        return Utilities.getColorAdapter(getCustomizationsContent());
    }

    public abstract void rebuildView();

    public abstract void refreshView();

    public abstract void notifyOfRefreshIntent();

    public abstract void notifyOfFeedRebuild();

    private void init() {
        setOrientation(VERTICAL);

        this.headerRow = new LinearLayout(getContext());
        this.headerRow.setLayoutParams(getParamsAdapter().getHeaderRowParams());

        this.addView(this.headerRow);

        this.listView = new NonScrollableListView(getContext());
        this.listView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        this.addView(getListView());
    }

    public DataStackView(Context context) {
        super(context);
        init();
    }

    public DataStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DataStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DataStackView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}