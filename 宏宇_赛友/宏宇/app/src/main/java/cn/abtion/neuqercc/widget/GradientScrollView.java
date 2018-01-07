package cn.abtion.neuqercc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * @author fhyPayaso
 * @since 2017/11/26 17:03
 * email fhyPayaso@qq.com
 */

public class GradientScrollView extends ScrollView {


    private ScrollViewListener scrollViewListener = null;

    public GradientScrollView(Context context) {
        super(context);
    }

    public GradientScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected  void onScrollChanged(int x,int y,int oldx,int oldy) {

        //x表示当前横坐标，y表示当前纵坐标，oldx表示上次横坐标，oldy表示上次纵坐标
        super.onScrollChanged(x, y, oldx, oldy);
        if(scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this,x,y,oldx,oldy);
        }

    }


    public interface ScrollViewListener {

        void onScrollChanged(GradientScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener=scrollViewListener;
    }

}
