package com.example.ckyblue.adtwisei4;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import UI_Utils.BounceInterpolator;
import UI_Utils.CustomViews.InputPanel;
import Utility.Algorithm.Process.Content;
import Utility.Algorithm.Process.Feed;
import Utility.Algorithm.Process.Printer;
import Utility.Colors.Components;
import Utility.Data.Alteration;
import Utility.Data.Layer.Component;
import Utility.Themes.Cascades;
import Utility.Themes.Defaults;

/*TODO Configuring customizations to Data Views*/

public class Dispatcher extends AppCompatActivity {
    private Utility.Data.Layer.Printer dataLayerSecondaryPrinter = new Utility.Data.Layer.Printer() {
        @Override
        public void notifyOfChangeToUnit() {
            onDataLayerUpdated();
        }

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
        public void notifyOfHideIntent(String unitkey) {
            onSourceCodeLayerUpdated();
        }

        @Override
        public void notifyOfShowIntent(String unitkey) {
            onSourceCodeLayerUpdated();
        }

        @Override
        public void notifyOfFeedRebuild() {
            onSourceCodeLayerUpdated();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }

        @Override
        public void notifyOfChangeToUnit() {
            onSourceCodeLayerUpdated();
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
//            Toast.makeText(Dispatcher.this, "Cmd " + getContent().getIdOfCmdInOperation() + " ran.", Toast.LENGTH_SHORT).show();

            getContent().refreshIntent();
        }

        @Override
        public void notifyOfNewCmdInOperation() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewCmdInOperation() called");
            onNewCmd();
        }

        @Override
        public void notifyOfNewAlgorithmLoaded(String algorithmKey) {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewAlgorithmLoaded() called");
            newAlgorithmLoaded(algorithmKey);
        }

        @Override
        public void notifyOfAlgorithmTerminated() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfAlgorithmTerminated() called");
            Toast.makeText(Dispatcher.this, "Algorithm Terminated", Toast.LENGTH_SHORT).show();

            bottomBar.setVisibility(View.GONE);
            algorithmMenuContainer.setVisibility(View.VISIBLE);
        }

        @Override
        public void notifyOfInputReaderInOperation() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReaderInOperation() called");
            inputPanel.setVisibility(View.VISIBLE);
            Toast.makeText(Dispatcher.this, "Taking input", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void notifyOfInputReceived() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReceived() called");
        }

        @Override
        public void notifyOfInputHandled() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputHandled() called");
            inputPanel.setVisibility(View.GONE);
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
                    bottomBar.setVisibility(View.VISIBLE);

                } else {
                    bottomBar.setVisibility(View.GONE);

                }
            }
        }

        @Override
        public void notifyOfNewAlgorithmTree() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfNewAlgorithmTree() called");
//            Toast.makeText(Dispatcher.this, "Starting Initializer Algorithm", Toast.LENGTH_LONG).show();

            nextBtn.setBackgroundColor(inertTabColor);
            nextBtn.setTextColor(inertTabTextColor);

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
    private LinearLayout algorithmMenuContainer;

    private LinearLayout bottomBar;
    private TextView currentAlgorithmTitleView;
    private Button nextBtn;

    private String TAG = getClass().getName();

    private static final int sourceCodesFrameKey = 0;
    private static final int dataLayerFrameKey = 1;
    private static final int logFrameKey = 2;
    private static final int outputFrameKey = 3;

    private final SparseArray<String> tabHeaders = new SparseArray<>();
    private final SparseArray<FrameLayout> frames = new SparseArray<>();

    private final SparseArray<Button> headerBtns = new SparseArray<>();
    private final SparseArray<TextView> headerBtnBlinkers = new SparseArray<>();

    private final SparseBooleanArray tabColorsUpdates = new SparseBooleanArray();

    {
        tabHeaders.put(sourceCodesFrameKey, "Code");
        tabHeaders.put(dataLayerFrameKey, "Data");
        tabHeaders.put(logFrameKey, "Log");
        tabHeaders.put(outputFrameKey, "Output");
    }

    private static final int frameKeyCount = 4;
    private static final int defaultFrameKey = sourceCodesFrameKey;

    private Utility.Colors.Chrome.Content tabHeaderChrome = Cascades.ContentStreamers.AquaGreens.getChrome();

    private int inertTabColor = Color.parseColor(Utility.Colors.Color.isabelline.getHexARGB());
    private int inertTabTextColor = Color.parseColor(Utility.Colors.Color.gray.getHexARGB());

    private int activeTabColor = Color.parseColor(Utility.Colors.Color.light_gray.getHexARGB());
    private int activeTabTextColor = Color.parseColor(Utility.Colors.Color.rick_black.getHexARGB());

    private int inertBlinkerColor = Color.parseColor(Utility.Colors.Color.gray.getHexARGB());
    ;
    private int currentBlinkerColor = inertTabColor;

    private void validateFrameKey(int frameKey) {
        if (frameKey < 0 || frameKey >= frameKeyCount) {
            throw new IllegalArgumentException(frameKey + " is an invalid frame key.");
        }
    }

    private LinearLayout buildTabHeader(int frameCount, String text) {
        LinearLayout.LayoutParams tabHeadLayoutParams = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        tabHeadLayoutParams.setMargins(3, 0, 3, 0);

        LinearLayout tabHeaderBox = (LinearLayout) getLayoutInflater().inflate(
                R.layout.blinking_tab_headers,
                (LinearLayout) findViewById(R.id.nav),
                false);

        tabHeaderBox.setLayoutParams(tabHeadLayoutParams);

        Button btn = tabHeaderBox.findViewById(R.id.button);
        TextView blinker = tabHeaderBox.findViewById(R.id.blinker);

        btn.setText(text);
        btn.setTag(frameCount);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFrame((int) v.getTag());
            }
        });

        btn.setTextColor(inertTabTextColor);
        btn.setBackgroundColor(inertTabColor);

        blinker.setBackgroundColor(inertBlinkerColor);

        return tabHeaderBox;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher);

        Bundle bundle = getIntent().getExtras();
        String algorithmKey = (String) bundle.get(String.valueOf(MainActivity.tree_key));

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
            LinearLayout tabHeaderBox = buildTabHeader(frameCount, tabHeaders.get(frameCount));

            headerBtns.put(frameCount, (Button) tabHeaderBox.findViewById(R.id.button));
            headerBtnBlinkers.put(frameCount, (TextView) tabHeaderBox.findViewById(R.id.blinker));

            nav.addView(tabHeaderBox);
        }

        algorithmMenu = findViewById(R.id.algorithmMenu);
        algorithmMenuContainer = findViewById(R.id.algorithmMenuContainer);
        TextView titleView = findViewById(R.id.titleTextView);
        titleView.setText("Algorithm Menu");

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setVisibility(View.GONE);

        currentAlgorithmTitleView = findViewById(R.id.currentAlgorithm);
        currentAlgorithmTitleView.setBackgroundColor(inertTabColor);
        currentAlgorithmTitleView.setTextColor(inertTabTextColor);
        currentAlgorithmTitleView.setTypeface(Typeface.MONOSPACE);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setBackgroundColor(inertTabColor);
        nextBtn.setTextColor(inertTabTextColor);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation myAnim = AnimationUtils.loadAnimation(Dispatcher.this, R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 20);
                myAnim.setInterpolator(interpolator);
                v.startAnimation(myAnim);

                next();
            }
        });

        inputPanel = findViewById(R.id.inputPanel);
        inputPanel.setVisibility(View.GONE);
        inputPanel.setReceiver(process.getInputReceiver());
        inputPanel.setTitleText("Input");

        resourcesPrinter.setFeed(process.getResourcesFeed());

        processPrinter.setFeed(new Feed());
        processPrinter.getFeed().setContent(process);

        process.setAlgorithmTree(Implementations.algorithmTrees.get(algorithmKey));

        displayFrame(defaultFrameKey);
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "Process: " + process.toString());
    }

    private void onNewCmd() {
        cmdCount++;

        for (int frameCount = 0; frameCount < frameKeyCount; frameCount++) {
            tabColorsUpdates.put(frameCount, false);
        }

        Components colorComponents = tabHeaderChrome.fetchComponents(String.valueOf(cmdCount), cmdCount);
        currentBlinkerColor = Color.parseColor(colorComponents.getBg_color());
        int blinkerSyncedTextColor = Color.parseColor(colorComponents.getText_color());

        currentAlgorithmTitleView.setBackgroundColor(currentBlinkerColor);
        currentAlgorithmTitleView.setTextColor(blinkerSyncedTextColor);
    }

    private void onDataLayerUpdated() {
        updateDeltaIndicator(dataLayerFrameKey);
    }

    private void onSourceCodeLayerUpdated() {
        updateDeltaIndicator(sourceCodesFrameKey);
    }

    private void onLogUpdated() {
        updateDeltaIndicator(logFrameKey);
    }

    private void onOutputUpdated() {
        updateDeltaIndicator(outputFrameKey);
    }

    private void updateDeltaIndicator(int frameKey) {
        validateFrameKey(frameKey);

        boolean tabColorUpdated = tabColorsUpdates.get(frameKey);
        if (tabColorUpdated) {
            return;
        }

        tabColorsUpdates.put(frameKey, true);
        headerBtnBlinkers.get(frameKey).setBackgroundColor(currentBlinkerColor);
    }

    private void displayFrame(int frameKey) {
        validateFrameKey(frameKey);

        // Clears changes to all buttons & hides all frames
        for (int frameCount = 0; frameCount < frameKeyCount; frameCount++) {
            frames.get(frameCount).setVisibility(View.INVISIBLE);

            Button button = headerBtns.get(frameCount);

            button.setTextSize(11);
            button.setTypeface(null, Typeface.NORMAL);
            button.setBackgroundColor(inertTabColor);
            button.setTextColor(inertTabTextColor);

            if ((button.getPaintFlags() & Paint.UNDERLINE_TEXT_FLAG) > 0) {
                button.setPaintFlags(button.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
            }
        }

        // Shows intended frame and affects active button
        frames.get(frameKey).setVisibility(View.VISIBLE);

        Button activeBtn = headerBtns.get(frameKey);

        activeBtn.setTextSize(13);
        activeBtn.setTypeface(null, Typeface.BOLD_ITALIC);
        activeBtn.setBackgroundColor(activeTabColor);
        activeBtn.setTextColor(activeTabTextColor);

        activeBtn.setPaintFlags(activeBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void loadAlgorithm(String algorithmKey) {
        if (process.isAlgorithmInOperation()) {
            Toast.makeText(this, "An algorithm is already running. End it first.", Toast.LENGTH_LONG).show();

        } else {
            process.loadAlgorithm(algorithmKey);
        }
    }

    private void newAlgorithmLoaded(String algorithmKey) {
        Toast.makeText(Dispatcher.this, "Algorithm \"" + algorithmKey + "\" loaded.", Toast.LENGTH_LONG).show();

        bottomBar.setVisibility(View.VISIBLE);
        algorithmMenuContainer.setVisibility(View.GONE);

        String algorithmTitleString = "Active Algorithm : \"" + algorithmKey + "\"";
        currentAlgorithmTitleView.setText(algorithmTitleString);

        for (int frameCount = 0; frameCount < frameKeyCount; frameCount++) {
            headerBtnBlinkers.get(frameCount).setBackgroundColor(inertBlinkerColor);
        }
    }

    private void buildAlgorithmMenu() {
        com.example.ckyblue.adtwisei4.Logger.log(TAG, "buildAlgorithmMenu() called");

        algorithmMenu.removeAllViews();
        Button algorithmBtn;

        if (process.getAlgorithmKeys() != null) {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "buildAlgorithmMenu() building");
            LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    0, 1);
            btnParams.setMargins(7, 7, 7, 7);

            for (String algorithmKey : process.getAlgorithmKeys()) {
                algorithmBtn = new Button(this);
                algorithmBtn.setLayoutParams(btnParams);

                algorithmBtn.setAllCaps(false);
                algorithmBtn.setBackgroundColor(Color.parseColor(Utility.Colors.Color.light_gray.getHexARGB()));
                algorithmBtn.setTextColor(Color.parseColor(Defaults.defaultDarkText().getHexARGB()));
                algorithmBtn.setTypeface(Typeface.MONOSPACE);
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
