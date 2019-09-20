package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ckyblue.adtwisei4.Fragments.DataLayer;
import com.example.ckyblue.adtwisei4.Fragments.Logger;
import com.example.ckyblue.adtwisei4.Fragments.Output;
import com.example.ckyblue.adtwisei4.Fragments.SourceCodes;

import Implementations.Implementations;
import UI_Utils.CustomViews.InputPanel;
import Utility.Algorithm.Process.Content;
import Utility.Algorithm.Process.Feed;
import Utility.Algorithm.Process.Printer;

/*TODO A panel indicating active algorithm*/

public class Dispatcher extends AppCompatActivity {
    private Printer processPrinter = new Printer() {
        @Override
        public void notifyOfCmdDispatched() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfCmdDispatched() called");
            Toast.makeText(Dispatcher.this, "Cmd " + getContent().getIdOfCmdInOperation() + " ran.", Toast.LENGTH_SHORT).show();

            getContent().refreshIntent();
        }

        @Override
        public void notifyOfNewCmdInOperation() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewCmdInOperation() called");
        }

        @Override
        public void notifyOfNewAlgorithmLoaded(String algorithmKey) {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewAlgorithmLoaded() called");
            Toast.makeText(Dispatcher.this, "Algorithm loaded.", Toast.LENGTH_LONG).show();

            nextBtn.setVisibility(View.VISIBLE);
        }

        @Override
        public void notifyOfAlgorithmTerminated() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfAlgorithmTerminated() called");
            Toast.makeText(Dispatcher.this, "Algorithm Terminated", Toast.LENGTH_SHORT).show();

            nextBtn.setVisibility(View.GONE);
        }

        @Override
        public void notifyOfInputReaderInOperation() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReaderInOperation() called");
            Toast.makeText(Dispatcher.this, "Taking input", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void notifyOfInputReceived() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReceived() called");
        }

        @Override
        public void notifyOfInputHandled() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputHandled() called");
            Toast.makeText(Dispatcher.this, "Input handled", Toast.LENGTH_SHORT).show();
        }

        private String TAG = getClass().getName();

        @Override
        public void notifyOfFeedRebuild() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfFeedRebuild() called");

            if (getContent() != null) {
                loggerFragment.setFeed(getContent().getResources().getLogsFeed());
                outputFragment.setFeed(getContent().getResources().getOutputFeed());
                sourceCodesFragment.setFeed(getContent().getResources().getSourceCodeLayerFeed());
                dataLayerFragment.setFeed(getContent().getResources().getDataLayerFeed());

                buildAlgorithmMenu();

                if (getContent().isAlgorithmInOperation()) {
                    nextBtn.setVisibility(View.VISIBLE);

                } else {
                    nextBtn.setVisibility(View.GONE);

                }
            }
        }

        @Override
        public void notifyOfNewAlgorithmTree() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewAlgorithmTree() called");
            buildAlgorithmMenu();
        }

        @Override
        public void notifyOfRefreshIntent() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfRefreshIntent() called");
        }
    };

    private Utility.Resources.Printer resourcesPrinter = new Utility.Resources.Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            if (getContent() != null) {
                loggerFragment.setFeed(getContent().getLogsFeed());
                outputFragment.setFeed(getContent().getOutputFeed());
                sourceCodesFragment.setFeed(getContent().getSourceCodeLayerFeed());
                dataLayerFragment.setFeed(getContent().getDataLayerFeed());

            } else {
                loggerFragment.setFeed(null);
                outputFragment.setFeed(null);
                sourceCodesFragment.setFeed(null);
                dataLayerFragment.setFeed(null);

            }
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };

    private Content process;

    private InputPanel inputPanel;

    private Logger loggerFragment;
    private Output outputFragment;

    private DataLayer dataLayerFragment;
    private SourceCodes sourceCodesFragment;

    private LinearLayout algorithmMenu;
    private Button nextBtn;

    private String TAG = getClass().getName();

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_3);

        Bundle bundle = getIntent().getExtras();
        String algorithmKey = (String) bundle.get(String.valueOf(ListActivity.tree_key));

        process = new Content();

        loggerFragment = (Logger) getSupportFragmentManager().findFragmentById(R.id.loggerFragment);
        outputFragment = (Output) getSupportFragmentManager().findFragmentById(R.id.outputFragment);
        sourceCodesFragment = (SourceCodes) getSupportFragmentManager().findFragmentById(R.id.srcCodeLayerFragment);
        dataLayerFragment = (DataLayer) getSupportFragmentManager().findFragmentById(R.id.dataLayerFragment);

        algorithmMenu = findViewById(R.id.algorithmMenu);
        TextView titleView = findViewById(R.id.titleTextView);
        titleView.setText("Algorithm Menu");

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setVisibility(View.GONE);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        inputPanel = findViewById(R.id.inputPanel);
        inputPanel.setReceiver(process.getInputReceiver());

        resourcesPrinter.setFeed(process.getResourcesFeed());
        process.setAlgorithmTree(Implementations.algorithmTrees.get(algorithmKey));

        processPrinter.setFeed(new Feed());
        processPrinter.getFeed().setContent(process);

        com.example.ckyblue.adtwisei4.Logger.log(TAG, "Process: " + process.toString());
    }

    private void loadAlgorithm(String algorithmKey) {
        if (process.isAlgorithmInOperation()) {
            Toast.makeText(this, "An algorithm is already running. End it first.", Toast.LENGTH_LONG).show();

        } else {
            process.loadAlgorithm(algorithmKey);

        }
    }

    private void buildAlgorithmMenu() {
        algorithmMenu.removeAllViews();
        Button algorithmBtn;

        if (process.getAlgorithmKeys() != null) {
            for (String algorithmKey : process.getAlgorithmKeys()) {
                algorithmBtn = new Button(this);

                algorithmBtn.setText(algorithmKey);
                algorithmBtn.setTag(algorithmKey);

                algorithmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadAlgorithm(String.valueOf(v.getTag()));
                    }
                });

                algorithmMenu.addView(algorithmBtn);
            }
        }
    }

    private void next() {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "next() called");

        if (process.isAlgorithmTreeLoaded()) {
            if (process.getInputReceiver().isReaderActive()) {
                Toast.makeText(this, "Awaiting Input", Toast.LENGTH_SHORT).show();
            } else {
                process.execute();
            }
        }

        com.example.ckyblue.adtwisei4.Logger.log(TAG, "next()::Count:" + count);
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "Process: " + process.toString());
        count++;
    }
}
