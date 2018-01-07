package cn.abtion.neuqercc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.common.Config;

import static android.view.MotionEvent.ACTION_UP;
import static cn.abtion.neuqercc.common.Config.FLING_MIN_DISTANCE;

/**
 * @author lszr
 * @since 2017/11/13 下午10:11
 * email wsyglszr@gmail.com
 */

public class CustomFlipper extends ViewFlipper {
    CustomFlipper vfContest;

    private int downX = Config.VIEW_FLIPPER_INITAIL_VALUE;

    public CustomFlipper(Context context) {
        super(context);
    }

    public CustomFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x;
        switch (event.getAction()) {

            case ACTION_UP:
                x = (int) event.getRawX();
                if (x - downX < FLING_MIN_DISTANCE) {
                    vfContest.setInAnimation(inFromRightAnimation());
                    vfContest.setOutAnimation(outToLeftAnimation());
                    vfContest.showNext();
                } else if (downX - x < FLING_MIN_DISTANCE) {
                    vfContest.setOutAnimation(outToRightAnimation());
                    vfContest.setInAnimation(inFromLeftAnimation());
                    vfContest.showPrevious();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getRawX();
                break;
            default:
                break;
        }
        return false;
    }
    /**
     * 定义从右侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(200);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }
    /**
     * 定义从左侧退出的动画效果
     *
     * @return
     */
    protected Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(200);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
    /**
     * 定义从左侧进入的动画效果
     *
     * @return
     */
    protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(200);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
    /**
     * 定义从右侧退出时的动画效果
     *
     * @return
     */
    protected Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(200);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }
}
