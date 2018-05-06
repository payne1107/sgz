package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.DpAndPxUtils;
import android.sgz.com.utils.PhoneSystem;
import android.sgz.com.utils.StringUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WD on 2018/5/6.
 * 搜索
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText etSearch;
    private TextView tvSearch;
    private TagFlowLayout tagFlowLayout;
    private AutoLinearLayout layoutSearch;
    private List<String> tagFlowLayoutList = new ArrayList<>();
    private String searchStr;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activivty_search);
    }

    @Override
    protected void initData() {
        tagFlowLayoutList.add("日结");
        tagFlowLayoutList.add("家教");
        tagFlowLayoutList.add("服务员");
        setFlowLayout(tagFlowLayout,tagFlowLayoutList);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("", true, true);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setVisibility(View.VISIBLE);
        tvSearch = (TextView) findViewById(R.id.activity_set);
        tvSearch.setText("搜索");
        tvSearch.setVisibility(View.VISIBLE);

        tagFlowLayout = (TagFlowLayout) findViewById(R.id.TagFlowLayout);
        layoutSearch = (AutoLinearLayout) findViewById(R.id.layout_search);

//        initListView();
        setListenter();
    }

    private void setListenter() {
        tvSearch.setOnClickListener(this);
        //浮动标签点击事件
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                searchStr = tagFlowLayoutList.get(position);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }


    /****
     * 设置浮动标签
     */
    private void setFlowLayout(TagFlowLayout flowLayout,List<String> mValues) {
        final List<TextView> tvs = new ArrayList<>();
        final LayoutInflater mInflater = LayoutInflater.from(this);
        final TagFlowLayout finalFlowLayout = flowLayout;
        TagAdapter adapter = new TagAdapter<String>(mValues) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.flag_text_context, finalFlowLayout, false);
                tv.setText(s);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (!StringUtils.isEmpty(PhoneSystem.getSystem()) && PhoneSystem.getSystem().equals(PhoneSystem.SYS_EMUI)) {
                    lp.setMargins(0, DpAndPxUtils.dip2px(SearchActivity.this,6),
                            DpAndPxUtils.dip2px(SearchActivity.this,4), 0);
                } else {
                    lp.setMargins(0, DpAndPxUtils.dip2px(SearchActivity.this,12), DpAndPxUtils.dip2px(SearchActivity.this,8), 0);
                }
                tv.setLayoutParams(lp);
                tvs.add(tv);
                return tv;
            }
        };
        flowLayout.setAdapter(adapter);
    }
}
