//package com.example.ckyblue.adtwisei4.Fragments;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.ckyblue.adtwisei4.R;
//
//import java.util.HashMap;
//
//import UI_Utils.CustomViews.SourceCodeUnitView;
//import UI_Utils.SingleChainView;
//import Utility.SourceCode.FormattingKey;
//import Utility.SourceCode.Layer.Content;
//import Utility.SourceCode.Layer.Feed;
//import Utility.SourceCode.Layer.Printer;
//
///*TODO Every content object having a name so it can propagate upwards in Compositions*/
///*TODO A Type class superclass that is generic for BaseFeed so HashMap<String, DiagramViews> have values accepting setFeed()s*/
//
//public class Diagrams {
//    private View rootView;
//    private LinearLayout rootContainer;
//
//    private final HashMap<String, LinearLayout> diagramBoxes = new HashMap<>();
//    private final HashMap<String, SingleChainView> singleChainViews = new HashMap<>();
//
//
//}
//
//class SourceCodes extends Fragment {
//    Printer srcCodeLayer_Printer = new Printer() {
//        @Override
//        public void notifyOfRefreshIntent() {
//
//        }
//
//        @Override
//        public void notifyOfFeedRebuild() {
//            if (srcCodeLayer_Container != null) {
//                rebuildSourceCodeViews();
//            }
//        }
//
//        @Override
//        public void notifyOfHideIntent(String unitkey) {
//            hideUnit(unitkey);
//        }
//
//        @Override
//        public void notifyOfShowIntent(String unitkey) {
//            showUnit(unitkey);
//        }
//    };
//
//    private void hideUnit(String key) {
//        titledSrcCodeBoxes.get(key).setVisibility(View.GONE);
//    }
//
//    private void showUnit(String key) {
//        titledSrcCodeBoxes.get(key).setVisibility(View.VISIBLE);
//    }
//
//    public void rebuildSourceCodeViews() {
//        SourceCodeUnitView srcCodeUnitView;
//        Utility.SourceCode.Unit.Feed unitFeed;
//        Content layerContent;
//
//        LinearLayout innerContainer;
//        LinearLayout titleViewBox;
//
//        /*The unit Feeds constituting a Layer contain references to BasePrinter objects used for rendering them. Those references are removed
//        ro prevent a memory leak.*/
//
//        for (SourceCodeUnitView sourceCodeUnitView : sourceCodeUnitViews.values()) {
//            sourceCodeUnitView.setFeed(null);
//        }
//
//        sourceCodeUnitViews.clear();
//        srcCodeLayer_Container.removeAllViews();
////        titledSrcCodeBoxes.clear();
//
//        layerContent = this.srcCodeLayer_Printer.getContent();
//
//        if (layerContent != null) {
//            for (String sourceCodeUnitKey : layerContent.getUnitKeys()) {
//                unitFeed = layerContent.getUnitFeed(sourceCodeUnitKey);
//
//                srcCodeUnitView = new SourceCodeUnitView(getContext());
//                srcCodeUnitView.setFeed(unitFeed);
//
//                srcCodeUnitView.setSpanFactory(FormattingKey.Highlighting_A, previewHighlightFormatting);
//                srcCodeUnitView.setSpanFactory(FormattingKey.Highlighting_B, executionHighlightFormatting);
//
//                srcCodeUnitView.setSpanFactory(FormattingKey.Line_Count, lineCountFormatting);
//
//                srcCodeUnitView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT));
//
//                titleViewBox = (LinearLayout) getLayoutInflater().inflate(R.layout.titled_source_code_box,
//                        srcCodeLayer_Container, false);
//
//                ((TextView) titleViewBox.findViewById(R.id.titleTextView)).setText(sourceCodeUnitKey);
//                innerContainer = titleViewBox.findViewById(R.id.container);
//                innerContainer.addView(srcCodeUnitView);
//
//                ((LinearLayout.LayoutParams) titleViewBox.getLayoutParams()).setMargins(6, 6, 6, 6);
//
//                titledSrcCodeBoxes.put(sourceCodeUnitKey, titleViewBox);
//                sourceCodeUnitViews.put(sourceCodeUnitKey, srcCodeUnitView);
//                srcCodeLayer_Container.addView(titleViewBox);
//            }
//
//            for (String sourceCodeUnitKey : layerContent.getUnitKeys()) {
//                if (!layerContent.getDisplayingUnits().contains(sourceCodeUnitKey)) {
//                    hideUnit(sourceCodeUnitKey);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.fragment_source_code, container, false);
//        initUI();
//        return rootView;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    public void initUI() {
//        this.srcCodeLayer_Container = rootView.findViewById(R.id.container);
//        rebuildSourceCodeViews();
//    }
//
//    public void setFeed(Feed layerFeed) {
//        this.srcCodeLayer_Printer.setFeed(layerFeed);
//    }
//}