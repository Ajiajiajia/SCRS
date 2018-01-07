package cn.abtion.neuqercc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author fhyPayaso
 * @since 2017/11/22 17:00
 * email fhyPayaso@qq.com
 */

public class HonorGridView extends GridView{


    public HonorGridView(Context context) {
        super(context);
    }

    public HonorGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HonorGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 重写onMeasure解决滑动问题
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
