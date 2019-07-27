package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import UI_Utils.CustomViews.InputPanel;
import Utility.Data.Type;
import Utility.Input.Reader;
import Utility.Input.Receiver;

public class InputPanelTestActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();
    int count = 0;

    private InputPanel inputPanel;
    private Reader reader;
    private Receiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_2);

        receiver = new Receiver() {
            @Override
            public void receiveInput(Object input) {
                Logger.log(TAG, "recieveInput(" + input + ")");
                inputHandled();
            }
        };

        inputPanel = findViewById(R.id.inputPanel);
        inputPanel.setReceiver(receiver);
    }

    private void test() {
        switch (count) {
            case 0: {
                Logger.log(TAG, "Deploying for STRING");
                receiver.deployReader(Type.STRING);
                break;
            }
            case 1: {
                Logger.log(TAG, "Deploying for INTEGER");
                receiver.deployReader(Type.INTEGER);
                break;
            }
            case 2: {
                Logger.log(TAG, "Deploying for FLOAT");
                receiver.deployReader(Type.FLOAT);
                break;
            }
            case 3: {
                Logger.log(TAG, "Deploying for BOOLEAN");
                receiver.deployReader(Type.BOOLEAN);
                break;
            }
            case 4: {
                Logger.log(TAG, "");
                break;
            }
            case 5: {
                Logger.log(TAG, "");
                break;
            }
            case 6: {
                Logger.log(TAG, "");
                break;
            }
            case 7: {
                Logger.log(TAG, "");
                break;
            }
            case 8: {
                Logger.log(TAG, "");
                break;
            }
            case 9: {
                Logger.log(TAG, "");
                break;
            }
            case 10: {
                Logger.log(TAG, "");
                break;
            }
        }

    }

    public void update(View view) {
        test();

        count++;
        Logger.log(TAG, "Count: " + count);
    }
}
