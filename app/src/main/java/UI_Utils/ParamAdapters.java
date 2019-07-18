package UI_Utils;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import UI_Utils.ParamsAdapter.Content;

public enum ParamAdapters {
    SquareIndices(new Content() {
        LinearLayout.LayoutParams indexChild = new LinearLayout.LayoutParams(140, LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams dataChild = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        {
            dataChild.setMargins(5, 0, 5, 0);
            indexChild.setMargins(5, 5, 5, 5);
        }

        AbsListView.LayoutParams rowParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 150);

        Typeface typeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);

        @Override
        public void applyModifications(String key, View view) {
            TextView tv = (TextView) view;

            if (key.equals("Pointer") || key.equals("Index")) {
                tv.setTextSize(17);
            } else {
                tv.setTextSize(15);
            }

            tv.setTypeface(typeface);
        }

        @NonNull
        @Override
        public AbsListView.LayoutParams getRowParams() {
            return rowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getChildParams(String key) {
            if (key.equals("Pointer") || key.equals("Index")) {
                return indexChild;
            }
            return dataChild;
        }
    }),

    StandardVariables(new Content() {
        LinearLayout.LayoutParams identifierChild = new LinearLayout.LayoutParams(140, LinearLayout.LayoutParams.MATCH_PARENT, 0.40f);

        LinearLayout.LayoutParams valueChild = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.60f);

        {
            identifierChild.setMargins(5, 0, 5, 0);
            valueChild.setMargins(5, 0, 5, 0);
        }

        AbsListView.LayoutParams rowParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 150);

        Typeface typeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);

        @Override
        public void applyModifications(String key, View view) {
            TextView tv = (TextView) view;
            tv.setTextSize(15);
            tv.setTypeface(typeface);
        }

        @NonNull
        @Override
        public AbsListView.LayoutParams getRowParams() {
            return rowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getChildParams(String key) {
            if (key.equals("Identifier")) {
                return identifierChild;
            }
            return valueChild;
        }
    });

    ParamAdapters(Content paramsAdapterContent) {
        this.paramsAdapterContent = paramsAdapterContent;
    }

    private Content paramsAdapterContent;

    public Content getParamsAdapter() {
        return this.paramsAdapterContent;
    }
}
