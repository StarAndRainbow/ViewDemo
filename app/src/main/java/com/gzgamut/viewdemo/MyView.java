package com.gzgamut.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 2018/2/2
 * guanbp@gzgamut.com
 */
public class MyView  extends Button{
    private Context mContext;
    private static final String TAG = "MyView";
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float  moveX;
    float moveY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
      //  Log.e(TAG, "onTouchEvent: "+ViewConfiguration.get(mContext).getScaledTouchSlop() );
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "开始onTouchEvent: getX:"+getX()+"----getTranslationX:"+getTranslationX() +"-----getLeft"+getLeft()
                        +"-----getTop:"+getTop()+"---getRight"+getRight()+"getBottom"+getBottom() + "-------rawX:"+event.getRawX()+"--------rawY:"+event.getRawY());
                Log.e(TAG, "onTouchEvent: getY:"+getY()+"----getTranslationY:"+getTranslationY());
                Log.e(TAG, "onTouchEvent: getX:"+event.getX() +"getY:"+event.getY() );
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(getX() + (event.getX() - moveX));
                setTranslationY(getY() + (event.getY() - moveY));
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "结束onTouchEvent: getX:"+getX()+"----getTranslationX:"+getTranslationX() +"-----getLeft"+getLeft()
                        +"-----getTop:"+getTop()+"---getRight"+getRight()+"getBottom"+getBottom() + "-------rawX:"+event.getRawX()+"--------rawY:"+event.getRawY() );
                Log.e(TAG, "onTouchEvent: getY:"+getY()+"----getTranslationY:"+getTranslationY());
                Log.e(TAG, "onTouchEvent: getX:"+event.getX() +"getY:"+event.getY() );
                break;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
