package com.aofan.cardismantling.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

import com.aofan.cardismantling.R;


/**
 * Created by Administrator on 2016/2/19.
 */
public class CustomProgressView extends CustomBaseView {


    int mFirstColor;
    int mSecondColor;

    int mCircleWidth;

    int mDotCount;
    int mSplitSize;

    int mCurrCount;

    Paint mPaint;

    public static final int DEFAULT_INNER_IMG_MARGIN_VALUE = 10;


    public CustomProgressView(Context context) {
        this(context, null);
    }

    public CustomProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomProgressView);
        for (int i = 0;i<typedArray.getIndexCount();i++)
        {
            int attr = typedArray.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomProgressView_fitstColor:
                    mFirstColor = typedArray.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomProgressView_secondColor:
                    mSecondColor = typedArray.getColor(attr,Color.GREEN);
                    break;
                case R.styleable.CustomProgressView_circleWidth:
                    mCircleWidth = (int) typedArray.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressView_dotCount:
                    mDotCount = typedArray.getInt(attr, 20);
                    break;
                case R.styleable.CustomProgressView_splitSize:
                    mSplitSize = typedArray.getInt(attr, 20);
                    break;

            }
        }
        typedArray.recycle();

    }


    boolean isNext = false;
    @Override
    public void init() {
        mPaint = new Paint();
        mCurrCount = 0;

        // 绘图线程
        new Thread()
        {
            public void run()
            {
                while (true)
                {
                    mCurrCount++;

                    /*if (mCurrCount > mDotCount)
                    {
                        mCurrCount = 0;
                        *//*if (!isNext)
                            isNext = true;
                        else
                            isNext = false;*//*
                    }*/
                    postInvalidate();
                    try
                    {
                        Thread.sleep(50);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth()/2;
        int radius = center - mCircleWidth / 2;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);
        /*mPaint.setStrokeCap(Paint.Cap.BUTT);*/

        drawDot(canvas, center, radius);


        /*if (null!=mCenterImg)
        {
            //内部圆
            int innerRadius = radius - mCircleWidth/2 ;
            //内切正方形
            Rect rectDst = new Rect();
            //获取内切正方形的大小
            rectDst.left  = (int) (innerRadius - Math.sqrt(2)*1.0f/2*innerRadius+mCircleWidth)+DEFAULT_INNER_IMG_MARGIN_VALUE;
            rectDst.top = (int) (innerRadius - Math.sqrt(2)*1.0f/2*innerRadius+mCircleWidth)+DEFAULT_INNER_IMG_MARGIN_VALUE;
            rectDst.right  = (int) (rectDst.left +  Math.sqrt(2)*1.0f*innerRadius)-DEFAULT_INNER_IMG_MARGIN_VALUE;
            rectDst.bottom = (int) (rectDst.left +  Math.sqrt(2)*1.0f*innerRadius)-DEFAULT_INNER_IMG_MARGIN_VALUE;

            *//**
             * 如果图片比较小，那么根据图片的尺寸放置到正中心
             *//*
            if (mCenterImg.getWidth() < Math.sqrt(2) * innerRadius)
            {
                rectDst.left = (int) (rectDst.left + Math.sqrt(2) * innerRadius * 1.0f / 2 - mCenterImg.getWidth() * 1.0f / 2);
                rectDst.top = (int) (rectDst.top + Math.sqrt(2) * innerRadius * 1.0f / 2 - mCenterImg.getHeight() * 1.0f / 2);
                rectDst.right = (int) (rectDst.left + mCenterImg.getWidth());
                rectDst.bottom = (int) (rectDst.top + mCenterImg.getHeight());

            }

            // 绘图
            canvas.drawBitmap(mCenterImg, null, rectDst, mPaint);
        }*/


    }

    private void drawDot(Canvas canvas,int center,int radius)
    {
        RectF oval = new RectF(center - radius,center - radius,center+radius,center+radius);



        mPaint.setColor(mFirstColor);

        float itemSize = (360 * 1.0f - mDotCount * mSplitSize) / mDotCount;
        for (int i = 0;i < mDotCount;i++)
        {
            canvas.drawArc(oval,i* (itemSize + mSplitSize),itemSize,false,mPaint);
        }

        mPaint.setColor(mSecondColor);


        canvas.drawArc(oval, (mCurrCount - 1) * (itemSize + mSplitSize), itemSize*3, false, mPaint);
        /*canvas.drawArc(oval,(mCurrCount) * (itemSize + mSplitSize),itemSize,false,mPaint);
        canvas.drawArc(oval,(mCurrCount+1) * (itemSize + mSplitSize),itemSize,false,mPaint);*/
        /*for (int i = 0;i < mCurrCount ; i++)
        {
            canvas.drawArc(oval,i* (itemSize + mSplitSize),itemSize,false,mPaint);
        }*/
    }


    int yDown,yUp;


    private void upVolume()
    {
        mCurrCount++;
        postInvalidate();

    }


    private void downVolume()
    {
        mCurrCount--;
        postInvalidate();

    }

    public static final int DEFAULT_CHANGE_VALUE= 50;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                yDown = (int) event.getY();

                break;
            case MotionEvent.ACTION_UP:
                yUp = (int) event.getY();
                if (yUp > yDown)
                {
                    if ((yUp-yDown)>DEFAULT_CHANGE_VALUE)
                    {
                        upVolume();
                    }
                }

                if (yUp < yDown)
                {
                    if ((yDown-yUp)>DEFAULT_CHANGE_VALUE)
                    {
                        downVolume();
                    }
                }
                break;
        }

        return true;
    }
}
