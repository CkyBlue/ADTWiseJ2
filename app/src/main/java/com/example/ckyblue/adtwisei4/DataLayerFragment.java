package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import UI_Utils.CustomViews.DataViews.DataStackView;
import UI_Utils.CustomViews.DataViews.NodesStackView;
import UI_Utils.CustomViews.DataViews.VariablesStackView;
import Utility.Data.Alteration;
import Utility.Data.Layer.Component;
import Utility.Data.Layer.Content;
import Utility.Data.Layer.Feed;
import Utility.Data.Layer.Printer;

public class DataLayerFragment extends Fragment {
    String TAG = getClass().getName();
    private String dataStackViewTag = "dataStackViewTag";

    View rootView;
    LinearLayout dataLayer_Container;

    private final HashMap<String, LinearLayout> titledVariablesStackViews = new HashMap<>();
    private final HashMap<String, LinearLayout> titledNodesStackViews = new HashMap<>();

    private Printer dataLayer_Printer = new Printer() {
        private String TAG = "DataLayerFragment.DataLayerPrinter";

        @Override
        public void notifyOfContentAlteration(Alteration alteration, Component component, String componentKey) {
            Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + component + ", " + componentKey + ")");

            DataLayerFragment.this.notifyOfContentAlteration(alteration, component, componentKey);
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild()");

            DataLayerFragment.this.rebuildDataViews();
        }

        @Override
        public void notifyOfRefreshIntent() {
            Logger.log(TAG, "notifyOfRefreshIntent()");

            int count = 0;
            Content content = this.getContent();
            if (content != null) {
                for (Component component : Component.values()) {
                    for (String componentKey : content.getKeys(component)) {

                        count++;

                        switch (component) {
                            case NodesStack: {
                                content.getNodesStackFeed(componentKey).getContent().getUnit().refreshIntent();
                                break;

                            }
                            case VariablesStack: {
                                content.getVariablesStackFeed(componentKey).getContent().getUnit().refreshIntent();
                                break;

                            }
                        }
                    }
                }
            }

            Logger.log(TAG, "notifyOfRefreshIntent()::CallBacks:" + count);

        }
    };

    public void rebuildDataViews() {
        Logger.log(TAG, "rebuildDataViews()");

        Content layerContent;
        layerContent = this.dataLayer_Printer.getContent();

        dataLayer_Container.removeAllViews();

        /*The unit Feeds constituting a Layer contain references to Printer objects used for rendering them. Those references are removed
        ro prevent a memory leak.*/

        try {
            for (LinearLayout titledNodeStackView : titledNodesStackViews.values()) {
                ((NodesStackView) titledNodeStackView.findViewWithTag(dataStackViewTag)).setNodesStackFeed(null);
            }

            for (LinearLayout titledVariableStackView : titledVariablesStackViews.values()) {
                ((VariablesStackView) titledVariableStackView.findViewWithTag(dataStackViewTag)).setVariablesStackFeed(null);
            }

        } catch (NullPointerException e) {
            throw new IllegalStateException("Cannot find DataStackView within title view using tag designated to DataStackViews.");

        }

        titledNodesStackViews.clear();
        titledVariablesStackViews.clear();

        if (layerContent != null) {
            for (Component component : Component.values()) {
                for (String componentKey : layerContent.getKeys(component)) {
                    LinearLayout titledViewBox = buildTitledView(component, componentKey);

                    dataLayer_Container.addView(titledViewBox);

                    switch (component) {
                        case NodesStack: {
                            titledNodesStackViews.put(componentKey, titledViewBox);
                            break;

                        }
                        case VariablesStack: {
                            titledVariablesStackViews.put(componentKey, titledViewBox);
                            break;

                        }
                    }
                }
            }
        }
    }

    private LinearLayout buildTitledView(Component component, String componentKey) {
        Logger.log(TAG, "buildTitledView(" + component + ", " + componentKey + ")");

        Content layerContent = this.dataLayer_Printer.getContent();

        if (layerContent == null) {
            throw new IllegalStateException("Attempting to build views when Data.Layer.BaseContent is null.");

        } else if (!layerContent.getKeys(component).contains(componentKey)) {
            throw new IllegalArgumentException("Layer does not contain " + componentKey + " with key " + componentKey + ".");

        }

        Utility.Data.Variables.Stack.Feed variablesStackFeed;
        Utility.Data.Nodes.Stack.Feed nodesStackFeed;

        LinearLayout innerContainer;
        LinearLayout titleViewBox;

        DataStackView dataStackView = null;

        switch (component) {
            case NodesStack: {
                nodesStackFeed = layerContent.getNodesStackFeed(componentKey);

                dataStackView = new NodesStackView(getContext());
                ((NodesStackView) dataStackView).setNodesStackFeed(nodesStackFeed);

                break;
            }
            case VariablesStack: {
                variablesStackFeed = layerContent.getVariablesStackFeed(componentKey);

                dataStackView = new VariablesStackView(getContext());
                ((VariablesStackView) dataStackView).setVariablesStackFeed(variablesStackFeed);

                break;
            }
        }

        dataStackView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        dataStackView.setTag(dataStackViewTag);

        titleViewBox = (LinearLayout) getLayoutInflater().inflate(R.layout.titled_data_view_box,
                dataLayer_Container, false);

        ((TextView) titleViewBox.findViewById(R.id.titleTextView)).setText(componentKey);
        innerContainer = titleViewBox.findViewById(R.id.container);
        innerContainer.addView(dataStackView);

        return titleViewBox;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.log(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.log(TAG, "onCreateView()");
        rootView = inflater.inflate(R.layout.fragment_data_layer, container, false);
        initUI();
        return rootView;
    }

    @Override
    public void onResume() {
        Logger.log(TAG, "onResume()");
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.log(TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    public void initUI() {
        Logger.log(TAG, "initUI()");
        this.dataLayer_Container = rootView.findViewById(R.id.container);
        rebuildDataViews();
    }

    public void setFeed(Feed layerFeed) {
        this.dataLayer_Printer.setFeed(layerFeed);
    }

    public void notifyOfContentAlteration(Alteration alteration,
                                          Component component,
                                          String componentKey) {
        Logger.log(TAG, "notifyOfContentAlteration(" + alteration + ", " + component + ", " + componentKey + ")");

        LinearLayout titledViewBox;

        if (alteration == Alteration.component_added) {
            titledViewBox = buildTitledView(component, componentKey);

            dataLayer_Container.addView(titledViewBox);

            switch (component) {
                case NodesStack: {
                    titledNodesStackViews.put(componentKey, titledViewBox);
                    break;

                }
                case VariablesStack: {
                    titledVariablesStackViews.put(componentKey, titledViewBox);
                    break;

                }
            }

        } else if (alteration == Alteration.component_removed) {
            titledViewBox = null;

            switch (component) {

                case NodesStack: {
                    titledViewBox = titledNodesStackViews.get(componentKey);
                    titledNodesStackViews.remove(componentKey);
                    break;

                }
                case VariablesStack: {
                    titledViewBox = titledVariablesStackViews.get(componentKey);
                    titledVariablesStackViews.remove(componentKey);
                    break;

                }
            }

            this.dataLayer_Container.removeView(titledViewBox);
        }
    }
}
