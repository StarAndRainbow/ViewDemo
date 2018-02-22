package com.gzgamut.myviewpager;

import android.content.Context;



/*
* 单位转换工具
* */
public class DensityUtils {
    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}

//以上这是用公式转换方法进行转换。系统也提供了TypedValue帮助我们转换，代码如下：

/*
protected int dp2px(int dp){
        return  (int) TypedValue.applyDimension(
                      TypedValue.COMPLEX_UNIT_DIP,dp, 
                      getResources().getDisplayMetrics());
    }
    protected int sp2px(int sp){
        return  (int) TypedValue.applyDimension(
                      TypedValue.COMPLEX_UNIT_SP,sp，
                      getResources().getDisplayMetrics());
}*/
