package cn.rainbow.westore.imagespan;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        TextView tv = (TextView) findViewById(R.id.textView);
        SpannableStringBuilder ssb = new SpannableStringBuilder(tv.getText());
        ssb.setSpan(new MyReplacementSpan(),2,5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(ssb);
    }

    class MyReplacementSpan extends ReplacementSpan{

        int bottom;

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
            Log.d("getSize: ",text.toString());
            return (int) paint.measureText(text, start, end);
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
            Log.d("draw: ",text.toString());
        }
    }
}
