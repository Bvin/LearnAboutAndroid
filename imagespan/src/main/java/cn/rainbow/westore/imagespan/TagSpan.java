package cn.rainbow.westore.imagespan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;

import java.util.List;

/**
 * Created by bvin on 2016/10/24.
 */

public class TagSpan extends ReplacementSpan {

    private int mSpanWidth;
    private String mLeftText;
    private String mRightText;
    private int mMiddleIndex;
    private int mLeftWidth;
    private int mRoundRadius;
    private Paint mLeftTagPaint;
    private float mRifhtStartX;

    public TagSpan(String rightText, String leftText) {
        mRightText = rightText;
        mLeftText = leftText;
        mRoundRadius = 10;
        mLeftTagPaint = new Paint();
        mLeftTagPaint.setColor(Color.BLUE);
    }

    /**
     * Returns the width of the span. Extending classes can set the height of the span by updating
     * attributes of {@link Paint.FontMetricsInt}. If the span covers the whole
     * text, and the height is not set,
     * {@link #draw(Canvas, CharSequence, int, int, float, int, int, int, Paint)} will not be
     * called for the span.
     *
     * @param paint Paint instance.
     * @param text  Current text.
     * @param start Start character index for span.
     * @param end   End character index for span.
     * @param fm    Font metrics, can be null.
     * @return Width of the span.
     */
    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        mSpanWidth = (int) paint.measureText(text, start, end);
        //mMiddleIndex = TextUtils.indexOf(text,mRightText);
        mLeftWidth = (int) paint.measureText(mLeftText, 0, mLeftText.length());
        return mSpanWidth;
    }

    /**
     * Draws the span into the canvas.
     *
     * @param canvas Canvas into which the span should be rendered.
     * @param text   Current text.
     * @param start  Start character index for span.
     * @param end    End character index for span.
     * @param x      Edge of the replacement closest to the leading margin.
     * @param top    Top of the line.
     * @param y      Baseline.
     * @param bottom Bottom of the line.
     * @param paint  Paint instance.
     */
    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {


        RectF rect = new RectF(x, top, x + mRoundRadius*2 , bottom);//左边圆角部分
        canvas.drawRoundRect(rect, mRoundRadius, mRoundRadius, mLeftTagPaint);

        canvas.drawRect(x + mRoundRadius, top, x + mLeftWidth, bottom, mLeftTagPaint);

        mLeftTagPaint.setColor(Color.RED);

        rect = new RectF(x + (mSpanWidth-mRoundRadius*2), top, x+mSpanWidth, bottom);
        canvas.drawRoundRect(rect, mRoundRadius, mRoundRadius, mLeftTagPaint);

        mLeftTagPaint.setColor(Color.GREEN);
        //canvas.drawRect(x + mLeftWidth, top, x + (mSpanWidth-mRoundRadius), bottom, mLeftTagPaint);

        canvas.drawText(text, start, end, x, y, paint);
    }
}
