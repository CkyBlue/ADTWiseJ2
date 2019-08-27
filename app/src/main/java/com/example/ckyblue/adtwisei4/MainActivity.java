package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ckyblue.adtwisei4.Fragments.DataLayer;
import com.example.ckyblue.adtwisei4.Fragments.Logger;
import com.example.ckyblue.adtwisei4.Fragments.Output;
import com.example.ckyblue.adtwisei4.Fragments.SourceCodes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Utility.Data.Layer.Component;
import Utility.Data.Layer.Content;
import Utility.Data.Layer.Feed;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;
import Utility.Utilities;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getName();
    int step = 0;

    DataLayer dataLayerFragment;
    SourceCodes sourceCodesFragment;

    Content dataLayer_Content = new Content();
    Feed dataLayer_Feed = new Feed();

    Utility.SourceCode.Layer.Feed srcCodeFeed = new Utility.SourceCode.Layer.Feed();

    Output outputFragment;
    Utility.Logs.Feed logFeed = new Utility.Logs.Feed();

    Utility.Logs.Logger.Content outputContent = new Utility.Logs.Logger.Content();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputFragment = (Logger) getSupportFragmentManager().findFragmentById(R.id.loggerFragment);
        outputFragment.setFeed(logFeed);
        logFeed.setContent(outputContent);
    }

    private void dataLayerTest() {
        switch (step) {
            case 0: {
                com.example.ckyblue.adtwisei4.Logger.log(TAG, "Building a data layer.");

                dataLayer_Content.buildVariablesStack("myStack");

                BluePrint bluePrint = new BluePrint();

                bluePrint.addKey("Index", Type.INTEGER);
                bluePrint.addKey("Data", Type.STRING);
                bluePrint.addKey("Pointer", Type.INTEGER);

                dataLayer_Content.buildNodesStack("myStack", bluePrint, 12);
                break;
            }
            case 1: {
                com.example.ckyblue.adtwisei4.Logger.log(TAG, "Setting the data layer.");

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
                com.example.ckyblue.adtwisei4.Logger.log(TAG, "Setting null as data layer");
                dataLayerFragment.setFeed(null);

                break;
            }
            case 3: {
                com.example.ckyblue.adtwisei4.Logger.log(TAG, "Adding new components");
                dataLayerFragment.setFeed(dataLayer_Feed);

                dataLayer_Content.buildVariablesStack("locals");

                break;
            }
            case 4: {
                com.example.ckyblue.adtwisei4.Logger.log(TAG, "Manipulating components");

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
                com.example.ckyblue.adtwisei4.Logger.log(TAG, "Removing components");
                dataLayer_Content.removeStack(Component.VariablesStack, "myStack");
                break;
            }
            case 6: {
                break;
            }
        }
        dataLayer_Content.refreshIntent();
    }

    private void srcCodeLayerTest() {
        switch (step) {
            case 0: {
                Utility.SourceCode.Layer.Content content = new Utility.SourceCode.Layer.Content();

                Set<String> unitKeys = new HashSet<>();
                unitKeys.add("Pseudo");

                content.buildUnits(unitKeys);
                content.getUnitFeed("Pseudo").getContent().setText(Utilities.readRawTextFile(this, R.raw.queue_insert_pseudo));

                srcCodeFeed.setContent(content);
                sourceCodesFragment.setFeed(srcCodeFeed);

                com.example.ckyblue.adtwisei4.Logger.log("MainActivity", Arrays.toString(content.getUnitFeed("Pseudo").getPrinters().toArray()));
                break;
            }
            case 1: {
                sourceCodesFragment.setFeed(null);
                com.example.ckyblue.adtwisei4.Logger.log("MainActivity", Arrays.toString(srcCodeFeed.getContent().getUnitFeed("Pseudo").getPrinters().toArray()));
                break;
            }
            case 2: {
                sourceCodesFragment.setFeed(srcCodeFeed);
                break;
            }
        }

    }

    private void outputFragTest() {
        switch (step) {
            case 0: {
                outputContent.log("Hello\nWorld", true);
                outputContent.refreshIntent();

                break;
            }
            case 1: {
                outputContent.log("World", false);
                break;
            }
            case 2: {
                outputContent.log("More");
                outputContent.log("Output");

                outputContent.refreshIntent();

                break;
            }
            case 3: {
                outputFragment.setFeed(logFeed);
                break;
            }
        }

        com.example.ckyblue.adtwisei4.Logger.log(TAG, Arrays.toString(outputContent.getLogs().toArray()));
    }

    public void update(View view) {
        com.example.ckyblue.adtwisei4.Logger.log("Step", "<----------" + String.valueOf(step));
        outputFragTest();

        com.example.ckyblue.adtwisei4.Logger.log("Step", String.valueOf(step) + "---------->");
        step++;
    }
}
