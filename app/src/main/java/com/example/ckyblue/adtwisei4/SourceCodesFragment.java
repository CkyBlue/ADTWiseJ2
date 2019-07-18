package com.example.ckyblue.adtwisei4;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import UI_Utils.CustomViews.SourceCodeUnitView;

import UI_Utils.SpanFactory;
import Utility.Colors.Values;
import Utility.SourceCode.FormattingKey;
import Utility.SourceCode.Layer.Content;
import Utility.SourceCode.Layer.Feed;
import Utility.SourceCode.Layer.Printer;

public class SourceCodesFragment extends Fragment {
    View rootView;
    LinearLayout srcCodeLayer_Container;

//    private final HashMap<String, LinearLayout> titledSrcCodeBoxes = new HashMap<>();

    SpanFactory baseFormatting, lineCountFormatting, highlightFormatting;

    {
        baseFormatting = new SpanFactory() {
            @Override
            public Object[] createSpans(int scopeCount) {
                TypefaceSpan typefaceSpan = new TypefaceSpan("serif-monospace");
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                        Color.parseColor(
                                Utility.Colors.Color.gray.getHexARGB()
                        )
                );
                return new Object[]{typefaceSpan, foregroundColorSpan};
            }
        };

        lineCountFormatting = new SpanFactory() {
            @Override
            public Object[] createSpans(int scopeCount) {
                Values customGray = Utility.Colors.Color.rick_black.createValues();

                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                        Color.parseColor(customGray.getHexARGB())
                );
                return new Object[]{foregroundColorSpan};
            }
        };

        highlightFormatting = new SpanFactory() {
            @Override
            public Object[] createSpans(int scopeCount) {
                BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(
                        Color.parseColor(Utility.Colors.Color.light_gray.getHexARGB())
                );
                return new Object[]{backgroundColorSpan};
            }
        };
    }

    Printer srcCodeLayer_Printer = new Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            rebuildSourceCodeViews();
        }
    };

    public void rebuildSourceCodeViews() {
        SourceCodeUnitView srcCodeUnitView;
        Utility.SourceCode.Unit.Feed unitFeed;
        Content layerContent;

        LinearLayout innerContainer;
        LinearLayout titleViewBox;

        srcCodeLayer_Container.removeAllViews();
//        titledSrcCodeBoxes.clear();

        layerContent = this.srcCodeLayer_Printer.getContent();

        if (layerContent != null) {
            for (String sourceCodeUnitKey : layerContent.getUnitKeys()) {
                unitFeed = this.srcCodeLayer_Printer.getContent().getUnitFeed(sourceCodeUnitKey);

                srcCodeUnitView = new SourceCodeUnitView(getContext());
                srcCodeUnitView.setFeed(unitFeed);

                srcCodeUnitView.setSpanFactory(FormattingKey.Highlighting, highlightFormatting);
                srcCodeUnitView.setSpanFactory(FormattingKey.Line_Count, lineCountFormatting);

                srcCodeUnitView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                titleViewBox = (LinearLayout) getLayoutInflater().inflate(R.layout.titled_src_code_box,
                        srcCodeLayer_Container, false);

                ((TextView) titleViewBox.findViewById(R.id.titleTextView)).setText(sourceCodeUnitKey);
                innerContainer = titleViewBox.findViewById(R.id.container);
                innerContainer.addView(srcCodeUnitView);

//                titledSrcCodeBoxes.put(sourceCodeUnitKey, titleViewBox);
                srcCodeLayer_Container.addView(titleViewBox);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_source_code, container, false);
        initUI();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initUI() {
        this.srcCodeLayer_Container = rootView.findViewById(R.id.container);
        rebuildSourceCodeViews();
    }

    public void setFeed(Feed layerFeed) {
        this.srcCodeLayer_Printer.setFeed(layerFeed);
    }
}
