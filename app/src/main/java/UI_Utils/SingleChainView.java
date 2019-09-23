package UI_Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utility.Bases.SuperContent;
import Utility.Bases.SuperFeed;
import Utility.Bases.SuperPrinter;
import Utility.Colors.Components;
import Utility.Data.Alteration;
import Utility.Port;
import Utility.Themes.Cascades;

class Content extends SuperContent<Feed> {
    private boolean isInitializingChain = false;

    Utility.Data.Nodes.Stack.Printer mNodesStackPrinter = new Utility.Data.Nodes.Stack.Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, String elementKey, int index) {
            chainModified();
        }

        @Override
        public void notifyOfFeedRebuild() {
            chainModified();
        }

        @Override
        public void notifyOfRefreshIntent() {
        }
    };
    Utility.Data.Variables.Stack.Printer mPPointersStackPrinter = new Utility.Data.Variables.Stack.Printer() {
        @Override
        public void notifyOfContentAlteration(Alteration alteration, String variableName) {
            if (mPointerKeys.contains(variableName)) {
                chainModified();
            }
        }

        @Override
        public void notifyOfFeedRebuild() {
            chainModified();
        }

        @Override
        public void notifyOfRefreshIntent() {
        }
    };

    String mItemColKey;
    String mPointerColKey;
    ArrayList<String> mPointerKeys;

    int mheader;

    public void readChain(Utility.Data.Nodes.Stack.Feed nodesStackFeed,
                          String itemColKey, String pointerColKey,
                          Utility.Data.Variables.Stack.Feed pointerStackFeed,
                          int header, ArrayList<String> pointerKeys) {
        // callBacks notifying of modifications are swallowed until flag is negated
        isInitializingChain = true;

        mNodesStackPrinter.setFeed(nodesStackFeed);
        mPPointersStackPrinter.setFeed(pointerStackFeed);

        mItemColKey = itemColKey;
        mPointerColKey = pointerColKey;
        mheader = header;

        mPointerKeys = pointerKeys;

        isInitializingChain = false;

        if (getFeed() != null) {
            getFeed().feedRebuilt();
        }
        this.unitDelta();
    }

    private void chainModified() {
        if (!isInitializingChain) {
            if (getFeed() != null) {
                getFeed().chainModified();
            }
            this.unitDelta();
        }
    }
}

class Feed extends SuperFeed<Content, Printer> {
    void chainModified() {
        for (Printer printer : getPrinters()) {
            printer.notifyOfContentModification();
        }
    }
}

/*TODO Build a renderText method that handles centering text*/
/*TODO Test how \n is rendered on renderText*/
/*TODO Add a configuration for vertical orientation (Top->Down or vice-versa)*/
/*TODO Replace color args with chrome arg at drawNode*/
/*TODO Add mechanism for keys*/
/*TODO Handling null keys in adapter*/

abstract class Printer extends SuperPrinter<Content, Feed> {
    abstract void notifyOfContentModification();
}

public class SingleChainView extends View {
    private boolean chainContentValid = false;
    private Utility.Colors.ColorAdapter.Content mDefaultColorAdaper = new Utility.Colors.ColorAdapter.Content() {
        @Override
        public Components fetchComponents(Port port, String key, String content, int position) {
            if (key == null || !(key.equals(Utility.Data.Nodes.Unit.Content.Column.Index.toString()) ||
                    key.contains(Utility.Data.Nodes.Unit.Content.Column.Pointer.toString())
            )) {
                return Cascades.IndexStreamers.Stripes.getChrome().fetchComponents(content, position);
            }

            return Cascades.ContentStreamers.AllInPlains.getChrome().fetchComponents(content, position);
        }
    };

    String mItemColKey;
    String mPointerColKey = Utility.Data.Nodes.Unit.Content.Column.Pointer.toString();

    ArrayList<String> mPointerKeys;

    private final ArrayList<String> nodeItems = new ArrayList<>();
    private final ArrayList<Integer> nodePointers = new ArrayList<>();

    private final Utility.Colors.ColorAdapter.Printer colorAdapterPrinter = new Utility.Colors.ColorAdapter.Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            postInvalidate();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };

    private Printer mChainPrinter = new Printer() {
        @Override
        void notifyOfContentModification() {
            notifyOfFeedRebuild();
        }

        @Override
        public void notifyOfFeedRebuild() {
            chainContentValid = false;
            Content chainContent = getContent();

            nodeItems.clear();
            nodePointers.clear();

            try {
                mPointerColKey = chainContent.mPointerColKey;
                mItemColKey = chainContent.mItemColKey;

                Utility.Data.Nodes.Unit.Content nodes = chainContent.mNodesStackPrinter.getUnit();
                Utility.Data.Variables.Unit.Content pointers = chainContent.mPPointersStackPrinter.getUnit();

                int currentPointer = chainContent.mheader;

                while (currentPointer != -1) {
                    nodeItems.add(nodes.getStrEqv(mItemColKey, currentPointer));

                    currentPointer = nodes.getInt(mPointerColKey, currentPointer);
                    nodePointers.add(currentPointer);
                }

                ArrayList<String> pointerKeys = new ArrayList<>();
                List<String> allVarKeys = Arrays.asList(pointers.getVariableNames());

                for (String pointerKey : chainContent.mPointerKeys) {
                    if (allVarKeys.contains(pointerKey)) {
                        pointerKeys.add(pointerKey);
                    }
                }

                mPointerKeys = pointerKeys;
                chainContentValid = true;

            } catch (Exception e) {
                chainContentValid = false;

            }

            postInvalidate();
        }

        @Override
        public void notifyOfRefreshIntent() {
        }

    };

    float arrowHeadSize;

    float nodeHeight, keyHeight, keyWidth, itemColWidth, pointerColWidth;
    float verticalPadding, keyToNodePadding, textSize, strokeWidth;
    float nodeWidth, minContentWidth;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Path path = new Path();

    int strokeColor = Color.DKGRAY;

    private void init(AttributeSet attrs) {
        arrowHeadSize = dipToPx(20);

        nodeHeight = dipToPx(40);
        itemColWidth = dipToPx(120);
        pointerColWidth = nodeHeight;

        verticalPadding = dipToPx(30);
        keyToNodePadding = dipToPx(50);

        keyHeight = dipToPx(nodeHeight + verticalPadding * 2 - 10);
        keyWidth = dipToPx(200);

        textSize = dipToPx(16);
        strokeWidth = dipToPx(1);

        nodeWidth = (itemColWidth + pointerColWidth * 2);
        minContentWidth = keyToNodePadding + nodeWidth + keyWidth;

        mPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(textSize);

        mArrowPaint.setStrokeWidth(dipToPx(3));
        mArrowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setStrokeJoin(Paint.Join.MITER);
        mTextPaint.setColor(Color.BLACK);

        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeJoin(Paint.Join.MITER);
        mStrokePaint.setColor(Color.BLACK);
        mStrokePaint.setStrokeWidth(strokeWidth);

        /*TODO Remove*/
        // For test purposes only
        for (int i = 0; i < 10; i++) {
            nodeItems.add("Ram" + ('A' + i));
            nodePointers.add(i);
        }

        chainContentValid = true;
        postInvalidate();
    }

    public SingleChainView(Context context) {
        super(context);
        init(null);
    }

    public SingleChainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SingleChainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SingleChainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public float dipToPx(float dip) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                getResources().getDisplayMetrics()
        );
    }

    private void renderBoxedText(Canvas canvas, float left, float top, float width, float height,
                                 int fillColor, int textColor, String textContent) {
        mFillPaint.setColor(fillColor);

        float right = left + width;
        float bottom = top + height;

        canvas.drawRect(left, top, right, bottom, mFillPaint);
        canvas.drawRect(left, top, right, bottom, mStrokePaint);

        renderText(canvas, (left + right) / 2, (top + bottom) / 2, textColor, textContent);
    }

    private void renderText(Canvas canvas, float x, float y, int textColor, String textContent) {
        if (textContent == null) {
            return;
        }

        mTextPaint.setColor(textColor);
        float adjustedY = y - ((mTextPaint.descent() + mTextPaint.ascent()) / 2.0f);
        canvas.drawText(textContent, x, adjustedY, mTextPaint);
    }

    public void buildChain(Canvas canvas) {
        if (!chainContentValid) {
            return;
        }

        float cursorY = 0;
        float cursorX = 0;
        int screenWidth = getWidth();

        float horizontalExtPadding = (screenWidth - minContentWidth) / 2.0f;

        // Drawing keys & nodes
        cursorY += verticalPadding;
        cursorX = dipToPx(120);

        float startX, endX, startY, endY;

        for (int i = 0; i < nodeItems.size(); i++) {
            renderNode(canvas, cursorX, cursorY, nodeHeight, itemColWidth, pointerColWidth,
                    i, "String" + i, i + 1);

            startX = cursorX + nodeWidth - pointerColWidth / 2;
            startY = cursorY + nodeHeight;

            endX = cursorX + pointerColWidth / 2;
            endY = startY + verticalPadding;

            if (i < nodeItems.size() - 1) {
                renderArrow(canvas, startX, endY, endX, startY, arrowHeadSize);
            }

            cursorY += nodeHeight + verticalPadding;
        }
    }

    private void renderNode(Canvas canvas, float left, float top,
                            float height, float itemColWidth, float indexColWidth,
                            int indexVal, String itemVal, int pointerVal) {

        int indexBgColor, indexTextColor;
        int itemBgColor, itemTextColor;
        int pointerBgColor, pointerTextColor;

        Utility.Colors.ColorAdapter.Content colorAdapter = mDefaultColorAdaper;

        if (colorAdapterPrinter.getContent() != null) {
            colorAdapter = colorAdapterPrinter.getContent();
        }

        Components indexColorComponents = colorAdapter.fetchComponents(
                Port.body,
                Utility.Data.Nodes.Unit.Content.Column.Index.toString(),
                String.valueOf(indexVal), indexVal);
        indexBgColor = Color.parseColor(indexColorComponents.getBg_color());
        indexTextColor = Color.parseColor(indexColorComponents.getText_color());

        Components itemColorComponents = colorAdapter.fetchComponents(
                Port.body,
                mItemColKey, itemVal, indexVal);
        itemBgColor = Color.parseColor(itemColorComponents.getBg_color());
        itemTextColor = Color.parseColor(itemColorComponents.getText_color());

        Components pointerColorComponents = colorAdapter.fetchComponents(
                Port.body,
                mPointerColKey,
                String.valueOf(pointerVal), indexVal);
        pointerBgColor = Color.parseColor(pointerColorComponents.getBg_color());
        pointerTextColor = Color.parseColor(pointerColorComponents.getText_color());

        renderBoxedText(canvas, left, top, indexColWidth, height, indexBgColor, indexTextColor, "I");
        renderBoxedText(canvas, left + indexColWidth, top, itemColWidth, height, itemBgColor, itemTextColor, "D\nD");
        renderBoxedText(canvas, left + indexColWidth + itemColWidth, top, indexColWidth, height, pointerBgColor, pointerTextColor, "P");
    }

    private void renderArrow(Canvas canvas, float x1, float y1, float x2, float y2,
                             float arrowHeadSize) {
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);

        canvas.drawPath(path, mArrowPaint);

        float delY = y2 - y1;
        float delX = x2 - x1;

        float delMagnitude = (float) Math.pow(Math.pow(delX, 2) + Math.pow(delY, 2), 0.5);

        float unitDelY = delY / delMagnitude;
        float unitDelX = delX / delMagnitude;

        canvas.save();
        canvas.rotate(90, x2, y2);

        path.reset();
        path.moveTo(x2 + unitDelX * arrowHeadSize / 2, y2 + unitDelY * arrowHeadSize / 2);
        path.lineTo(x2 - unitDelX * arrowHeadSize / 2, y2 - unitDelY * arrowHeadSize / 2);
        canvas.drawPath(path, mArrowPaint);

        canvas.restore();

        float tipX = x2 + unitDelX * arrowHeadSize, tipY = y2 + unitDelY * arrowHeadSize;

        path.reset();
        path.moveTo(tipX, tipY);

        canvas.save();
        canvas.rotate(150, tipX, tipY);
        path.lineTo(tipX + unitDelX * arrowHeadSize, tipY + unitDelY * arrowHeadSize);
        canvas.drawPath(path, mArrowPaint);
        canvas.restore();

        path.reset();
        path.moveTo(tipX, tipY);

        canvas.save();
        canvas.rotate(-150, tipX, tipY);
        path.lineTo(tipX + unitDelX * arrowHeadSize, tipY + unitDelY * arrowHeadSize);
        canvas.drawPath(path, mArrowPaint);
        canvas.restore();
    }

    private void renderCircle(Canvas canvas, float centerX, float centerY, float rad, int fillColor,
                              int textColor, String textContent) {
        mFillPaint.setColor(fillColor);
        canvas.drawCircle(centerX, centerY, rad, mFillPaint);
        canvas.drawCircle(centerX, centerY, rad, mStrokePaint);

        renderText(canvas, centerX, centerY, textColor, textContent);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = (int) minContentWidth;
        int desiredHeight = (int) ((nodeHeight + verticalPadding) * nodeItems.size() +
                verticalPadding * 4);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
/*        renderArrow(canvas, 0, 0,
                dipToPx(200), dipToPx(200),
                dipToPx(arrowHeadSize),
                dipToPx(arrowHeadWidth));

        renderNode(canvas,
                0, 0,
                dipToPx(nodeHeight), dipToPx(itemColWidth), dipToPx(pointerColWidth),
                "Item", 2, Color.RED, Color.GREEN
        );*/

        buildChain(canvas);
    }
}
