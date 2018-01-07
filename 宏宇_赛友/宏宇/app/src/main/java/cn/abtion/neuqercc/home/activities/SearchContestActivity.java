package cn.abtion.neuqercc.home.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.team.activities.SearchTeamResultActivity;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author lszr
 * @since 2017/12/24 上午11:56
 * email wsyglszr@gmail.com
 */

public class SearchContestActivity extends NoBarActivity {
    @BindView(R.id.edit_search_contest)
    EditText editSearchContest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_contest;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        searchContest();

    }

    private void searchContest() {
        SpannableString ss = new SpannableString(getString(R.string.edit_search_contest));
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editSearchContest.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失


        editSearchContest.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (editSearchContest.getText().toString().equals(Config.EMPTY_FIELD)) {
                        ToastUtil.showToast(getString(R.string.search_contest_null));
                    } else {
                        Intent intent = new Intent(SearchContestActivity.this, SearchTeamResultActivity.class);
                        intent.putExtra("searchContestName", editSearchContest.getText().toString());
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void loadData() {

    }



    @OnClick(R.id.img_search_contest)
    public void onViewClicked() {
        if (editSearchContest.getText().toString().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast(getString(R.string.search_contest_null));
        } else {
            Intent intent = new Intent(SearchContestActivity.this, SearchContestResultActivity.class);
            intent.putExtra("searchContestName", editSearchContest.getText().toString());
            startActivity(intent);
        }
    }
}
