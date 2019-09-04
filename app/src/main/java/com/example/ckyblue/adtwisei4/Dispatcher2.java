package com.example.ckyblue.adtwisei4;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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
import Utility.Colors.Components;
import Utility.Data.Alteration;
import Utility.Data.Layer.Component;
import Utility.Themes.Cascades;

/*TODO A panel indicating active algorithm*/
/*TODO Reverting tab color after viewing*/

public class Dispatcher2 extends AppCompatActivity {
    private Utility.Data.Layer.Printer dataLayerSecondaryPrinter = new Utility.Data.Layer.Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, Component component, String componentKey) {
            onDataLayerUpdated();
        }

        @Override
        public void notifyOfFeedRebuild() {
            onDataLayerUpdated();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };
    private Utility.SourceCode.Layer.Printer sourceCodeLayerSecondaryPrinter = new Utility.SourceCode.Layer.Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            onSourceCodeLayerUpdated();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };
    private Utility.Logs.Printer logsSecondaryPrinter = new Utility.Logs.Printer() {
        @Override
        public void notifyOfNewLog() {
            onLogUpdated();
        }

        @Override
        public void notifyOfFeedRebuild() {
            onLogUpdated();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };
    private Utility.Logs.Printer outputSecondaryPrinter = new Utility.Logs.Printer() {
        @Override
        public void notifyOfNewLog() {
            onOutputUpdated();
        }

        @Override
        public void notifyOfFeedRebuild() {
            onOutputUpdated();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };

    private int cmdCount = 0;
    private int nextBtnPressCounts = 0;

    private Printer processPrinter = new Printer() {
        @Override
        public void notifyOfCmdDispatched() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfCmdDispatched() called");
            Toast.makeText(Dispatcher2.this, "Cmd " + getContent().getIdOfCmdInOperation() + " ran.", Toast.LENGTH_SHORT).show();

            getContent().refreshIntent();
        }

        @Override
        public void notifyOfNewCmdInOperation() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewCmdInOperation() called");
            onNewCmd();
        }

        @Override
        public void notifyOfNewAlgorithmLoaded() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewAlgorithmLoaded() called");
            Toast.makeText(Dispatcher2.this, "Algorithm loaded.", Toast.LENGTH_LONG).show();

            nextBtn.setVisibility(View.VISIBLE);
        }

        @Override
        public void notifyOfAlgorithmTerminated() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfAlgorithmTerminated() called");
            Toast.makeText(Dispatcher2.this, "Algorithm Terminated", Toast.LENGTH_SHORT).show();

            nextBtn.setVisibility(View.GONE);
        }

        @Override
        public void notifyOfInputReaderInOperation() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReaderInOperation() called");
            inputPanel.setVisibility(View.VISIBLE);
            Toast.makeText(Dispatcher2.this, "Taking input", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void notifyOfInputReceived() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReceived() called");
        }

        @Override
        public void notifyOfInputHandled() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputHandled() called");
            inputPanel.setVisibility(View.GONE);
            Toast.makeText(Dispatcher2.this, "Input handled", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Dispatcher2.this, "Starting Initializer Algorithm", Toast.LENGTH_LONG).show();

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
            Utility.Logs.Feed logsFeed = new Utility.Logs.Feed();
            Utility.Logs.Feed outputFeed = new Utility.Logs.Feed();

            Utility.SourceCode.Layer.Feed srcCodeLayerFeed = new Utility.SourceCode.Layer.Feed();
            Utility.Data.Layer.Feed dataLayerFeed = new Utility.Data.Layer.Feed();

            if (getContent() != null) {
                logsFeed = getContent().getLogsFeed();
                outputFeed = getContent().getOutputFeed();
                srcCodeLayerFeed = getContent().getSourceCodeLayerFeed();
                dataLayerFeed = getContent().getDataLayerFeed();

            }

            loggerFragment.setFeed(logsFeed);
            logsSecondaryPrinter.setFeed(logsFeed);

            outputFragment.setFeed(null);
            outputSecondaryPrinter.setFeed(outputFeed);

            sourceCodesFragment.setFeed(srcCodeLayerFeed);
            sourceCodeLayerSecondaryPrinter.setFeed(srcCodeLayerFeed);

            dataLayerFragment.setFeed(dataLayerFeed);
            dataLayerSecondaryPrinter.setFeed(dataLayerFeed);
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

    private static final int sourceCodesFrameKey = 0;
    private static final int dataLayerFrameKey = 1;
    private static final int logFrameKey = 2;
    private static final int outputFrameKey = 3;

    private final SparseArray<String> tabHeaders = new SparseArray<>();
    private final SparseArray<FrameLayout> frames = new SparseArray<>();
    private final SparseArray<Button> headerBtns = new SparseArray<>();

    {
        tabHeaders.put(sourceCodesFrameKey, "Code");
        tabHeaders.put(dataLayerFrameKey, "Data");
        tabHeaders.put(logFrameKey, "Log");
        tabHeaders.put(outputFrameKey, "Output");
    }

    private static final int frameKeyCount = 4;
    private static final int defaultFrameKey = sourceCodesFrameKey;

    private void validateFrameKey(int frameKey) {
        if (frameKey < 0 || frameKey >= frameKeyCount) {
            throw new IllegalArgumentException(frameKey + " is an invalid frame key.");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dispatcher_2);

        Bundle bundle = getIntent().getExtras();
        String algorithmKey = (String) bundle.get(String.valueOf(ListActivity.tree_key));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(algorithmKey);
        }

        process = new Content();

        loggerFragment = (Logger) getSupportFragmentManager().findFragmentById(R.id.loggerFragment);
        outputFragment = (Output) getSupportFragmentManager().findFragmentById(R.id.outputFragment);
        sourceCodesFragment = (SourceCodes) getSupportFragmentManager().findFragmentById(R.id.srcCodeLayerFragment);
        dataLayerFragment = (DataLayer) getSupportFragmentManager().findFragmentById(R.id.dataLayerFragment);

        frames.put(logFrameKey, (FrameLayout) findViewById(R.id.loggerFrame));
        frames.put(outputFrameKey, (FrameLayout) findViewById(R.id.outputFrame));
        frames.put(sourceCodesFrameKey, (FrameLayout) findViewById(R.id.sourceCodeLayerFrame));
        frames.put(dataLayerFrameKey, (FrameLayout) findViewById(R.id.dataLayerFrame));

        LinearLayout nav = findViewById(R.id.nav);
        for (int frameCount = 0; frameCount < frameKeyCount; frameCount++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1.0f));
            btn.setText(tabHeaders.get(frameCount));
            btn.setTag(frameCount);
            btn.setTextSize(11);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayFrame((int) v.getTag());
                }
            });
            btn.setTextColor(currentTabTextColor);
            headerBtns.put(frameCount, btn);
            nav.addView(btn);
        }

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
        inputPanel.setVisibility(View.GONE);
        inputPanel.setReceiver(process.getInputReceiver());

        resourcesPrinter.setFeed(process.getResourcesFeed());

        processPrinter.setFeed(new Feed());
        processPrinter.getFeed().setContent(process);

        process.setAlgorithmTree(Implementations.algorithmTrees.get(algorithmKey));

        displayFrame(defaultFrameKey);
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "Process: " + process.toString());
    }

    private Utility.Colors.Chrome.Content tabHeaderChrome = Cascades.ContentStreamers.Crimson.getChrome();

    private int defaultTabTextColor = Color.parseColor(Utility.Colors.Color.gray.getHexARGB());
    private int currentTabTextColor = defaultTabTextColor;
    ;
    private int defaultTabColor = Color.parseColor(Utility.Colors.Color.isabelline.getHexARGB());
    private int currentTabColor = defaultTabColor;

    private void onNewCmd() {
        cmdCount++;

        for (int i = 0; i < tabColorsUpdates.size(); i++) {
            int key = tabColorsUpdates.keyAt(i);
            tabColorsUpdates.put(key, false);
        }

        Components colorComponents = tabHeaderChrome.fetchComponents(String.valueOf(cmdCount), cmdCount);
        currentTabColor = Color.parseColor(colorComponents.getBg_color());
        currentTabTextColor = Color.parseColor(colorComponents.getText_color());
    }

    private void onDataLayerUpdated() {
        updateTabHeaderColor(dataLayerFrameKey);
    }

    private void onSourceCodeLayerUpdated() {
        updateTabHeaderColor(sourceCodesFrameKey);
    }

    private void onLogUpdated() {
        updateTabHeaderColor(logFrameKey);
    }

    private void onOutputUpdated() {
        updateTabHeaderColor(outputFrameKey);
    }

    private final SparseBooleanArray tabColorsUpdates = new SparseBooleanArray();

    private void updateTabHeaderColor(int frameKey) {
        validateFrameKey(frameKey);

        boolean tabColorUpdated = tabColorsUpdates.get(frameKey);
        if (tabColorUpdated) {
            return;
        }

        tabColorsUpdates.put(frameKey, true);
        headerBtns.get(frameKey).setBackgroundColor(currentTabColor);
        headerBtns.get(frameKey).setTextColor(currentTabTextColor);
    }

    private void displayFrame(int frameKey) {
        validateFrameKey(frameKey);

        for (int frameCount = 0; frameCount < frameKeyCount; frameCount++) {
            frames.get(frameCount).setVisibility(View.INVISIBLE);
            headerBtns.get(frameCount).setTextSize(11);
            headerBtns.get(frameCount).setTypeface(null, Typeface.NORMAL);
        }

        frames.get(frameKey).setVisibility(View.VISIBLE);
        headerBtns.get(frameKey).setTypeface(null, Typeface.BOLD_ITALIC);
        headerBtns.get(frameKey).setTextSize(13);

        headerBtns.get(frameKey).setBackgroundColor(defaultTabColor);
        headerBtns.get(frameKey).setTextColor(defaultTabTextColor);
    }

    private void loadAlgorithm(String algorithmKey) {
        if (process.isAlgorithmInOperation()) {
            Toast.makeText(this, "An algorithm is already running. End it first.", Toast.LENGTH_LONG).show();

        } else {
            process.loadAlgorithm(algorithmKey);

        }
    }

    private void buildAlgorithmMenu() {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "buildAlgorithmMenu() called");

        algorithmMenu.removeAllViews();
        Button algorithmBtn;

        if (process.getAlgorithmKeys() != null) {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "buildAlgorithmMenu() building");

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

        com.example.ckyblue.adtwisei4.Logger.log(TAG, "next()::Count:" + nextBtnPressCounts);
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "Process: " + process.toString());
        nextBtnPressCounts++;
    }
}
