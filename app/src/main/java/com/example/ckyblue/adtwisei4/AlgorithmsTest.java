package com.example.ckyblue.adtwisei4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import Implementations.Tests;
import Utility.Algorithm.Process;

public class AlgorithmsTest extends AppCompatActivity {
    private LoggerFragment loggerFragment;
    private OutputFragment outputFragment;

    private DataLayerFragment dataLayerFragment;
    private SourceCodesFragment sourceCodesFragment;

    private String TAG = getClass().getName();

    private Process process;

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_3);

        process = new Process() {
            @Override
            public void onCmdDispatched() {
                Logger.log(TAG, "onCmdDispatched() called");

                refreshIntent();
            }
        };

        process.setAlgorithmTree(Tests.tree);
        process.loadAlgorithm("Algorithm 1");

        loggerFragment = (LoggerFragment) getSupportFragmentManager().findFragmentById(R.id.loggerFragment);
        outputFragment = (OutputFragment) getSupportFragmentManager().findFragmentById(R.id.outputFragment);
        sourceCodesFragment = (SourceCodesFragment) getSupportFragmentManager().findFragmentById(R.id.srcCodeLayerFragment);
        dataLayerFragment = (DataLayerFragment) getSupportFragmentManager().findFragmentById(R.id.dataLayerFragment);

        loggerFragment.setFeed(process.getResources().getLogsFeed());
        outputFragment.setFeed(process.getResources().getOutputFeed());
        sourceCodesFragment.setFeed(process.getResources().getSourceCodeLayerFeed());
        dataLayerFragment.setFeed(process.getResources().getDataLayerFeed());
    }

    private void test() {
        Logger.log(TAG, "process.execute() called");
        process.execute();
    }

    public void update(View view) {
        test();

        Logger.log(TAG, "update()::Count:" + count);
        count++;
    }
}
