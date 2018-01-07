package cn.abtion.neuqercc.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import cn.abtion.neuqercc.NEUQerCCApplication;

/**
 * @author abtion.
 * @since 17/9/22 17:55.
 * email caiheng@hrsoft.net
 */

public final class ToastUtil {
    private static final int duration = Toast.LENGTH_SHORT;
    private static final boolean isShowErrorCode = true;

    public static void showToast(final String msg) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NEUQerCCApplication.getInstance(), msg, duration).show();
            }
        });
    }

    public static void showToast(@StringRes final int resId) {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NEUQerCCApplication.getInstance(), resId, duration).show();
            }
        });
    }

    public static void showToast(String msg, int... errorCode) {
        if (isShowErrorCode) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }

    public static void showToast(@StringRes int resId, int... errorCode) {
        String msg = NEUQerCCApplication.getInstance().getResources().getString(resId);
        if (isShowErrorCode) {
            for (int anErrorCode : errorCode) {
                msg = msg + "-" + anErrorCode;
            }
        }
        showToast(msg);
    }
}
