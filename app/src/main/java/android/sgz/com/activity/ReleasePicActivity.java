package android.sgz.com.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.sgz.com.R;
import android.sgz.com.adapter.HorizontalListViewAdapter;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.Bimp;
import android.sgz.com.utils.SDCardUtil;
import android.sgz.com.widget.HorizontalListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

import static android.sgz.com.application.MyApplication.PHOTO_PATH;

/**
 * Created by 92457 on 2018/5/19.
 */

public class ReleasePicActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutRelease;
    private EditText etFeedBack;
    private ImageView ivCamera;
    private Context mContext;
    private Dialog dialog;
    private HorizontalListView listView;
    private HorizontalListViewAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_release_pic);
        mContext = ReleasePicActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("发表图片", true, true);
        layoutRelease = (AutoLinearLayout) findViewById(R.id.layout_release);
        etFeedBack = (EditText) findViewById(R.id.et_feedback);
        ivCamera = (ImageView) findViewById(R.id.iv_camera);
        listView = (HorizontalListView) findViewById(R.id.horizontal_listview);
        adapter = new HorizontalListViewAdapter(this);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        ivCamera.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,PhotoActivity.class);
                intent.putExtra("ID", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_camera:
                showSeclectPhotoDialog();
                break;
            case R.id.tv_cancel_photo:
                dialog.dismiss();
                break;
            case R.id.tv_select_photo:
                choosePhoto();
                dialog.dismiss();
                break;
            case R.id.tv_take_photo:
                if (isGetPermission(Manifest.permission.CAMERA)) {
                    showCamera();
                } else {
                    startPhoto();
                }
                dialog.dismiss();
                break;
        }
    }

    /***
     * 打开相机拍照
     */
    private void startPhoto() {
        if(SDCardUtil.cheekSDCardIsMounted()) {
            Intent intentToCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intentToCamera, MyApplication.SLECT_CARMEA_CODE);
        } else {
            toastMessage(getString(R.string.str_check_sd_card));
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

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.update();
        }
    }

    /***
     * 选择相册
     */
    private void goSeePhoto() {
        Intent intent = new Intent(mContext, ChoosePicActivity.class);
        startActivity(intent);
        dialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case MyApplication.SLECT_CARMEA_CODE: //相机拍照
                    Bitmap bitmap;
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        bitmap = bundle.getParcelable("data");
                        //将bitmap保存到本地，下次获取可以从本地获取展示，之后在从网络取出显示
                        SDCardUtil.saveBitmap(bitmap, PHOTO_PATH);
                        if (Bimp.drr.size() < 6) {
                            Bimp.drr.add(PHOTO_PATH);
                            //Bimp.bmp.add(bitmap);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Bimp.bmp.clear();
        Bimp.drr.clear();
        Bimp.max = 0;
    }

    /**
     * 选着照片和拍照的Dialog
     * @author Dong
     ***/
    private void showSeclectPhotoDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.photo_choose_dialog, null);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView btnCancel = (TextView) view.findViewById(R.id.tv_cancel_photo);
        TextView btnTakePhoto = (TextView) view.findViewById(R.id.tv_take_photo);
        TextView btnSelectPhoto = (TextView) view.findViewById(R.id.tv_select_photo);
        btnCancel.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);
        btnSelectPhoto.setOnClickListener(this);
    }
}
