package UI_Utils.CustomViews;

import android.content.Context;
import android.support.v4.text.HtmlCompat;
import android.text.Editable;
import android.util.AttributeSet;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;

import Utility.Logs.BaseContent;
import Utility.Logs.Feed;
import Utility.Logs.Printer;

/*TODO Decouple internal Printers and views. Create identical CallBack methods in Views for methods in Printer
 * instead of handling view within the Printer*/

public class LogView extends android.support.v7.widget.AppCompatTextView {
    String TAG = getClass().getName();
    boolean newLogs = false;

    private Printer printer = new Printer() {
        String TAG = "LogView.Logs.Printer";

        @Override
        public void notifyOfNewLog() {
            Logger.log(TAG, "notifyOfNewLog()");
            LogView.this.notifyOfNewLog();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");
            LogView.this.notifyOfFeedRebuild();
        }

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");
            LogView.this.notifyOfRefreshIntent();
        }
    };

    private Printer getPrinter() {
        return printer;
    }

    public BaseContent getContent() {
        return getPrinter().getContent();
    }

    public void setFeed(Feed feed) {
        getPrinter().setFeed(feed);
    }

    public Feed getFeed() {
        return getPrinter().getFeed();
    }

    public void notifyOfNewLog() {
        if (getContent() == null) {
            throw new IllegalStateException("This CallBack is only initiated via a non-null Logs.BaseContent.");
        }

        String newLog = getContent().getLogs().get(getContent().getLogs().size() - 1);
        Logger.log(TAG, "notifyOfNewLog()::" + newLog);

        this.newLogs = true;
    }

    public void notifyOfFeedRebuild() {
        Logger.log(TAG, "notifyOfFeedRebuild()");

        newLogs = true;
        notifyOfRefreshIntent();
    }

    public void notifyOfRefreshIntent() {
        Logger.log(TAG, "notifyOfRefreshIntent()");

        if (newLogs) {
            StringBuilder stringBuilder = new StringBuilder();
            if (getContent() != null && getContent().getLogs() != null) {

                for (String log : this.getContent().getLogs()) {
                    stringBuilder.append(log);
                }

            }

            this.setLog(stringBuilder.toString());
            newLogs = false;
        }
    }

    public void setLog(String log) {
        Logger.log(TAG, "setLog(" + log + ")");
        this.setText(HtmlCompat.fromHtml(log.replace("\n", "<br>") + " ",
                HtmlCompat.FROM_HTML_MODE_LEGACY));
    }

    private void init() {
    }

    public LogView(Context context) {
        super(context);
        init();
    }

    public LogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
