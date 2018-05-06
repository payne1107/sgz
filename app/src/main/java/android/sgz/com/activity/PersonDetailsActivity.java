package android.sgz.com.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.application.MyApplication;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.widget.CircleImageView;
import android.util.Log;
import android.view.View;

import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by WD on 2018/5/6.
 * 个人资料
 */

public class PersonDetailsActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView circleImageView;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_person_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("个人资料", true, true);
        circleImageView = (CircleImageView) findViewById(R.id.update_avatar);

        setListener();
    }

    private void setListener() {
        circleImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_avatar:
                //更新头像
                choosePhoto();
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
//                            SDCardUtil.saveBitmap(bitmap, newPath);
//                            uploadImg(MyApplication.userId, newPath);
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
}
