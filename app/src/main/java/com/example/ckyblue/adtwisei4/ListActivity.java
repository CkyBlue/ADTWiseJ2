package com.example.ckyblue.adtwisei4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Implementations.Implementations;

public class ListActivity extends AppCompatActivity {

    public static String tree_key = "ListActivity.tree_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<String> trees = new ArrayList<>(Implementations.algorithmTrees.keySet());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, trees);
        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView btn = (TextView) view;
                String key = btn.getText().toString();

//                Toast.makeText(ListActivity.this, key, Toast.LENGTH_SHORT).show();

                launchDispatcher(key);
            }
        });
    }

    public void launchDispatcher(String key) {
        Intent intent = new Intent(this, Dispatcher2.class);
        intent.putExtra(tree_key, key);

        startActivity(intent);
    }
}
