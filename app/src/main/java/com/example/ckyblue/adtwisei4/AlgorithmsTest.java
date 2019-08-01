package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AlgorithmsTest extends AppCompatActivity {
    private String TAG = getClass().getName();

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_3);

    }

    private void test() {
        switch (count) {
            case 0: {
                Logger.log(TAG, "");
                break;
            }
            case 1: {
                Logger.log(TAG, "");
                break;
            }
            case 2: {
                Logger.log(TAG, "");
                break;
            }
            case 3: {
                Logger.log(TAG, "");
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
        Logger.log(TAG, "update()::Count:" + count);
        count++;
    }
}
