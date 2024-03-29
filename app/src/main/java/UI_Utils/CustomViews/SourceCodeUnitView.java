package UI_Utils.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import UI_Utils.SpanFactory;
import Utility.SourceCode.FormattingKey;
import Utility.SourceCode.Tracker;
import Utility.SourceCode.Unit.Content;
import Utility.SourceCode.Unit.Feed;
import Utility.SourceCode.Unit.Printer;

public class SourceCodeUnitView extends android.support.v7.widget.AppCompatTextView {
    private final Printer printer = new Printer() {
        @Override
        public void notifyOfRefreshIntent() {

        }

        @Override
        public void buildFormatting(FormattingKey key) {
            Logger.log(getClass().getName(), "buildFormatting(" + key + ")");

            addFormattingKey(key);

            if (getContent() != null) {
                Logger.log("buildFormatting()", "getContent() != null");

                Tracker tracker = getContent().getFormatting(key);
                SpanFactory spanFactory = getSpanFactories().get(key);

                int start, end;
                Object[] manufacturedSpans;

                if (tracker != null &&
                        spanFactory != null &&
                        spanFactory.createSpans(getContent().getScopeCount()) != null) {
                    Logger.log("buildFormatting()", "building spans");

                    ArrayList<Object> spans = new ArrayList<>();

                    for (int i = 0; i < tracker.getLength(); i++) {
                        start = tracker.getStart(i);
                        end = tracker.getEnd(i);

                        if (getContent().getMaskedKeys() == null ||
                                !getContent().getMaskedKeys().contains(key) ||
                                !maskedOut(start, end)) {
                            manufacturedSpans = spanFactory.createSpans(getContent().getScopeCount());

                            for (Object span : manufacturedSpans) {
                                if (span != null) {
                                    spans.add(span);
                                    SourceCodeUnitView.this.getSpannableString().setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                        }
                    }
                    getAppliedSpans().put(key, spans.toArray());
                }
            }
        }

        @Override
        public void removeFormatting(FormattingKey key) {
            Object[] spans = getAppliedSpans().get(key);

            if (spans != null) {
                for (Object span : spans) {
                    getSpannableString().removeSpan(span);
                }
            }

            getAppliedSpans().put(key, null);
        }

        @Override
        public void notifyOfFormattingChange(FormattingKey key) {
            Logger.log(getClass().getName(), "notifyOfFormattingChange(" + key + ")");

            removeFormatting(key);
            buildFormatting(key);
        }

        @Override
        public void notifyOfScopeChange() {
            Logger.log(getClass().getName(), "notifyOfScopeChange()");

            removeAllFormatting();
            buildAllFormatting();
        }

        @Override
        public void notifyOfMaskChange() {
            Logger.log(getClass().getName(), "notifyOfMaskChange()");

            removeAllFormatting();
            buildAllFormatting();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(getClass().getName(), "notifyOfFeedRebuild()");

            SourceCodeUnitView.this.setSpannableString(createSpannableString());
            removeAllFormatting();
            buildAllFormatting();
        }
    };
    private SpannableString spannableString;

    private HashMap<FormattingKey, SpanFactory> spanFactories = new HashMap<>();
    public HashMap<FormattingKey, Object[]> appliedSpans = new HashMap<>();

    private HashMap<FormattingKey, SpanFactory> getSpanFactories() {
        return spanFactories;
    }

    private HashMap<FormattingKey, Object[]> getAppliedSpans() {
        return appliedSpans;
    }

    public void addFormattingKey(FormattingKey key) {
        if (!getPrinter().getFormattingKeys().contains(key)) {
            getPrinter().getFormattingKeys().add(key);
        }
    }

    public void removeFormattingKey(FormattingKey key) {
        getPrinter().removeFormatting(key);
        getSpanFactories().remove(key);
        getPrinter().getFormattingKeys().remove(key);
    }

    public void setSpanFactory(FormattingKey key, SpanFactory spanFactory) {
        addFormattingKey(key);
        getPrinter().removeFormatting(key);

        getSpanFactories().put(key, spanFactory);
        getPrinter().buildFormatting(key);
    }

    private SpannableString getSpannableString() {
        return spannableString;
    }

    private void setSpannableString(SpannableString spannableString) {
        SourceCodeUnitView.this.setText(spannableString, BufferType.SPANNABLE);
        this.spannableString = (SpannableString) SourceCodeUnitView.this.getText();
    }

    private SpannableString createSpannableString() {
        if (getContent() == null || getContent().getText() == null) {
            return new SpannableString("");
        }

        return new SpannableString(getContent().getText());
    }

    private Printer getPrinter() {
        return printer;
    }

    public Content getContent() {
        return getPrinter().getContent();
    }

    public void setFeed(Feed feed) {
        getPrinter().setFeed(feed);
    }

    public Feed getFeed() {
        return getPrinter().getFeed();
    }

    public void init() {
        this.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
    }

    public SourceCodeUnitView(Context context) {
        super(context);
        init();
    }

    public SourceCodeUnitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SourceCodeUnitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public String toString() {
        StringBuilder spanFactories = new StringBuilder();
        SpanFactory spanFactory;

        for (FormattingKey key : this.spanFactories.keySet()) {

            spanFactories.append("( ");
            spanFactories.append(key);
            spanFactories.append(" : ");

            spanFactory = this.spanFactories.get(key);
            if (spanFactory == null) {
                spanFactories.append("null");

            } else {
                spanFactories.append(spanFactory.getClass().getName());

            }

            spanFactories.append(" ), \n");
        }

        StringBuilder output = new StringBuilder();

        output.append("spanFactories : {");
        output.append(spanFactories.toString());
        output.append("}/n");

        output.append("formattingKeys : {");
        output.append(Arrays.toString(getPrinter().getFormattingKeys().toArray()));
        output.append("}/n");

        output.append("appliedSpans : {");
        output.append(Arrays.toString(appliedSpans.keySet().toArray()));
        output.append("}/n");

        return output.toString();
    }
}
