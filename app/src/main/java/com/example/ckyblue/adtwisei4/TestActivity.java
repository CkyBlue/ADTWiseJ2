package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

import UI_Utils.SingleChainView;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Nodes.Stack.Content;
import Utility.Data.Type;
import Utility.Diagram.Units.Chain.Feed;

/*TODO SubClass ChainView into SingleChainView*/

//TODO ** Reiterate with only refresh callBack (no incremental changes, rebuild from data-state every-time for simplified architecture)

public class TestActivity extends AppCompatActivity {
    private String TAG = getClass().getName();

    private Utility.Diagram.Units.Chain.Unbranching.Content chainContent;
    private SingleChainView singleChainView;
    private Utility.Data.Nodes.Stack.Content nodes;
    private Utility.Data.Variables.Stack.Content variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        LinearLayout container = findViewById(R.id.container);

        singleChainView = new SingleChainView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        singleChainView.setLayoutParams(layoutParams);

        container.setPadding(15, 15, 15, 15);
        container.addView(singleChainView);

        BluePrint nodearrayBluePrint = new BluePrint();
        nodearrayBluePrint.addKey("Item", Type.STRING);
        nodearrayBluePrint.addKey("Pointer", Type.INTEGER);

        nodes = new Content("NodesArray", nodearrayBluePrint, 10);

        for (int count = 0; count < nodes.getUnit().getSize(); count++) {
            nodes.getUnit().set("Pointer", count, count + 1);
        }
        nodes.getUnit().set("Pointer", nodes.getUnit().getSize() - 1, -1);
        Logger.log(TAG, nodes.toString());

        variables = new Utility.Data.Variables.Stack.Content("pointers");

        variables.getUnit().declareVariable("head_pointer", Type.INTEGER);
        variables.getUnit().set("head_pointer", -1);
        variables.getUnit().declareVariable("tail_pointer", Type.INTEGER);
        variables.getUnit().set("tail_pointer", -1);
        variables.getUnit().declareVariable("current_pointer", Type.INTEGER);
        variables.getUnit().set("current_pointer", -1);
        variables.getUnit().declareVariable("free_pointer", Type.INTEGER);
    }

    private int count = 0;

    public void update(View view) {
        switch (count) {
            case 0: {
                Logger.log(TAG, "Setting empty feed to ChainView");

                Feed chainFeed = new Feed();
                singleChainView.setFeed(chainFeed);

                break;
            }
            case 1: {
                Logger.log(TAG, "Setting content to feed");

                chainContent = new Utility.Diagram.Units.Chain.Unbranching.Content();
                singleChainView.getFeed().setContent(chainContent);

                break;
            }
            case 2: {
                Logger.log(TAG, "Reading nodes & variables into Chain content");

                Utility.Data.Nodes.Stack.Feed nodesFeed = new Utility.Data.Nodes.Stack.Feed();
                nodesFeed.setContent(nodes);

                Utility.Data.Variables.Stack.Feed variablesFeed = new Utility.Data.Variables.Stack.Feed();
                variablesFeed.setContent(variables);

                chainContent.readChain(nodesFeed, "Item", "Pointer", variablesFeed, "head_pointer",
                        Arrays.asList(variables.getUnit().getVariableNames()));
                break;
            }
            case 3: {
                Logger.log(TAG, "Adjusting pointer values 1");

                variables.getUnit().set("head_pointer", 0);
                break;
            }
            case 4: {
                Logger.log(TAG, "Adjusting pointer values 2");

                variables.getUnit().set("head_pointer", 1);
                break;
            }
            case 5: {
                Logger.log(TAG, "Adjusting node values");

                nodes.getUnit().set("Item", 5, "Ram");
                nodes.getUnit().set("Item", 6, "Ram");
                nodes.getUnit().set("Item", 7, "Ram");
                nodes.getUnit().set("Item", 8, "Ram");
                break;
            }
            case 6: {
                Logger.log(TAG, "Adjusting pointer values 3");

                nodes.getUnit().set("Pointer", 5, -1);
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

        count++;
        Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
    }
}
