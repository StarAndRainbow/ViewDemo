# ViewDemo
自定义View






######View的位置参数: x = left + TranslationX


-  View的位置参数getLeft(),getTop(),getRight(),getBottom()
   getLeft()是View的左上角横坐标，getTop是左上角的纵坐标。

-  getRight()是右下角的横坐标，getBottom（）是右下角的纵坐标
   getTranslationX(),getTranslationY()
   getTranslationX（）是View移动的横坐标，getTranslationY()是移动的纵坐标

-  motionEvent.getX()-----getX()是表示Widget相对于自身左上角的横坐标,
   motionEvent.getY()-----getY()是表示Widget相对于自身左上角的横坐标
   
-  getX()，getY()是View自带的方法，不是motionEvent中的 motionEvent.getX(),  motionEvent.getY() 

-  motionEvent.getRawX(),motionEvent.getRawY().getRawX()是表示相对于屏幕左上角的x坐标值(注意:这个屏幕左上角是手机屏幕左上角,
   不管activity是否有      titleBar或是否全屏幕),getY(),getRawY()一样的道理


######MotionEvent



- ACTION_DOWN

- ACTION_MOVE
- ACTION_UP
####  TouchSlop系统所能被是识别出认为是滑动的最小距离  ####
####  VelocityTracker手指滑动过程中的速度####
#### Scroller弹性滑动####
#### GestureDetector手势识别####
#### ScrollTo/ScrollBy ----绝对移动/相对移动(移动的是内容不是View)####
#### 动画移动和LayoutParams实现移动####
#### 事件分发机制####
#### ViewRoot和DecorView####
#### MeasureSpec、MeasureSpec和LayoutParams关系####
#### View的measure,layout，draw流程，适配padding####
#### 自定义属性####




  
    