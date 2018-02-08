package com.gzgamut.myviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * linxin
 * 2018/2/7
 * guanbp@gzgamut.com
 */
public class MyViewPager  extends ViewGroup{

    private static final String TAG = "MyViewPager";

    String [] titles;

     private Context mContext;
     private GestureDetector gestureDetector;

    public MyViewPager(Context context) {
        this(context,null);
    }


    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        initData();
        initView();
    }



    /**
     *添加View到ViewGroup中
     */
    private void  initView(){
         for(int i = 0 ; i<titles.length;i++){
             TextView tv = new TextView(mContext);
             tv.setText(titles[i]);

             this.addView(tv);
         }
    }

    /**
     * 初始化 数据gestureDetector
     */
    private void initData(){
         titles    = new String[]{"第一页","第二页","第三页","第四页"};
         gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
             @Override
             public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                 // e1按下时的事件
                 // e2每次触发onScroll函数得到的事件
                 //距离是e1减去当前e2得到的结果

                 //distanceX手指滑动的距离
                 //ScrollBy,相对于当前位置坐滑动，并且滑动的是内容，不是View
                 LogUtil.i("distanceX:"+distanceX+",distanceY----"+distanceY);
                 scrollBy((int)distanceX,0);
                 return super.onScroll(e1, e2, distanceX, distanceY);
             }
         });
    }


    float startX = 0;
    float endX = 0;
    int scrollX = 0;
    int screentWidth = 0;
    int pos = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //onTouchEvent委托给手势识别器，并且返回true，让这个控件消耗这个事件,或者直接设置手势监听器
                gestureDetector.onTouchEvent(event);
                startX = event.getX();

                scrollX = getScrollX();                       //getScrollX是ScrollTo方法,记录滑动的绝对位置    http://blog.csdn.net/bigconvience/article/details/26697645
                //抬起手的时候，判断当前滑动的位置，是在某页的左边还是右边
                LogUtil.i("输出控件的宽度和高度getWidth:"+getWidth()+"getHeight:"+getHeight());
                WindowManager wm  =  (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics dm = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(dm);
                 screentWidth = dm.widthPixels;
                //当前滑动页
                pos =( scrollX / screentWidth  ) ;
                LogUtil.i("屏幕的宽度:"+screentWidth+"-----当前滑动页面:"+pos );

                break;
            case MotionEvent.ACTION_MOVE:
                gestureDetector.onTouchEvent(event);
                break;
            case MotionEvent.ACTION_UP:

                endX = event.getX();

                LogUtil.i("getScrollX:"+getScrollX()+"getScrollY:"+getScrollY()+"---页数："+pos);
                Log.e(TAG, "onTouchEvent: " + (endX - startX));
                if( (endX -startX)   > screentWidth/2 ){
                    //往前走一页
                    pos = pos-1;
                    if(pos <0){
                        pos = 0;
                    }
                }else if((endX -startX)   < screentWidth/2*-1 ){
                    //往后走一页
                    pos = pos+1;
                    if(pos>3){
                        pos =3;
                    }
                }else{
                     //滑动不够一半，或者左右滑动相等，回到当前页
                    pos =pos;
                }

                Log.e(TAG, "onTouchEvent: "+pos );
                scrollTo(pos * screentWidth,0);
                break;
        }

        return true;
    }

    /**
     *  对View进行绘制，ViewGroup一般不用该方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    int width = 0;
    int height = 0;
    /**
     * 测量,获取
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize  =MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){
             //测量子View的宽度
        }else{
             width = DensityUtils.dip2px(mContext,300);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize ;
        }else if(heightMode == MeasureSpec.AT_MOST){
            //测量子View的高度
        }else{
            height = DensityUtils.dip2px(mContext,300);
        }
        setMeasuredDimension(width,height);
    }



    /**
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     * 对子View进行摆放
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
          int chindCount = this.getChildCount();
          for(int i = 0 ;i<chindCount;i++){
                //第一页的布局 l = 0 ,t = 0 ,r =width,b = height
                //第二页的布局 l = width ,t = 0 ,r =width*2,b = height
                //第三页的布局 l = width*2 ,t = 0 ,r =width*3,b = height
                //第四页的布局 l = width*3 ,t = 0 ,r =width*4,b = height
                TextView tv = (TextView) this.getChildAt(i);
                tv.layout(width*i,0,width*(i+1),height);

              //设置TextView的大小和背景色
              ViewGroup.LayoutParams lp = getLayoutParams();
              lp.width =width;
              lp.height = height;
              tv.setLayoutParams(lp);
              tv.setBackgroundColor(Color.parseColor("#ffffff"));
          }
    }


}

