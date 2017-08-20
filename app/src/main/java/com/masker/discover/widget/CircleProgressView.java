package com.masker.discover.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.masker.discover.R;

/**
 * Author: masker
 * Date: 2017/8/14
 * Description:
 */

public class CircleProgressView extends View{

    public static final float DEFAULT_PROGRESS_WIDTH = 10f;
    public static final float DEFAULT_ICON_WIDTH = 10f;
    public static final float MAX_ANGLE = 360f;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private Paint mTextPaint;
    private int mProgressColor;
    private float mProgressWidth;
    private int mProgress;

    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mProgressColor = ta.getColor(R.styleable.CircleProgressView_progress_color, Color.BLACK);
        mProgressWidth = ta.getDimension(R.styleable.CircleProgressView_progress_width,DEFAULT_PROGRESS_WIDTH);
        ta.recycle();
        init();
    }


    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mTextPaint = new Paint();
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        int r = (int) (Math.min(mWidth,mHeight)/2-mProgressWidth)-5;
        //draw progress
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);
        canvas.drawCircle(0,0,r,mPaint);
        mPaint.setColor(mProgressColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        float angle = MAX_ANGLE*mProgress/100;
        RectF rectF = new RectF(-r,-r,r,r);
        canvas.drawArc(rectF,-90f,angle,false,mPaint);

        //draw text
        String text = String.valueOf(mProgress);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(sp2px(getContext(),12));
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(text,0,text.length(),textBounds);
        int w = textBounds.width();
        int h = textBounds.height();
        canvas.drawText(text,-w/2,h/2,mTextPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setProgress(int progress){
        if(progress < 0){
            mProgress = 0;
        }
        else if(progress > 100){
            mProgress = 100;
        }
        else{
            mProgress = progress;
        }
        invalidate();
    }

    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
