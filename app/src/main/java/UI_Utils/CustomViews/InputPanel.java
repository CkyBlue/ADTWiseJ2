package UI_Utils.CustomViews;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ckyblue.adtwisei4.Logger;
import com.example.ckyblue.adtwisei4.R;

import Utility.Data.Type;
import Utility.Input.Reader;
import Utility.Input.Receiver;

/*TODO Add support for Type.BOOLEAB*/

public class InputPanel extends FrameLayout {
    String TAG = getClass().getName();

    private LayoutInflater inflater;

    private FrameLayout container;
    private ViewGroup rootView;

    private TextView titleTextView;
    private Button submitBtn;

    private Receiver receiver;
    private Type activeType;
    private Reader reader = new Reader() {
        String TAG = "InputPanel.Reader";

        @Override
        public void returnInput(Object input) {
            Logger.log(TAG, "returnInput(" + input + ")");
            super.returnInput(input);
        }

        @Override
        protected void buildReader(Type type) {
            Logger.log(TAG, "buildReader(" + type + ")");
            InputPanel.this.buildReader(type);
        }

        @Override
        protected void destroyReader() {
            Logger.log(TAG, "destroyReader()");
            InputPanel.this.buildReader(null);
        }
    };

    public InputPanel(Context context) {
        super(context);
        init();
    }

    public InputPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public InputPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setReceiver(Receiver receiver) {
        if (this.receiver != null) {
            this.receiver.setReader(null);
        }

        if (receiver != null) {
            receiver.setReader(this.reader);
        }

        this.receiver = receiver;
    }

    public void init() {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rootView = (ViewGroup) inflater.inflate(R.layout.input_panel, this, true);

        this.titleTextView = findViewById(R.id.titleTextView);
        this.submitBtn = findViewById(R.id.submitBtn);
        this.container = findViewById(R.id.container);

        this.submitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InputPanel.this.onSubmit();
            }
        });

        this.buildReader(null);
    }

    public void onSubmit() {
        Logger.log(TAG, "onSubmit()");

        if (activeType == null || !reader.isReaderActive()) {
            buildReader(null);
            return;
        }

        Object input = null;

        if (activeType == Type.FLOAT || activeType == Type.INTEGER || activeType == Type.STRING) {
            EditText editText = findViewWithTag(activeType);
            String stringInput = editText.getText().toString();

            switch (activeType) {
                case STRING: {
                    input = stringInput;
                    break;
                }
                case INTEGER: {
                    input = Integer.parseInt(stringInput);
                    break;
                }
                case FLOAT: {
                    input = Float.parseFloat(stringInput);
                    break;
                }
            }

            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        } else if (activeType == Type.BOOLEAN) {
            RadioGroup radioGroup = findViewWithTag(activeType);

            if (((RadioButton) radioGroup.findViewById(R.id.option_true)).isChecked()) {
                input = true;

            } else if (((RadioButton) radioGroup.findViewById(R.id.option_false)).isChecked()) {
                input = false;

            }
        }


        if (input == null) {
            throw new IllegalStateException("The input cannot be null");
        }

        this.reader.returnInput(input);
    }

    public void buildReader(Type type) {
        Logger.log(TAG, "buildReader(" + type + ")");

        this.container.removeAllViews();
        this.activeType = type;

        if (activeType == Type.FLOAT || activeType == Type.INTEGER || activeType == Type.STRING) {
            EditText editText = new EditText(getContext());
            FrameLayout.LayoutParams editTextParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            editText.setLayoutParams(editTextParams);

            switch (activeType) {
                case STRING: {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                }
                case INTEGER: {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                }
                case FLOAT: {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    break;
                }
            }

            editText.setTag(activeType);
            editText.requestFocus();

            InputMethodManager inputMethodManager =
                    (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            this.container.addView(editText);

        } else if (activeType == Type.BOOLEAN) {
            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.boolean_radio_input, this.container, true);
            RadioGroup radioGroup = viewGroup.findViewById(R.id.radioGroup);

            radioGroup.setTag(activeType);
            radioGroup.requestFocus();
        }
    }
}