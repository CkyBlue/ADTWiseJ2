package com.example.ckyblue.adtwisei4;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.HashSet;

import Utility.Colors.Values;
import Utility.SourceCode.Layer.Content;
import Utility.SourceCode.Layer.Feed;
import Utility.SourceCode.Layer.Printer;
import Utility.Utilities;

public class MainActivity extends AppCompatActivity {
    /*TODO Test working with Layer*/

    Content srcCodeLayer_Content;
    Feed srcCodeLayer_Feed;

    private final HashMap<String, LinearLayout> titledSrcCodeBoxes = new HashMap<>();

    SourceCodesFragment srcCodeLayer_Fragment;

    int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srcCodeLayer_Fragment = (SourceCodesFragment) getSupportFragmentManager().findFragmentById(R.id.sourceCodeFragment);
    }

    public void update(View view) {
        /*switch (step) {
            case 0: {
                srcCodeLayer_Feed = new Feed();
                this.srcCodeLayer_Fragment.setFeed(srcCodeLayer_Feed);

                break;
            }
            case 1: {
                srcCodeLayer_Content = new Content();
                srcCodeLayer_Feed.setLogText(srcCodeLayer_Content);

                break;
            }
            case 2: {
                HashSet<String> unitKeys = new HashSet<>();
                unitKeys.add("Pseudocode");
                unitKeys.add("Python");

                srcCodeLayer_Content.buildUnits(unitKeys);
                break;
            }
            case 3: {
                Utilities.SourceCode.Unit.Feed feed = srcCodeLayer_Content.getUnitFeed("Pseudocode");
                feed.getContent().setText(Utilities.readRawTextFile(this, R.raw.binary_tree_insert_pseudo));
                break;
            }
            case 4: {
                HashSet<String> unitKeys = new HashSet<>();
                unitKeys.add("Pseudocode");

                srcCodeLayer_Content = new Content();
                srcCodeLayer_Content.buildUnits(unitKeys);
                Utilities.SourceCode.Unit.Feed feed = srcCodeLayer_Content.getUnitFeed("Pseudocode");

                Utilities.SourceCode.Unit.Content unitContent = new Utilities.SourceCode.Unit.Content();
                unitContent.setText(Utilities.readRawTextFile(this, R.raw.binary_search));
                unitContent.highlight(new int[]{1, 3});

                feed.setLogText(unitContent);

                srcCodeLayer_Feed.setLogText(srcCodeLayer_Content);
                break;
            }
            case 5: {
                srcCodeLayer_Feed = new Feed();

                HashSet<String> unitKeys = new HashSet<>();
                unitKeys.add("Python");

                srcCodeLayer_Content = new Content();
                srcCodeLayer_Content.buildUnits(unitKeys);
                Utilities.SourceCode.Unit.Feed feed = srcCodeLayer_Content.getUnitFeed("Python");
                feed.getContent().setText(Utilities.readRawTextFile(this, R.raw.queue_insert_pseudo));
                feed.getContent().highlight(new int[]{1, 3});

                srcCodeLayer_Feed.setLogText(srcCodeLayer_Content);
                this.srcCodeLayer_Fragment.setFeed(srcCodeLayer_Feed);
                break;
            }
        }*/

        Log.i("Step", String.valueOf(step));
        step++;
    }
}
