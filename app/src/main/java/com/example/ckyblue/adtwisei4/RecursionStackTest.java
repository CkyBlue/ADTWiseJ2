package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import UI_Utils.RecursionStackView;
import Utility.RecursionStack.Data;

public class RecursionStackTest extends AppCompatActivity {
    private final String TAG = getClass().getName();

    private RecursionStackView recursionStackView;
    private Data data = new Data();
    private EditText input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recursion_stack_test);

        recursionStackView = findViewById(R.id.recursioStackView);
        recursionStackView.setData(data);

        input = findViewById(R.id.input);
    }

    public void submit(View view) {
        data.pushToStack(input.getText().toString());
    }

    public void pop(View view) {
        if (data.getSize() > 0) {
            data.popFromtack();

        } else {
            Toast.makeText(this, "Stack Empty", Toast.LENGTH_SHORT).show();

        }
    }
}
