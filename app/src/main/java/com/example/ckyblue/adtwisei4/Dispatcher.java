package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
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
import Utility.Algorithm.Process.Printer;
import Utility.Logs.Feed;

/*TODO A panel indicating active algorithm*/

public class Dispatcher extends AppCompatActivity {
    private class StateInfoPagerAdapter extends FragmentStatePagerAdapter {
        private String TAG = getClass().getName();

        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
        private static final int NUM_PAGES = 4;

        static final int sourceCodeFragPosition = 0;
        static final int dataLayerFragPosition = 1;
        static final int logFragPosition = 2;
        static final int outputFragPosition = 3;

        StateInfoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "getItem() called with: position = [" + position + "]");
            switch (count) {
                case sourceCodeFragPosition: {
                    SourceCodes sourceCodesFragment = new SourceCodes();
                    sourceCodesFragment.setFeed(process.getResources().getSourceCodeLayerFeed());
                    return sourceCodesFragment;
                }
                case dataLayerFragPosition: {
                    DataLayer dataLayerFragment = new DataLayer();
                    dataLayerFragment.setFeed(process.getResources().getDataLayerFeed());
                    return dataLayerFragment;
                }
                case logFragPosition: {
                    Logger loggerFragment = new Logger();
                    loggerFragment.setFeed(process.getResources().getLogsFeed());
                    return loggerFragment;
                }
                case outputFragPosition: {
                    Output outputFragment = new Output();
                    outputFragment.setFeed(process.getResources().getOutputFeed());
                    return outputFragment;
                }
            }

            throw new IllegalStateException("Unanticipated fragment slot.");
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }


        @Override
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }

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
        public void notifyOfNewAlgorithmLoaded() {
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
            inputPanel.setVisibility(View.VISIBLE);
        }

        @Override
        public void notifyOfInputReceived() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputReceived() called");
        }

        @Override
        public void notifyOfInputHandled() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfInputHandled() called");
            Toast.makeText(Dispatcher.this, "Input handled", Toast.LENGTH_SHORT).show();
            inputPanel.setVisibility(View.GONE);
        }

        private String TAG = getClass().getName();

        @Override
        public void notifyOfFeedRebuild() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfFeedRebuild() called");

            if (getContent() != null) {
                resourcesPrinter.setFeed(getContent().getResourcesFeed());
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
            notifyOfFeedRebuild();
        }

        @Override
        public void notifyOfRefreshIntent() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfRefreshIntent() called");
        }
    };

    private Utility.Resources.Printer resourcesPrinter = new Utility.Resources.Printer() {
        private String TAG = getClass().getName();

        @Override
        public void notifyOfFeedRebuild() {
            com.example.ckyblue.adtwisei4.Logger.log(TAG, "notifyOfFeedRebuild() called");

            Logger logger = (Logger) pagerAdapter.getRegisteredFragment(StateInfoPagerAdapter.logFragPosition);
            Output output = (Output) pagerAdapter.getRegisteredFragment(StateInfoPagerAdapter.outputFragPosition);
            SourceCodes sourceCodes = (SourceCodes) pagerAdapter.getRegisteredFragment(StateInfoPagerAdapter.sourceCodeFragPosition);
            DataLayer dataLayer = (DataLayer) pagerAdapter.getRegisteredFragment(StateInfoPagerAdapter.dataLayerFragPosition);

            Feed logFeed = null;
            Feed outputFeed = null;
            Utility.SourceCode.Layer.Feed sourceCodeLayerFeed = null;
            Utility.Data.Layer.Feed dataLayerFeed = null;

            if (getContent() != null) {
                logFeed = getContent().getLogsFeed();
                outputFeed = getContent().getOutputFeed();
                sourceCodeLayerFeed = getContent().getSourceCodeLayerFeed();
                dataLayerFeed = getContent().getDataLayerFeed();

            }

            if (logger != null) {
                logger.setFeed(logFeed);
            } else {
                Toast.makeText(Dispatcher.this, "logger is null", Toast.LENGTH_SHORT).show();
            }
            if (output != null) {
                output.setFeed(outputFeed);
            }
            if (sourceCodes != null) {
                sourceCodes.setFeed(sourceCodeLayerFeed);
            }
            if (dataLayer != null) {
                dataLayer.setFeed(dataLayerFeed);
            }
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };

    private Content process;

    private InputPanel inputPanel;

    private LinearLayout algorithmMenu;
    private Button nextBtn;

    private String TAG = getClass().getName();

    int count = 0;

    private ViewPager mPager;
    private StateInfoPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dipatcher);

        Bundle bundle = getIntent().getExtras();
        String algorithmKey = (String) bundle.get(String.valueOf(ListActivity.tree_key));

        mPager = findViewById(R.id.pager);
        pagerAdapter = new StateInfoPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        process = new Content();

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

        process.setAlgorithmTree(Implementations.algorithmTrees.get(algorithmKey));

        com.example.ckyblue.adtwisei4.Logger.log(TAG, "Process: " + process.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();

        processPrinter.setFeed(new Utility.Algorithm.Process.Feed());
        processPrinter.getFeed().setContent(process);
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
