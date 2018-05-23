package android.sgz.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.adapter.ImageBucketAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.widget.camera.AlbumHelper;
import android.sgz.com.widget.camera.ImageBucket;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 * 选择相册图片
 */

public class ChoosePicActivity extends BaseActivity {
    private AlbumHelper helper;
    private List<ImageBucket> dataList;
    private GridView gridView;
    private ImageBucketAdapter adapter;
    public static final String EXTRA_IMAGE_LIST = "imagelist";

    @Override
    protected void initView() {
        super.initView();
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        dataList = helper.getImagesBucketList(false);
        gridView = (GridView) findViewById(R.id.gridview);
        adapter = new ImageBucketAdapter(ChoosePicActivity.this, dataList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 根据position参数，可以获得跟GridView的子View相绑定的实体类，然后根据它的isSelected状态，
                 * 来判断是否显示选中效果。 至于选中效果的规则，下面适配器的代码中会有说明
                 */
                /**
                 * 通知适配器，绑定的数据发生了改变，应当刷新视图
                 */
                // adapter.notifyDataSetChanged();
                Intent intent = new Intent(ChoosePicActivity.this, ImageGridActivity1.class);
                intent.putExtra(ChoosePicActivity.EXTRA_IMAGE_LIST, (Serializable) dataList.get(position).imageList);
                startActivity(intent);
                finish();
            }

        });
    }

    @Override
    protected void initData() {
        setInVisibleTitleIcon("相册",true,false);
        TextView tvCancel = (TextView) findViewById(R.id.activity_set);
        tvCancel.setText("取消");
        tvCancel.setVisibility(View.VISIBLE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image_bucket);
    }
}
