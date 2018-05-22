package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.PoiKeywordSearchAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.PoiAddressBean;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 92457 on 2018/5/22.
 * 选择地址
 */

public class ChooseLocationActivity extends BaseActivity implements View.OnClickListener, LocationSource, AMap.OnMapClickListener, AMapLocationListener, PoiSearch.OnPoiSearchListener {
    private EditText etSearch;
    private TextView tvSearch;
    private MapView mMapView;
    private AMap aMap;
    private UiSettings uiSettings;
    private MyLocationStyle locationStyle;
    private AMapLocationClient mlocationClient;
    private MarkerOptions markerOptions;
    private AMapLocationClientOption mLocationOption;
    private double currentLat;
    private double currentLon;
    private String keyWord;
    private ListView listView;
    private PoiSearch.Query query;
    private PoiResult poiResult;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_location);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("", true, true);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setHint("搜索地址信息..");
        etSearch.setVisibility(View.VISIBLE);
        tvSearch = (TextView) findViewById(R.id.activity_set);
        tvSearch.setText("搜索");
        tvSearch.setVisibility(View.VISIBLE);
        listView = (ListView) findViewById(R.id.listView);

        setListener();
        init();
    }

    private void setListener() {
        tvSearch.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PoiAddressBean data = (PoiAddressBean) parent.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.putExtra(ReleaseOrderActivity.REQUEST_CHOOSE_LOCATION_ADDRESS_KEY, data.getDetailAddress());
                intent.putExtra(ReleaseOrderActivity.REQUEST_CHOOSE_LOCATION_LAT_KEY, data.getLatitude());
                intent.putExtra(ReleaseOrderActivity.REQUEST_CHOOSE_LOCATION_LON_KEY, data.getLongitude());
                setResult(RESULT_OK,intent);
                finish();
                toastMessage("你点击了---》" + data.getCity() +" -->" +data.getDetailAddress() +"-->" +data.getDistrict() +"->>" +data.getProvince());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_set:
                //搜索地址信息获取详细列表
                doSearchQuery();
                hideKeyboard();
                break;
        }
    }

    /***
     * 初始化地图
     */
    private void init() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        uiSettings = aMap.getUiSettings();
        //定位（当前位置）的绘制样式类。
        locationStyle = new MyLocationStyle();
        mlocationClient = new AMapLocationClient(this);
        markerOptions = new MarkerOptions();
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(500000);
        //设置缓存
        mLocationOption.setLocationCacheEnable(false);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        aMap.setMyLocationStyle(locationStyle);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //解决每次都先显示北京地图问题
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.8195224566,117.2286701202), 19));

        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        setMapSettings();

        //当可视范围改变时回调的接口 滑动地图回调
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                LatLng latLng = cameraPosition.target;
                Log.d("Dong", "latLng == " +latLng);
            }
        });
        aMap.setOnMapClickListener(this);
        //启动定位
        mlocationClient.startLocation();
    }


    /****
     * 设置地图上图标显示
     */
    private void setMapSettings() {
        // 设置定位监听
        aMap.setLocationSource(this);
        //设置是否显示默认定位按钮
        uiSettings.setMyLocationButtonEnabled(true);
        //设置手势缩放
        uiSettings.setZoomGesturesEnabled(true);
        //隐藏缩放和放大按钮
        uiSettings.setZoomControlsEnabled(false);
        // 是否可触发定位并显示定位层
        aMap.setMyLocationEnabled(true);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView != null) {
            mMapView.onDestroy();
        }
        if (mlocationClient != null) {
            mlocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁}
            mlocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。重新绘制加载地图
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume()，
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause()，暂停地图的绘制
        mMapView.onPause();
        deactivate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState(outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                currentLat = amapLocation.getLatitude();
                currentLon = amapLocation.getLongitude();
                //存储经纬度
                LatLng location = new LatLng(currentLat, currentLon);
                //设置地图展示的当前位置和缩放级别 缩放级别是在3-19之间
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("Dong","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        int currentPage = 0;
        //不输入城市名称有些地方搜索不到
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        keyWord = etSearch.getText().toString().trim();
        query = new PoiSearch.Query(keyWord, "", "合肥市");
        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(50);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {  // 搜索poi的结果
                if (result.getQuery().equals(query)) {  // 是否是同一条
                    poiResult = result;
                    ArrayList<PoiAddressBean> data = new ArrayList<PoiAddressBean>();//自己创建的数据集合
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    for(PoiItem item : poiItems){
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();
                        double lat = llp.getLatitude();

                        String title = item.getTitle();
                        String text = item.getSnippet();
                        String provinceName = item.getProvinceName();
                        String cityName = item.getCityName();
                        String adName = item.getAdName();
                        data.add(new PoiAddressBean(String.valueOf(lon), String.valueOf(lat), title, text,provinceName, cityName,adName));
                    }
                    PoiKeywordSearchAdapter adapter = new PoiKeywordSearchAdapter(ChooseLocationActivity.this,data);
                    listView.setAdapter(adapter);
                }
            } else {
                toastMessage("对不起，没有搜索到相关数据");
            }
        } else {
            toastMessage("搜索失败" + rCode);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
