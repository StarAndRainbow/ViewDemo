package com.gzgamut.viewdemo;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


//了解View 的left top right bottom(相对父布局的位置，移动后不变) 和x,y, TranslationX和TranslationY（移动的位置）      x = left + TranslationX;

//了解滑动事件的MotionEvent(滑动事件，保存了getX,getY，getRawX，getRawY等信息),TouchSlop（怎样才算滑动，该值就表示的是滑动的标准）  , RowX,RowY（当前View相对于屏幕）

//了解velocityTraker(可以监控手势的滑动速度水平和垂直速度，速度可以为负数)

//GestureDetecter 手势检测，单击，滑动，长按，双击等行为,

//Scroller

//scrollTo / scrollBy(改变的是View内容的变化，而不是View移动)

// android动画TranslationX,TranslationY.    View动画和属性动画

//改变布局参数进行滑动LayoutParams

//弹性滑动Scroller源码分析,使用动画对一个数字改变，动态计算要改变的距离，延时策略（handler或者sleep和view.postDelayed进行延时）

//事件分发(理论+源码)MotionEvent分析，dispatchTouchEvent(返回结果受当前View onTouchEvent和下级View.dispatchTouchEvent影响)，InterceptchTouchEvent;(在dispatchTouchEvent中调用判断是否需要拦截事件)

public class MainActivity extends AppCompatActivity {
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (TextView) findViewById(R.id.view);

        //只能返回false
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return false;
            }
        });
    }
}
