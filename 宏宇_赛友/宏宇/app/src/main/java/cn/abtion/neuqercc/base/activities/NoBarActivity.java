package cn.abtion.neuqercc.base.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.sql.Time;
import java.util.Calendar;

/**
 * super activity for no bar activity
 * @author abtion.
 * @since 17/9/22 17:45.
 * email caiheng@hrsoft.net
 */

public abstract class NoBarActivity extends BaseActivity {

    static Calendar timeBegin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }
}