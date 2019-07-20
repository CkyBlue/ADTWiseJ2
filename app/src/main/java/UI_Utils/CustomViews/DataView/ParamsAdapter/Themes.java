package UI_Utils.CustomViews.DataView.ParamsAdapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Themes {
    public static final Content SquareIndices = new Content() {
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
    };
    public static final Content Default = new Content() {
        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.60f);

        {
            childParams.setMargins(5, 0, 5, 0);
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
            return childParams;
        }
    };
    public static final Content Variables = new Content() {
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
    };
}
