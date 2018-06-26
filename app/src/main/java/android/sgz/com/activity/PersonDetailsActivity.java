package android.sgz.com.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.VIPMemberCenterBasicInfoBean;
import android.sgz.com.utils.ConfigUtil;
import android.sgz.com.utils.SDCardUtil;
import android.sgz.com.utils.StringUtils;
import android.sgz.com.widget.CircleImageView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WD on 2018/5/6.
 * 个人资料
 */

public class PersonDetailsActivity extends BaseActivity implements View.OnClickListener, OnDateSetListener {

    private CircleImageView circleImageView;
    private AutoLinearLayout layoutMineName;
    private Context mContext;
    private AutoLinearLayout layoutProfession;
    private TextView tvProfessionName;
    private TextView tvSaveInfo;
    private AutoLinearLayout layoutProfessionTitle;
    private AutoLinearLayout layoutBirthday;
    private TextView tvBirthday;
    private AutoLinearLayout layoutPersonalizedSignature;
    private AutoLinearLayout layoutSetPhone;
    private AutoLinearLayout layoutSalary;
    private AutoLinearLayout layoutChooseCity;
    // 裁剪之后保存图片到新路径 专用头像路径，更换后会被替换头像
    private String newPath = MyApplication.getImageFolderPath() + ".png";
    private String personBasicInfoJson;
    private TextView tvName;
    private TextView tvProfessionNameLevel;
    private TextView tvSalary;
    private TextView tvPhone;
    private TextView tvCityName;
    private TextView tvSgin;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_details);
        mContext = PersonDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("个人资料", true, true);
        tvSaveInfo = (TextView) findViewById(R.id.activity_set);
        tvSaveInfo.setText("保存");
        tvSaveInfo.setVisibility(View.VISIBLE);
        personBasicInfoJson = getIntent().getStringExtra("person_basic_info");

        circleImageView = (CircleImageView) findViewById(R.id.update_avatar);
        layoutMineName = (AutoLinearLayout) findViewById(R.id.layout_mine_name);
        layoutProfession = (AutoLinearLayout) findViewById(R.id.layout_profession);
        tvProfessionName = (TextView) findViewById(R.id.tv_profession_name);
        tvName = findViewById(R.id.tv_name);
        tvProfessionNameLevel = findViewById(R.id.tv_profession_level);
        tvSalary = findViewById(R.id.tv_salary);
        tvPhone = findViewById(R.id.tv_phone);
        tvCityName = findViewById(R.id.tv_city_name);
        tvSgin = findViewById(R.id.tv_sign);
        layoutProfessionTitle = (AutoLinearLayout) findViewById(R.id.layout_profession_title);
        layoutBirthday = (AutoLinearLayout) findViewById(R.id.layout_birthday);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        layoutPersonalizedSignature = (AutoLinearLayout) findViewById(R.id.layout_personalized_signature);
        layoutSetPhone = (AutoLinearLayout) findViewById(R.id.layout_set_phone);
        layoutSalary = findViewById(R.id.layout_salary);
        layoutChooseCity = findViewById(R.id.layout_choose_city);

        setListener();
        initViewDateDialog(this, System.currentTimeMillis() - ConfigUtil.TenYears);

        setBasicInfoValue(personBasicInfoJson);
    }


    /****
     * 获取基本信息
     */
    private void getBasicInfo() {
        startIOSDialogLoading(mContext,"加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_VIP_BASIC_INFO_URL, params, ConfigUtil.QUERY_VIP_BASIC_INFO_URL_ACTION);
    }


    /****
     * 设置用户基本信息
     * @param personBasicInfoJson
     */
    private void setBasicInfoValue(String personBasicInfoJson) {
        VIPMemberCenterBasicInfoBean bean = JSON.parseObject(personBasicInfoJson, VIPMemberCenterBasicInfoBean.class);
        if (bean != null) {
            VIPMemberCenterBasicInfoBean.DataBean data = bean.getData();
            if (data != null) {
                String photo =data.getPhoto();
                String realName =data.getRealname();
                String profession =data.getProfession();
                String professionLevelName =data.getProfessionlevelname();
                String salary =data.getMsalary();
                String mobile =data.getMobile();
                String cityName =data.getCityname();
                String birthday =data.getBirthday();
                String sign = data.getSign();
                if (!StringUtils.isEmpty(photo)) {
                    MyApplication.imageLoader.displayImage(photo,  circleImageView);
                }
                tvName.setText(StringUtils.isEmpty(realName) ? "" : realName);
                tvProfessionName.setText(StringUtils.isEmpty(profession) ? "请选择" : profession);
                tvProfessionNameLevel.setText(StringUtils.isEmpty(professionLevelName) ? "请选择" : professionLevelName);
                tvSalary.setText(StringUtils.isEmpty(salary) ? "" : salary);
                tvPhone.setText(StringUtils.isEmpty(mobile) ? "" : mobile);
                tvCityName.setText(StringUtils.isEmpty(cityName) ? "请选择" : cityName);
                tvBirthday.setText(StringUtils.isEmpty(birthday)?"请选择":birthday);
                tvSgin.setText(StringUtils.isEmpty(sign) ? "未填写" : sign);
            }
        }
    }

    private void setListener() {
        circleImageView.setOnClickListener(this);
        layoutMineName.setOnClickListener(this);
        layoutProfession.setOnClickListener(this);
        tvSaveInfo.setOnClickListener(this);
        layoutProfessionTitle.setOnClickListener(this);
        layoutBirthday.setOnClickListener(this);
        layoutPersonalizedSignature.setOnClickListener(this);
        layoutSetPhone.setOnClickListener(this);
        layoutSalary.setOnClickListener(this);
        layoutChooseCity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_avatar:
                //更新头像
                choosePhoto();
                break;
            case R.id.layout_mine_name:
                //修改昵称
                startActivity(new Intent(mContext, UpdateNickNameActvity.class));
                break;
            case R.id.layout_profession:
                //职业选择
                startActivity(new Intent(mContext,ChooseProfessionActivity.class));
                break;
            case R.id.layout_profession_title:
                startActivity(new Intent(mContext, ChooseProfessionLevelActivity.class));
                break;
            case R.id.activity_set:
                //保存用戶信息
                saveBirthdayData();
                break;
            case R.id.layout_birthday:
                //选择生日
                //项目开始日期
                dialogDay.show(getSupportFragmentManager(), "all");
                break;
            case R.id.layout_personalized_signature:
                //个性签名
                startActivity(new Intent(mContext, PersonalizedSignatureActivity.class));
                break;
            case R.id.layout_set_phone:
                startActivity(new Intent(mContext, SetPhoneNumberActivity.class));
                break;
            case R.id.layout_salary:
                //设置工资
                startActivity(new Intent(mContext, SetUserSalaryActivity.class));
                break;
            case R.id.layout_choose_city:
                startActivity(new Intent(mContext, ChooseCityActivity.class));
                break;
        }
    }

    /***
     * 选择相册
     */
    private void choosePhoto() {
        if (isGetPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            readExternalStorage();
        } else {
            goSeePhoto();
        }
    }

    private void goSeePhoto() {
        if (isGetPermission(Manifest.permission.CAMERA)) {
            showCamera();
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, MyApplication.SELECT_PICTURE_CODE);
        }
    }

    @Override
    public void handleShowCamera() {
        super.handleShowCamera();
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, MyApplication.SELECT_PICTURE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MyApplication.SELECT_PICTURE_CODE:
                    Uri imageUrl = data.getData();
                    startPhotoZoom(imageUrl, false);
                    break;
                case MyApplication.REQUEST_CODE_RESULT:
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            Bitmap bitmap = bundle.getParcelable("data");
                            //将bitmap保存到本地，下次在获取可以从本地获取展示，之后在从网络获取显示
                            circleImageView.setImageBitmap(bitmap);
                            SDCardUtil.saveBitmap(bitmap, newPath);
                            uploadImg(MyApplication.userId, newPath);
                        }
                    }
                    break;
            }
        }
    }

    /***
     * @author Dong 裁剪图片
     */
    public void startPhotoZoom(Uri uri, boolean type) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && type) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        /** outputX 越大，图片越大 */
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, MyApplication.REQUEST_CODE_RESULT);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date d = new Date(millseconds);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        tvBirthday.setText(sf.format(d));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBasicInfo();
    }

    /***
     * 保存生日日期
     */
    private void saveBirthdayData() {
        String birthday = tvBirthday.getText().toString().trim();
        if (birthday.equals("请选择")) {
            toastMessage("请选择出生日期");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("birthday", birthday);
        httpPostRequest(ConfigUtil.SAVE_BIRTHDAY_URL, params, ConfigUtil.SAVE_BIRTHDAY_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.SAVE_BIRTHDAY_URL_ACTION:
                Log.d("Dong", "出生日期---》" +json);
                handleSaveBirthday(json);
                break;
            case ConfigUtil.QUERY_VIP_BASIC_INFO_URL_ACTION:
                Log.d("Dong", "获取基本信息----》" +json);
                setBasicInfoValue(json);
                break;
        }
    }

    /****
     * 保存生日日期
     * @param json
     */
    private void handleSaveBirthday(String json) {
        int requestCode = getRequestCode(json);
        if (requestCode == 1) {
            toastMessage("保存成功");
        }
    }

    @Override
    protected void getImageUrl(String url) {
        super.getImageUrl(url);
        Log.d("Dong", "url --->" + url + "new Path--- " + newPath);
    }
}
