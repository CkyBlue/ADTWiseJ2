package UI_Utils.CustomViews.DataView.ParamsAdapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import Utility.Port;

public class Themes {
    private static AbsListView.LayoutParams bodyRowParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 150);
    private static LinearLayout.LayoutParams headerRowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
    private static LinearLayout.LayoutParams headerChildParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

    static {
        headerChildParams.setMargins(0, 5, 5, 0);
    }

    public static final Content SquareIndices = new Content() {
        LinearLayout.LayoutParams indexChild = new LinearLayout.LayoutParams(140, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams dataChild = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        {
            dataChild.setMargins(5, 0, 5, 0);
            indexChild.setMargins(5, 5, 5, 5);
        }

        Typeface typeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);

        @Override
        public void applyModifications(Port port, String key, View view) {
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
        public AbsListView.LayoutParams getBodyRowParams() {
            return bodyRowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getHeaderRowParams() {
            return headerRowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getChildParams(Port port, String key) {
            if (port == Port.header) {
                return headerChildParams;
            }

            if (key.equals("Pointer") || key.equals("Index")) {
                return indexChild;
            }
            return dataChild;
        }
    };
    public static final Content Default = new Content() {
        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        LinearLayout.LayoutParams headerChildParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        {
            headerChildParams.setMargins(0, 5, 5, 0);
            childParams.setMargins(5, 0, 5, 0);
        }

        Typeface typeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);

        @Override
        public void applyModifications(Port port, String key, View view) {
            TextView tv = (TextView) view;
            tv.setTextSize(15);
            tv.setTypeface(typeface);
        }

        @NonNull
        @Override
        public AbsListView.LayoutParams getBodyRowParams() {
            return bodyRowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getHeaderRowParams() {
            return headerRowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getChildParams(Port port, String key) {
            if (port == Port.header) {
                return headerChildParams;
            }

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

        Typeface typeface = Typeface.create("sans-serif-thin", Typeface.NORMAL);

        @Override
        public void applyModifications(Port port, String key, View view) {
            TextView tv = (TextView) view;
            tv.setTextSize(15);
            tv.setTypeface(typeface);
        }

        @NonNull
        @Override
        public AbsListView.LayoutParams getBodyRowParams() {
            return bodyRowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getHeaderRowParams() {
            return headerRowParams;
        }

        @NonNull
        @Override
        public LinearLayout.LayoutParams getChildParams(Port port, String key) {
            if (port == Port.header) {
                return headerChildParams;
            }

            if (key.equals("Identifier")) {
                return identifierChild;
            }
            return valueChild;
        }
    };
}
