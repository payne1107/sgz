package android.sgz.com.activity;

import android.content.Context;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.RootCityAdapter;
import android.sgz.com.adapter.SubCityAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ChooseAllCityBean;
import android.sgz.com.utils.ConfigUtil;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WD on 2018/6/8.
 * 选择城市
 */

public class ChooseCityActivity extends BaseActivity {

    private ListView rootListView;
    private ListView subListView;
    private List<ChooseAllCityBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private RootCityAdapter adapter;
    private List<ChooseAllCityBean.DataBean.CityListBean> subList = new ArrayList<>();
    private int provinceId = 0;
    private int cityId = 0;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_city);
        mContext = ChooseCityActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("选择城市", true, true);
        setSettingBtn("保存");
        rootListView = findViewById(R.id.root_listview);
        subListView = findViewById(R.id.sub_listview);

        queryCityListInfo();

        setListener();
    }

    private void setListener() {
        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChooseAllCityBean.DataBean dataBean = (ChooseAllCityBean.DataBean) parent.getAdapter().getItem(position);
                provinceId = dataBean.getId();
                if (dataBean != null) {
                    subList =dataBean.getCityList();
                    SubCityAdapter subAdapter = new SubCityAdapter(mContext,subList);
                    subListView.setAdapter(subAdapter);
                }
            }
        });

        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChooseAllCityBean.DataBean.CityListBean cityListBean = (ChooseAllCityBean.DataBean.CityListBean) parent.getAdapter().getItem(position);
                if (cityListBean != null) {
                    cityId =cityListBean.getId();
                }
            }
        });
        //保存城市信息
        tvSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCityInfo(provinceId,cityId);
            }
        });
    }

    /****
     * 保存城市信息
     */
    private void saveCityInfo(int provinceid,int cityid) {
        if (provinceid == 0) {
            toastMessage("请选择城市信息");
            return;
        }
        if (cityid == 0) {
            toastMessage("请选择具体城市");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("provinceid", String.valueOf(provinceid));
        params.put("cityid", String.valueOf(cityid));
        httpPostRequest(ConfigUtil.SAVE_CITY_URL, params, ConfigUtil.SAVE_CITY_URL_ACTION);
    }

    /****
     * 查询城市信息
     */
    private void queryCityListInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_ALL_CITY_URL, params, ConfigUtil.QUERY_ALL_CITY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_ALL_CITY_URL_ACTION:
                Log.d("Dong", "获取城市信息----》" + json);
                handlerQueryAllCityInfo(json);
                break;
            case ConfigUtil.SAVE_CITY_URL_ACTION:
                if (getRequestCode(json) == 1) {
                    toastMessage("保存城市信息成功");
                    finish();
                }
                break;
        }
    }

    private void handlerQueryAllCityInfo(String json) {
        ChooseAllCityBean cityBean = JSON.parseObject(json, ChooseAllCityBean.class);
        if (cityBean != null) {
             mList = cityBean.getData();
             adapter = new RootCityAdapter(mContext,mList);
             rootListView.setAdapter(adapter);
        }
    }
}
