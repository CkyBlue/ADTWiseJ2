package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import UI_Utils.CustomViews.NodesStackView;
import UI_Utils.DataViewCustomizations.Content;
import UI_Utils.ParamAdapters;
import UI_Utils.ParamsAdapter.Themes;

import Utility.Colors.Components;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;
import Utility.Data.Variables.Stack.Feed;
import Utility.Themes.Cascades;
import Utility.Themes.Defaults;

/*TODO Test Nodes.Unit.Content for sent notifications*/

public class TestActivity extends AppCompatActivity {
    Utility.Data.Variables.Stack.Content variableStackContent = new Utility.Data.Variables.Stack.Content("myVarStack");
    Feed feed = new Feed();

    NodesStackView nodesStackView;
    BluePrint bluePrint = new BluePrint();

    {
        bluePrint.addKey("Index", Type.INTEGER);
        bluePrint.addKey("Data", Type.STRING);
        bluePrint.addKey("Pointer", Type.INTEGER);
    }

    Utility.Data.Nodes.Stack.Content nodesStackContent = new Utility.Data.Nodes.Stack.Content("myNodesStack", bluePrint, 12);
    Utility.Data.Nodes.Stack.Feed nodesStackFeed = new Utility.Data.Nodes.Stack.Feed();

    String TAG = "Info " + getClass().getName();

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Log.i(TAG, "Nodes: " + nodesStackContent.toString());

        nodesStackView = findViewById(R.id.nodesStackView);
        nodesStackView.setNodesStackFeed(nodesStackFeed);
    }

    public void testingVariables() {

        String mString = "mString";
        String mInt = "mInt";
        String mBool = "mBool";
        String mFloat = "mFloat";

        String newVar1 = "newVar1";
        String newVar2 = "newVar2";

        String l3Var1 = "l3Var1";
        String l3Var2 = "l3Var2";

        Log.i(TAG, "updateView()");
        Log.i(TAG, "Count: " + count);

       /* switch (count) {
            case 0: {
                Log.i(TAG, "Setting content.");

                feed.setLogText(variableStackContent);
                break;
            }
            case 1: {
                Log.i(TAG, "Building variables.");

                variableStackContent.getUnit().declareVariable(mString, Type.STRING);
                variableStackContent.getUnit().declareVariable(mInt, Type.INTEGER);
                variableStackContent.getUnit().declareVariable(mBool, Type.BOOLEAN);
                variableStackContent.getUnit().declareVariable(mFloat, Type.FLOAT);

                break;
            }
            case 2: {
                Log.i(TAG, "Setting variables.");

                variableStackContent.getUnit().set(mString, "Sanskar");
                variableStackContent.getUnit().set(mInt, 12);
                variableStackContent.getUnit().set(mFloat, 12.267f);
                variableStackContent.getUnit().set(mBool, true);

                break;
            }
            case 4: {
                Log.i(TAG, "Getting variables and setting new customizations.");

                Log.i("Output", variableStackContent.getUnit().getStr(mString));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getInt(mInt)));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getBool(mBool)));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getFloat(mFloat)));

                Content customizationsContent = new Content(Cascades.Crimson_CA.getChrome(), Themes.Variables);
                UI_Utils.DataViewCustomizations.Feed customizationsFeed = new UI_Utils.DataViewCustomizations.Feed();
                customizationsFeed.setLogText(customizationsContent);

                nodesStackView.setCustomizationsFeed(customizationsFeed);

                break;
            }
            case 5: {
                Log.i(TAG, "Adding layer.2");

                variableStackContent.push();
                break;
            }
            case 6: {
                Log.i(TAG, "Trying to access old variables.");

*//*
                Log.i("Output", variableStackContent.getUnit().getStr(mString));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getInt(mInt)));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getBool(mBool)));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getFloat(mFloat)));
*//*

                break;
            }
            case 7: {
                Log.i(TAG, "Declaring new variables and changing chromeConteent.");

                nodesStackView.getCustomizationsFeed().getContent().setColorAdapter(Cascades.AquaGreens_CA.getChrome());

                variableStackContent.getUnit().declareVariable(newVar1, Type.STRING);
                variableStackContent.getUnit().declareVariable(newVar2, Type.INTEGER);

                break;
            }
            case 8: {
                Log.i(TAG, "Setting variables.");

                variableStackContent.getUnit().set(newVar1, "Rhythm");
                variableStackContent.getUnit().set(newVar2, 52);

                break;
            }
            case 9: {
                Log.i(TAG, "Getting variables.");

                Log.i("Output", variableStackContent.getUnit().getStr(newVar1));
                Log.i("Output", String.valueOf(variableStackContent.getUnit().getInt(newVar2)));

                break;
            }
            case 10: {
                Log.i(TAG, "Adding layer 3.");

                variableStackContent.push();
                break;
            }
            case 11: {
                Log.i(TAG, "Declaring new variables.");

                variableStackContent.getUnit().declareVariable(l3Var1, Type.STRING);
                variableStackContent.getUnit().declareVariable(l3Var2, Type.INTEGER);

                break;
            }
            case 12: {
                Log.i(TAG, "First pop");

                variableStackContent.pop();
                break;
            }
            case 13: {
                Log.i(TAG, "Re-pushing Layer 3");

                variableStackContent.push();
                break;
            }
            case 14: {
                Log.i(TAG, "Another pop");

                variableStackContent.pop();
                break;
            }
            case 15: {
                Log.i(TAG, "Another pop");

                variableStackContent.pop();
                break;
            }
            case 16: {
                Log.i(TAG, "Attempting to pop base layer ");

                variableStackContent.pop();
                break;
            }

        }*/

        count++;

        variableStackContent.getUnit().refreshIntent();
        Log.i(TAG, "Variables: " + variableStackContent.toString());
    }

    public void testingNodes() {
        Log.i(TAG, "updateView()");
        Log.i(TAG, "Count: " + count);

        final String index = "Index";
        final String data = "Data";
        final String pointer = "Pointer";

        switch (count) {
            case 0: {
                Log.i(TAG, "Setting content and adding a node.");

                nodesStackFeed.setContent(nodesStackContent);
                nodesStackContent.getUnit().addNode();
                break;
            }
            case 1: {
                Log.i(TAG, "Setting values to nodes.");

                for (int i = 0; i < this.nodesStackContent.getUnit().getSize(); i++) {
                    this.nodesStackContent.getUnit().set(index, i, i);
                    this.nodesStackContent.getUnit().set(data, i, String.valueOf((char) ('A' + i)));
                    this.nodesStackContent.getUnit().set(pointer, i, i + 1);
                }

                break;
            }
            case 2: {
                Utility.Colors.ColorAdapter.Content colorAdapter = new Utility.Colors.ColorAdapter.Content() {
                    @Override
                    public Components fetchComponents(String key, String content, int position) {
                        Utility.Colors.Chrome.Content chrome = Defaults.chrome;

                        switch (key) {
                            case index: {
                                chrome = Cascades.ContentStreamers.Crimson.getChrome();
                                break;
                            }
                            case data: {
                                chrome = Cascades.IndexStreamers.Stripes.getChrome();
                                break;
                            }
                            case pointer: {
                                chrome = Cascades.ContentStreamers.Crimson.getChrome();
                                break;
                            }
                        }

                        return chrome.fetchComponents(content, position);
                    }
                };

                Content customizationsContent = new Content(colorAdapter, Themes.Variables);
                UI_Utils.DataViewCustomizations.Feed customizationsFeed = new UI_Utils.DataViewCustomizations.Feed();
                customizationsFeed.setContent(customizationsContent);

                nodesStackView.setCustomizationsFeed(customizationsFeed);
                break;
            }
            case 3: {
                Log.i(TAG, "Adding layer.2");

                nodesStackContent.push();
                break;
            }
            case 4: {
                Log.i(TAG, "Setting up new nodes.");

                for (int i = 0; i < this.nodesStackContent.getUnit().getSize(); i++) {
                    this.nodesStackContent.getUnit().set(index, i, i);
                    this.nodesStackContent.getUnit().set(data, i, String.valueOf((char) ('K' + i)));
                    this.nodesStackContent.getUnit().set(pointer, i, i + 4);
                }

                break;
            }
            case 5: {
                Log.i(TAG, "Retrieving values.");

                Log.i("Output", nodesStackContent.getUnit().getStr(data, 0));
                Log.i("Output", String.valueOf(nodesStackContent.getUnit().getInt(pointer, 0)));

                break;
            }
            case 6: {
                Log.i(TAG, "Adding layer 3.");

                nodesStackContent.push();
                break;
            }
            case 7: {
                Log.i(TAG, "Setting up new nodes and applying customization changes.");

                for (int i = 0; i < this.nodesStackContent.getUnit().getSize(); i++) {
                    this.nodesStackContent.getUnit().set(index, i, i);
                    this.nodesStackContent.getUnit().set(data, i, String.valueOf((char) ('M' + i)));
                    this.nodesStackContent.getUnit().set(pointer, i, i + 8);
                }

                nodesStackView.getCustomizationsFeed().getContent().setParamsAdapter(ParamAdapters.SquareIndices.getParamsAdapter());

                break;
            }
            case 8: {
                Log.i(TAG, "First pop");

                nodesStackContent.pop();
                break;
            }
            case 9: {
                Log.i(TAG, "Re-pushing Layer 3");

                nodesStackContent.push();
                break;
            }
            case 10: {
                Log.i(TAG, "Another pop");

                nodesStackContent.pop();
                break;
            }
            case 11: {
                Log.i(TAG, "Another pop");

                nodesStackContent.pop();
                break;
            }
            case 12: {
                Log.i(TAG, "Attempting to pop base layer ");

                nodesStackContent.pop();
                break;
            }
        }

        count++;
        nodesStackContent.refreshIntent();
        Log.i(TAG, "Nodes: " + nodesStackContent.toString());
    }

    public void update(View view) {
        testingNodes();
    }
}
