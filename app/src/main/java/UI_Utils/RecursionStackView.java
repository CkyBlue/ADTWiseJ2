package UI_Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ckyblue.adtwisei4.Logger;

import Utility.RecursionStack.Controller;
import Utility.RecursionStack.Data;

public class RecursionStackView extends LinearLayout {
    private String TAG = getClass().getName();

    private final Controller controller = new Controller() {
        @Override
        public void notifyOfChangeToData(Alteration alterationType) {
            Logger.log(TAG, "notifyOfChangeToData() called");
            Toast.makeText(getContext(), "notifyOfChangeToData()", Toast.LENGTH_SHORT).show();
            onChangeToData(alterationType);
        }
    };

    private TextView newTextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                120));
        textView.setBackgroundColor(Color.parseColor(Utility.Colors.Color.gray.getHexARGB()));
        textView.setTextColor(Color.parseColor(Utility.Colors.Color.alice_blue.getHexARGB()));

        return textView;
    }

    public void onChangeToData(Controller.Alteration alterationType) {
        this.removeAllViews();

        if (controller.getData() != null) {
            for (int i = controller.getData().getSize() - 1; i >= 0; i--) {
                this.addView(newTextView(controller.getData().getIdentifier(i)));
            }

        }
    }

    public void init() {
        this.setOrientation(VERTICAL);
        this.setGravity(Gravity.BOTTOM);
    }

    public RecursionStackView(Context context) {
        super(context);
        init();
    }

    public void setData(Data data) {
        controller.setData(data);
    }

    public RecursionStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecursionStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RecursionStackView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}
