package android.sgz.com.activity;

import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ChooseProfessionLevelAdapter;
import android.sgz.com.base.BaseActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 92457 on 2018/6/7.
 * 获取职称
 */

public class ChooseProfessionLevelActivity extends BaseActivity {

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_profession_level);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("职称", true, true);
        ListView listView = findViewById(R.id.listView);
        ChooseProfessionLevelAdapter adapter = new ChooseProfessionLevelAdapter();
        listView.setAdapter(adapter);
    }

    /****
     * 获取职称信息
     */
    private void queryAllProfessionLevelData() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
    }
}
