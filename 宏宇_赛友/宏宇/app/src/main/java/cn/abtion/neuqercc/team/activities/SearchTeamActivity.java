package cn.abtion.neuqercc.team.activities;

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
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author lszr
 * @since 2017/11/28 下午3:21
 * email wsyglszr@gmail.com
 */

public class SearchTeamActivity extends NoBarActivity {
    @BindView(R.id.edit_search_team)
    EditText editSearchTeam;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_team;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        searchTeam();

    }

    @Override
    protected void loadData() {

    }

    public void searchTeam() {


        SpannableString ss = new SpannableString(getString(R.string.edit_search_team));
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editSearchTeam.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失


        editSearchTeam.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (editSearchTeam.getText().toString().equals(Config.EMPTY_FIELD)) {
                        ToastUtil.showToast(getString(R.string.search_team_null));
                    } else {
                        Intent intent = new Intent(SearchTeamActivity.this, SearchTeamResultActivity.class);
                        intent.putExtra("searchTeamName", editSearchTeam.getText().toString());
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }


    @OnClick(R.id.img_return)
    public void onReturnViewClicked() {
        finish();

    }


    @OnClick(R.id.img_search_team)
    public void onSearchViewClicked() {
        if (editSearchTeam.getText().toString().equals(Config.EMPTY_FIELD)) {
            ToastUtil.showToast(getString(R.string.search_team_null));
        } else {
            Intent intent = new Intent(SearchTeamActivity.this, SearchTeamResultActivity.class);
            intent.putExtra("searchTeamName", editSearchTeam.getText().toString());
            startActivity(intent);
        }
    }
}
