package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

import UI_Utils.CustomViews.DataViews.NodesStackView;
import UI_Utils.CustomViews.DataViews.ParamsAdapter.Themes;
import UI_Utils.SingleChainView;
import Utility.Colors.Components;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Nodes.Stack.Content;
import Utility.Data.Type;
import Utility.Diagram.Units.Chain.Unbranching.Feed;
import Utility.Port;
import Utility.Themes.Cascades;
import Utility.Themes.Defaults;

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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 100, 10, 100);
        singleChainView.setLayoutParams(layoutParams);
        container.addView(singleChainView);

        NodesStackView nodesStackView = new NodesStackView(this);

        Utility.Colors.ColorAdapter.Content mDefaultColorAdaper = new Utility.Colors.ColorAdapter.Content() {
            @Override
            public Components fetchComponents(Port port, String key, String content, int position) {
                if (port == Port.header) {
                    return Defaults.headerChrome.fetchComponents(content, position);
                }

                if (key == null || !(key.equals(Utility.Data.Nodes.Unit.Content.Column.Index.toString()) ||
                        key.contains(Utility.Data.Nodes.Unit.Content.Column.Pointer.toString())
                )) {
                    return Cascades.IndexStreamers.Stripes.getChrome().fetchComponents(content, position);
                }

                return Cascades.ContentStreamers.AllInPlains.getChrome().fetchComponents(content, position);
            }
        };

        UI_Utils.CustomViews.DataViews.Customizations.Content customizations = new UI_Utils.CustomViews.DataViews.Customizations.Content(
                mDefaultColorAdaper,
                Themes.SquareIndices
        );
        UI_Utils.CustomViews.DataViews.Customizations.Feed customizationsFeed = new UI_Utils.CustomViews.DataViews.Customizations.Feed();
        customizationsFeed.setContent(customizations);

        nodesStackView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        nodesStackView.setCustomizationsFeed(customizationsFeed);

        BluePrint nodearrayBluePrint = new BluePrint();
        nodearrayBluePrint.addKey("Item", Type.STRING);
        nodearrayBluePrint.addKey("Pointer", Type.INTEGER);

        nodes = new Content("NodesArray", nodearrayBluePrint, 7);

        Utility.Data.Nodes.Stack.Feed nodesFeed = new Utility.Data.Nodes.Stack.Feed();
        nodesFeed.setContent(nodes);
        nodesStackView.setLayoutParams(layoutParams);
        nodesStackView.setNodesStackFeed(nodesFeed);
//        nodesStackView.setPadding(10,10,10,10);
        container.addView(nodesStackView);

        for (int count = 0; count < nodes.getUnit().getSize(); count++) {
            nodes.getUnit().set("Pointer", count, count + 1);
        }
        nodes.getUnit().set("Pointer", nodes.getUnit().getSize() - 1, -1);

        variables = new Utility.Data.Variables.Stack.Content("pointers");

        variables.getUnit().declareVariable("head_pointer", Type.INTEGER);
        variables.getUnit().set("head_pointer", 4);
        variables.getUnit().declareVariable("tail_pointer", Type.INTEGER);
        variables.getUnit().set("tail_pointer", 0);
        variables.getUnit().declareVariable("current_pointer", Type.INTEGER);
        variables.getUnit().set("current_pointer", -1);
        variables.getUnit().declareVariable("free_pointer", Type.INTEGER);
        variables.getUnit().set("free_pointer", -1);

        nodes.getUnit().set("Pointer", 6, 3);
        nodes.getUnit().set("Pointer", 3, 2);
        nodes.getUnit().set("Pointer", 2, 1);
        nodes.getUnit().set("Pointer", 1, 0);
        nodes.getUnit().set("Pointer", 0, -1);

        nodes.getUnit().set("Item", 4, "I");
        nodes.getUnit().set("Item", 5, "J");
        nodes.getUnit().set("Item", 6, "K");
//        nodes.getUnit().set("Item", 7, "L");
//        nodes.getUnit().set("Item", 8, "M");
//        nodes.getUnit().set("Item", 9, "N");
        nodes.getUnit().set("Item", 3, "L");
        nodes.getUnit().set("Item", 2, "M");
        nodes.getUnit().set("Item", 1, "N");
        nodes.getUnit().set("Item", 0, "O");


        Logger.log(TAG, nodes.toString());
    }

    private int count = 0;

    public void update(View view) {
        switch (count) {
            case 0: {
                Logger.log(TAG, "Setting empty feed to ChainView");

                Utility.Diagram.Units.Chain.Unbranching.Feed chainFeed = new Feed();
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
