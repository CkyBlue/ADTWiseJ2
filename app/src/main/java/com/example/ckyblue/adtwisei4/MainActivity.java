package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import Utility.Data.Layer.Component;
import Utility.Data.Layer.Content;
import Utility.Data.Layer.Feed;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getName();
    int step = 0;

    DataLayerFragment dataLayerFragment;

    Content dataLayer_Content = new Content();
    Feed dataLayer_Feed = new Feed();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataLayerFragment = (DataLayerFragment) getSupportFragmentManager().findFragmentById(R.id.dataLayerFragment);
    }

    private void dataLayerTest() {
        switch (step) {
            case 0: {
                Logger.log(TAG, "Building a data layer.");

                dataLayer_Content.buildVariablesStack("myStack");

                BluePrint bluePrint = new BluePrint();

                bluePrint.addKey("Index", Type.INTEGER);
                bluePrint.addKey("Data", Type.STRING);
                bluePrint.addKey("Pointer", Type.INTEGER);

                dataLayer_Content.buildNodesStack("myStack", bluePrint, 12);
                break;
            }
            case 1: {
                Logger.log(TAG, "Setting the data layer.");

                dataLayer_Content.getVariablesStackFeed("myStack").getContent().getUnit().declareVariable("myVar", Type.STRING);
                dataLayer_Content.getVariablesStackFeed("myStack").getContent().getUnit().declareVariable("myVar2", Type.INTEGER);

                dataLayerFragment.setFeed(dataLayer_Feed);
                dataLayer_Feed.setContent(dataLayer_Content);

                BluePrint bluePrint = new BluePrint();

                bluePrint.addKey("ID", Type.INTEGER);
                bluePrint.addKey("Data", Type.STRING);

                dataLayer_Content.buildNodesStack("hashTable", bluePrint, 5);
                break;
            }
            case 2: {
                Logger.log(TAG, "Setting null as data layer");
                dataLayerFragment.setFeed(null);

                break;
            }
            case 3: {
                Logger.log(TAG, "Adding new components");
                dataLayerFragment.setFeed(dataLayer_Feed);

                dataLayer_Content.buildVariablesStack("locals");

                break;
            }
            case 4: {
                Logger.log(TAG, "Manipulating components");

                String mString = "mString";
                String mInt = "mInt";
                String mBool = "mBool";
                String mFloat = "mFloat";

                final String index = "Index";
                final String data = "Data";
                final String pointer = "Pointer";

                dataLayer_Content.getVariablesStackFeed("myStack").getContent().getUnit().declareVariable(mString, Type.STRING);
                dataLayer_Content.getVariablesStackFeed("myStack").getContent().getUnit().declareVariable(mInt, Type.INTEGER);
                dataLayer_Content.getVariablesStackFeed("myStack").getContent().getUnit().declareVariable(mBool, Type.BOOLEAN);
                dataLayer_Content.getVariablesStackFeed("myStack").getContent().getUnit().declareVariable(mFloat, Type.FLOAT);

                for (int i = 0; i < dataLayer_Content.getNodesStackFeed("myStack").getContent().getUnit().getSize(); i++) {
                    dataLayer_Content.getNodesStackFeed("myStack").getContent().getUnit().set(index, i, i);
                    dataLayer_Content.getNodesStackFeed("myStack").getContent().getUnit().set(data, i, String.valueOf((char) ('A' + i)));
                    dataLayer_Content.getNodesStackFeed("myStack").getContent().getUnit().set(pointer, i, i + 1);
                }
                break;
            }
            case 5: {
                Logger.log(TAG, "Removing components");
                dataLayer_Content.removeStack(Component.VariablesStack, "myStack");
                break;
            }
            case 6: {
                break;
            }
        }
        dataLayer_Content.refreshIntent();
    }

    public void update(View view) {
        dataLayerTest();

        Logger.log("Step", String.valueOf(step));
        step++;
    }
}
