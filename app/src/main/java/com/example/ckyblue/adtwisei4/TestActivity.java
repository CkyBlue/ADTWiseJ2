package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import UI_Utils.CustomViews.DataView.NodesStackView;
import UI_Utils.CustomViews.DataView.Customizations.Content;
import UI_Utils.CustomViews.DataView.ParamsAdapter.Themes;

import UI_Utils.CustomViews.DataView.VariablesStackView;
import Utility.Port;
import Utility.Colors.Components;
import Utility.Data.Nodes.BluePrint;
import Utility.Data.Type;
import Utility.Data.Variables.Stack.Feed;
import Utility.Themes.Cascades;
import Utility.Themes.Defaults;

/*TODO Test Nodes.Unit.Content for sent notifications*/

public class TestActivity extends AppCompatActivity {
    Utility.Data.Variables.Stack.Content variableStackContent = new Utility.Data.Variables.Stack.Content("myVarStack");
    Feed variableStackFeed = new Feed();

    NodesStackView nodesStackView;
    VariablesStackView variablesStackView;

    BluePrint bluePrint = new BluePrint();

    {
        bluePrint.addKey("Index", Type.INTEGER);
        bluePrint.addKey("Data", Type.STRING);
        bluePrint.addKey("Pointer", Type.INTEGER);
    }

    Utility.Data.Nodes.Stack.Content nodesStackContent = new Utility.Data.Nodes.Stack.Content("myNodesStack", bluePrint, 12);
    Utility.Data.Nodes.Stack.Feed nodesStackFeed = new Utility.Data.Nodes.Stack.Feed();

    String TAG = getClass().getName();

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        nodesStackView = findViewById(R.id.nodesStackView);
        nodesStackView.setNodesStackFeed(nodesStackFeed);
        nodesStackFeed.setContent(nodesStackContent);

        variablesStackView = findViewById(R.id.variableStackView);
        variablesStackView.setVariablesStackFeed(variableStackFeed);

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

        Logger.log(TAG, "updateView()");
        Logger.log(TAG, "Count: " + count);

        switch (count) {
            case 0: {
                Logger.log(TAG, "Setting content.");

                variableStackFeed.setContent(variableStackContent);
                break;
            }
            case 1: {
                Logger.log(TAG, "Building variables.");

                variableStackContent.getUnit().declareVariable(mString, Type.STRING);
                variableStackContent.getUnit().declareVariable(mInt, Type.INTEGER);
                variableStackContent.getUnit().declareVariable(mBool, Type.BOOLEAN);
                variableStackContent.getUnit().declareVariable(mFloat, Type.FLOAT);

                break;
            }
            case 2: {
                Logger.log(TAG, "Setting variables.");

                variableStackContent.getUnit().set(mString, "Sanskar");
                variableStackContent.getUnit().set(mInt, 12);
                variableStackContent.getUnit().set(mFloat, 12.267f);
                variableStackContent.getUnit().set(mBool, true);

                break;
            }
            case 4: {
                Logger.log(TAG, "Getting variables and setting new customizations.");

                Logger.log("Output", variableStackContent.getUnit().getStr(mString));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getInt(mInt)));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getBool(mBool)));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getFloat(mFloat)));

                Utility.Colors.ColorAdapter.Content colorAdapter = new Utility.Colors.ColorAdapter.Content() {
                    @Override
                    public Components fetchComponents(Port port, String key, String content, int position) {

                        Utility.Colors.Chrome.Content chrome;

                        if (port == Port.header) {
                            chrome = Defaults.headerChrome;

                        } else {
                            chrome = Defaults.chrome;

                            if (key.equals(Utility.Data.Variables.Unit.Content.Column.Value.toString())) {
                                chrome = Cascades.IndexStreamers.Stripes.getChrome();
                            }
                        }

                        return chrome.fetchComponents(content, position);
                    }
                };

                Content customizationsContent = new Content(colorAdapter, Themes.Variables);
                UI_Utils.CustomViews.DataView.Customizations.Feed customizationsFeed = new UI_Utils.CustomViews.DataView.Customizations.Feed();
                customizationsFeed.setContent(customizationsContent);

                variablesStackView.setCustomizationsFeed(customizationsFeed);
                break;
            }
            case 5: {
                Logger.log(TAG, "Adding layer.2");

                variableStackContent.push();
                break;
            }
            case 6: {
         /*       Logger.log(TAG, "Trying to access old variables.");


                Logger.log("Output", variableStackContent.getUnit().getStr(mString));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getInt(mInt)));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getBool(mBool)));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getFloat(mFloat)));

*/
                break;
            }
            case 7: {
                Logger.log(TAG, "Declaring new variables and changing chromeConteent.");

                variableStackContent.getUnit().declareVariable(newVar1, Type.STRING);
                variableStackContent.getUnit().declareVariable(newVar2, Type.INTEGER);

                break;
            }
            case 8: {
                Logger.log(TAG, "Setting variables.");

                variableStackContent.getUnit().set(newVar1, "Rhythm");
                variableStackContent.getUnit().set(newVar2, 52);

                break;
            }
            case 9: {
                Logger.log(TAG, "Getting variables.");

                Logger.log("Output", variableStackContent.getUnit().getStr(newVar1));
                Logger.log("Output", String.valueOf(variableStackContent.getUnit().getInt(newVar2)));

                break;
            }
            case 10: {
                Logger.log(TAG, "Adding layer 3.");

                variableStackContent.push();
                break;
            }
            case 11: {
                Logger.log(TAG, "Declaring new variables.");

                variableStackContent.getUnit().declareVariable(l3Var1, Type.STRING);
                variableStackContent.getUnit().declareVariable(l3Var2, Type.INTEGER);

                break;
            }
            case 12: {
                Logger.log(TAG, "First pop");

                variableStackContent.pop();
                break;
            }
            case 13: {
                Logger.log(TAG, "Re-pushing Layer 3");

                variableStackContent.push();
                break;
            }
            case 14: {
                Logger.log(TAG, "Another pop");

                variableStackContent.pop();
                break;
            }
            case 15: {
                Logger.log(TAG, "Another pop");

                variableStackContent.pop();
                break;
            }
            case 16: {
                Logger.log(TAG, "Attempting to pop base layer ");

                variableStackContent.pop();
                break;
            }

        }

        variableStackContent.getUnit().refreshIntent();
        Logger.log(TAG, "Variables: " + variableStackContent.toString());
    }

    public void testingNodes() {
        Logger.log(TAG, "updateView()");
        Logger.log(TAG, "Count: " + count);

        final String index = "Index";
        final String data = "Data";
        final String pointer = "Pointer";

        switch (count) {
            case 0: {
                Logger.log(TAG, "Pushing a new layer and adding node.");

                nodesStackFeed.getContent().push();
                nodesStackContent.getUnit().addNode();
                break;
            }
            case 1: {
                Logger.log(TAG, "Setting values to nodes.");

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
                    public Components fetchComponents(Port port, String key, String content, int position) {

                        Utility.Colors.Chrome.Content chrome;

                        if (port == Port.header) {
                            chrome = Defaults.headerChrome;

                        } else {
                            chrome = Defaults.chrome;

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
                        }

                        return chrome.fetchComponents(content, position);
                    }
                };

                Content customizationsContent = new Content(colorAdapter, Themes.Variables);
                UI_Utils.CustomViews.DataView.Customizations.Feed customizationsFeed = new UI_Utils.CustomViews.DataView.Customizations.Feed();
                customizationsFeed.setContent(customizationsContent);

                nodesStackView.setCustomizationsFeed(customizationsFeed);
                break;
            }
            case 3: {
                Logger.log(TAG, "Adding layer.2");

                nodesStackContent.push();
                break;
            }
            case 4: {
                Logger.log(TAG, "Setting up new nodes.");

                for (int i = 0; i < this.nodesStackContent.getUnit().getSize(); i++) {
                    this.nodesStackContent.getUnit().set(index, i, i);
                    this.nodesStackContent.getUnit().set(data, i, String.valueOf((char) ('K' + i)));
                    this.nodesStackContent.getUnit().set(pointer, i, i + 4);
                }

                break;
            }
            case 5: {
                Logger.log(TAG, "Retrieving values.");

                Logger.log("Output", nodesStackContent.getUnit().getStr(data, 0));
                Logger.log("Output", String.valueOf(nodesStackContent.getUnit().getInt(pointer, 0)));

                break;
            }
            case 6: {
                Logger.log(TAG, "Adding layer 3.");

                nodesStackContent.push();
                break;
            }
            case 7: {
                Logger.log(TAG, "Setting up new nodes and applying customization changes.");

                for (int i = 0; i < this.nodesStackContent.getUnit().getSize(); i++) {
                    this.nodesStackContent.getUnit().set(index, i, i);
                    this.nodesStackContent.getUnit().set(data, i, String.valueOf((char) ('M' + i)));
                    this.nodesStackContent.getUnit().set(pointer, i, i + 8);
                }

                nodesStackView.getCustomizationsFeed().getContent().setParamsAdapter(Themes.SquareIndices);

                break;
            }
            case 8: {
                Logger.log(TAG, "First pop");

                nodesStackContent.pop();
                break;
            }
            case 9: {
                Logger.log(TAG, "Re-pushing Layer 3");

                nodesStackContent.push();
                break;
            }
            case 10: {
                Logger.log(TAG, "Another pop");

                nodesStackContent.pop();
                break;
            }
            case 11: {
                Logger.log(TAG, "Another pop");

                nodesStackContent.pop();
                break;
            }
            case 12: {
                Logger.log(TAG, "Attempting to pop base layer ");

                nodesStackContent.pop();
                break;
            }
        }

        nodesStackContent.refreshIntent();
        Logger.log(TAG, "Nodes: " + nodesStackContent.toString());
    }

    public void update(View view) {
        testingVariables();
        testingNodes();

        count++;
    }
}
