package UI_Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.ckyblue.adtwisei4.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Utility.Colors.Components;
import Utility.Diagram.Units.Chain.Unbranching.Content;
import Utility.Diagram.Units.Chain.Unbranching.Feed;
import Utility.Diagram.Units.Chain.Unbranching.Printer;
import Utility.Port;
import Utility.Themes.Cascades;
import Utility.Themes.Defaults;

/*TODO Add a configuration for vertical orientation (Top->Down or vice-versa)*/

/*TODO Handling null keys in adapters*/
/*TODO Test using the mCanvas approach to drawing without needing one master onDraw root*/

public class SingleChainView extends View {
    private String TAG = getClass().getName();

    private boolean chainContentValid = false;
    private Utility.Colors.ColorAdapter.Content mDefaultColorAdapter = new Utility.Colors.ColorAdapter.Content() {
        @Override
        public Components fetchComponents(Port port, String key, String content, int position) {
            if (port == Port.header) {
                return Defaults.headerChrome.fetchComponents(content, position);
            }

            if (key == null || !(key.equals(Utility.Data.Nodes.Unit.Content.Column.Index.toString()) ||
                    key.contains(Utility.Data.Nodes.Unit.Content.Column.Pointer.toString())
            )) {
                return Cascades.IndexStreamers.MonoChromeGradient.getChrome().fetchComponents(content, position);
            }

            return Cascades.ContentStreamers.AllInPlains.getChrome().fetchComponents(content, position);
        }
    };

    String mItemColKey;
    String mPointerColKey = Utility.Data.Nodes.Unit.Content.Column.Pointer.toString();

    final HashMap<String, Integer> mPointerKeys = new HashMap<>();

    private int header;
    private final ArrayList<String> nodeItems = new ArrayList<>();
    private final ArrayList<Integer> nodePointers = new ArrayList<>();

    private final Utility.Colors.ColorAdapter.Printer colorAdapterPrinter = new Utility.Colors.ColorAdapter.Printer() {
        @Override
        public void notifyOfFeedRebuild() {
            invalidate();
        }

        @Override
        public void notifyOfRefreshIntent() {

        }
    };

    public Feed getFeed() {
        return mChainPrinter.getFeed();
    }

    private Printer mChainPrinter = new Printer() {
        @Override
        public void notifyOfDiagramModification() {
            notifyOfFeedRebuild();
        }

        @Override
        public void notifyOfFeedRebuild() {
            Logger.log(TAG, "notifyOfFeedRebuild() called");

            Content chainContent = getContent();

            nodeItems.clear();
            nodePointers.clear();
            mPointerKeys.clear();

            try {
                mPointerColKey = chainContent.getPointerColKey();
                mItemColKey = chainContent.getItemColKey();

                Utility.Data.Nodes.Unit.Content nodes = chainContent.getNodesStackPrinter().getUnit();
                Utility.Data.Variables.Unit.Content pointers = chainContent.getPPointersStackPrinter().getUnit();

                header = pointers.getInt(chainContent.getRootPointerKey());
                int currentPointer = header;

                while (currentPointer != -1) {
                    nodeItems.add(nodes.getStrEqv(mItemColKey, currentPointer));

                    currentPointer = nodes.getInt(mPointerColKey, currentPointer);
                    nodePointers.add(currentPointer);
                }

                List<String> allVarKeys = Arrays.asList(pointers.getVariableNames());

                for (String pointerKey : chainContent.getMiscPointerKeys()) {
                    if (allVarKeys.contains(pointerKey)) {
                        mPointerKeys.put(pointerKey, pointers.getInt(pointerKey));
                    }
                }

                setMinimumWidth((int) (minContentWidth + measuredHorizontalPadding));
                setMinimumHeight((int) ((nodeHeight + verticalPadding) * nodeItems.size() +
                        verticalPadding));


                chainContentValid = true;

            } catch (Exception e) {
                chainContentValid = false;
            }

            invalidate();
        }

        @Override
        public void notifyOfRefreshIntent() {
        }

    };

    float arrowHeadHeight, arrowHeadWidth;

    float measuredHorizontalPadding;

    float nodeHeight, keyHeight, keyWidth, itemColWidth, pointerColWidth;
    float verticalPadding, keyToNodePadding, textSize, strokeWidth;
    float nodeWidth, minContentWidth;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint mTextPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    private Paint mArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    int keyBoxBg, keyBoxTextColor;

    private StaticLayout mStaticLayout;

    private Path mPath = new Path();

    int strokeColor = Color.DKGRAY;

    private void init(AttributeSet attrs) {
//        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        setWillNotDraw(false);

        measuredHorizontalPadding = dipToPx(15);

        arrowHeadHeight = dipToPx(20);
        arrowHeadWidth = dipToPx(40);

        nodeHeight = dipToPx(40);
        itemColWidth = dipToPx(100);
        pointerColWidth = nodeHeight;

        verticalPadding = dipToPx(30);

        keyToNodePadding = dipToPx(40);
        keyHeight = nodeHeight + verticalPadding - 10;
        keyWidth = dipToPx(100);

        textSize = dipToPx(14);
        strokeWidth = dipToPx(1);

        nodeWidth = (itemColWidth + pointerColWidth * 2);
        minContentWidth = keyToNodePadding + nodeWidth + keyWidth;

        Components components = mDefaultColorAdapter.fetchComponents(Port.header, "Key", null, 0);
        keyBoxTextColor = Color.parseColor(components.getText_color());
        keyBoxBg = Color.parseColor(components.getBg_color());

        mPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(strokeColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setStrokeJoin(Paint.Join.MITER);

        mArrowPaint.setStrokeWidth(dipToPx(3));
        mArrowPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeJoin(Paint.Join.MITER);
        mStrokePaint.setColor(strokeColor);
        mStrokePaint.setStrokeWidth(strokeWidth);
    }

    public void setFeed(Feed feed) {
        this.mChainPrinter.setFeed(feed);
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

        renderText(canvas, (left + right) / 2, (top + bottom) / 2, width, textColor, textContent);
    }

    private void renderText(Canvas canvas, float x, float y, float widthConstraint, int textColor, String textContent) {
        if (textContent == null) {
            return;
        }

        mTextPaint.setColor(textColor);

        StaticLayout.Builder builder = StaticLayout.Builder.obtain(textContent, 0, textContent.length(),
                mTextPaint, (int) widthConstraint)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL);

        mStaticLayout = builder.build();

        canvas.save();

        canvas.translate(x, y - mStaticLayout.getHeight() / 2.0f);
        mStaticLayout.draw(canvas);

        canvas.restore();
    }

    public void buildChain(Canvas canvas) {
        Logger.log(TAG, "buildChain()");

        if (!chainContentValid) {
            return;
        }
        Logger.log(TAG, "buildChain() w/ chainContentValid");

        float cursorY = 0;
        float cursorX = 0;
        int screenWidth = getWidth();

        float horizontalExtPadding = (screenWidth - minContentWidth) / 2.0f;
        float nodeLeft = horizontalExtPadding + keyWidth + keyToNodePadding;

        int index;

        // Drawing nodes
        cursorY = verticalPadding;
        cursorX = nodeLeft;

        index = header;

        for (int i = 0; i < nodeItems.size(); i++) {
            renderNode(canvas, cursorX, cursorY, nodeHeight, itemColWidth, pointerColWidth,
                    index, nodeItems.get(i), nodePointers.get(i));

            index = nodePointers.get(i);
            cursorY += nodeHeight + verticalPadding;
        }

        cursorY = verticalPadding;
        cursorX = nodeLeft;

        float startX, endX, startY, endY;

        for (int i = 0; i < nodeItems.size() - 1; i++) {
            startX = cursorX + nodeWidth - pointerColWidth / 2;
            startY = cursorY + nodeHeight;

            endX = cursorX + pointerColWidth / 2;
            endY = startY + verticalPadding;

            renderArrow(canvas, startX, startY, endX, endY, arrowHeadWidth, arrowHeadHeight);
            cursorY += nodeHeight + verticalPadding;
        }

        // Drawing keys
        cursorY = verticalPadding;
        cursorX = horizontalExtPadding;

        index = header;
        float keyTop;
        ArrayList<String> pointerAttributes = new ArrayList<>();

        for (int i = 0; i < nodeItems.size(); i++) {
            pointerAttributes.clear();

            keyTop = cursorY + nodeHeight / 2 - keyHeight / 2;

            for (String key : mPointerKeys.keySet()) {
                if (mPointerKeys.get(key) == index) {
                    pointerAttributes.add(key);
                }
            }

            if (!pointerAttributes.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(pointerAttributes.get(0));

                for (int j = 1; j < pointerAttributes.size(); j++) {
                    stringBuilder.append(", ");
                    stringBuilder.append(pointerAttributes.get(j));
                }

                renderBoxedText(canvas, cursorX, keyTop, keyWidth, keyHeight, keyBoxBg, keyBoxTextColor,
                        stringBuilder.toString());

                renderArrow(canvas, cursorX + keyWidth, keyTop + keyHeight / 2,
                        cursorX + keyWidth + keyToNodePadding, keyTop + keyHeight / 2,
                        arrowHeadWidth, arrowHeadHeight);
            }

            index = nodePointers.get(i);
            cursorY += nodeHeight + verticalPadding;
        }

    }


    private void renderNode(Canvas canvas, float left, float top,
                            float height, float itemColWidth, float indexColWidth,
                            int indexVal, String itemVal, int pointerVal) {
        int indexBgColor, indexTextColor;
        int itemBgColor, itemTextColor;
        int pointerBgColor, pointerTextColor;

        Utility.Colors.ColorAdapter.Content colorAdapter = mDefaultColorAdapter;

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

        renderBoxedText(canvas, left, top, indexColWidth, height, indexBgColor, indexTextColor, String.valueOf(indexVal));
        renderBoxedText(canvas, left + indexColWidth, top, itemColWidth, height, itemBgColor, itemTextColor, itemVal);
        renderBoxedText(canvas, left + indexColWidth + itemColWidth, top, indexColWidth, height,
                pointerBgColor, pointerTextColor, String.valueOf(pointerVal));
    }

    private void renderArrow(Canvas canvas, float x1, float y1, float x2, float y2,
                             float arrowHeadWidth, float arrowHeadHeight) {
        mPath.reset();
        mPath.moveTo(x1, y1);
        mPath.lineTo(x2, y2);

        canvas.drawPath(mPath, mArrowPaint);

        mPath.reset();

        float deltaX = x2 - x1;
        float deltaY = y2 - y1;
        float frac = (float) 0.05;

        float point_x_1 = x1 + (float) ((1 - frac) * deltaX + frac * deltaY);
        float point_y_1 = y1 + (float) ((1 - frac) * deltaY - frac * deltaX);
        float point_x_2 = x2;
        float point_y_2 = y2;
        float point_x_3 = x1 + (float) ((1 - frac) * deltaX - frac * deltaY);
        float point_y_3 = y1 + (float) ((1 - frac) * deltaY + frac * deltaX);

        mPath.moveTo(point_x_1, point_y_1);
        mPath.lineTo(point_x_2, point_y_2);
        mPath.lineTo(point_x_3, point_y_3);
        mPath.close();

        canvas.drawPath(mPath, mArrowPaint);
    }

    private void renderCircle(Canvas canvas, float centerX, float centerY, float rad, int fillColor,
                              int textColor, String textContent) {
        mFillPaint.setColor(fillColor);
        canvas.drawCircle(centerX, centerY, rad, mFillPaint);
        canvas.drawCircle(centerX, centerY, rad, mStrokePaint);

        renderText(canvas, centerX, centerY, rad * 2, textColor, textContent);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Logger.log(TAG, "onMeasure() called");

        int desiredWidth = 0;
        int desiredHeight = 0;

        if (chainContentValid) {
            desiredWidth = (int) (minContentWidth + measuredHorizontalPadding);
            desiredHeight = (int) ((nodeHeight + verticalPadding) * nodeItems.size() +
                    verticalPadding);

        }

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
//        super.onDraw(canvas);
        Logger.log(TAG, "onDraw()");
        buildChain(canvas);
    }
}
