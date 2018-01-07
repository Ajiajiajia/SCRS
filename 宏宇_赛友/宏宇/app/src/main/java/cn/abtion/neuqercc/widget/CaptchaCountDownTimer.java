package cn.abtion.neuqercc.widget;

import android.os.CountDownTimer;

/**
 * @author fhyPayaso
 * @since 2017/11/19 16:27
 * email fhyPayaso@qq.com
 */

public class CaptchaCountDownTimer extends CountDownTimer{



    public static long curMillis =0;
    public static boolean FLAG_FIRST_IN =true;


    public CaptchaCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }

    public void timerStart(boolean onClick){

        if(onClick) {
            CaptchaCountDownTimer.curMillis= System.currentTimeMillis();
        }
        CaptchaCountDownTimer.FLAG_FIRST_IN=false;

        this.start();
    }


}
