package android.sgz.com.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sgz.com.R;
import android.sgz.com.adapter.ImageGridAdapter;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.bean.ImageItem;
import android.sgz.com.utils.Bimp;
import android.sgz.com.utils.FileUtils;
import android.sgz.com.widget.camera.AlbumHelper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/***
 * 展示图片列表
 */
public class ImageGridActivity1 extends BaseActivity {
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	private List<ImageItem> dataList;
	private GridView gridView;
	private ImageGridAdapter adapter;
	private AlbumHelper helper;
	private TextView btn_confirm;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity1.this, "最多选择6张图片", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void initView() {
		super.initView();
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity1.this, dataList, mHandler);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new ImageGridAdapter.TextCallback() {
			public void onListen(int count) {
				btn_confirm.setText("上传" + "(" + count + ")");
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void initData() {
		setInVisibleTitleIcon("相册",true,false);
		TextView tvCancel = (TextView) findViewById(R.id.activity_set);
		tvCancel.setText("取消");
		tvCancel.setVisibility(View.VISIBLE);
		tvCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	@Override
	protected void onCreateCustom(Bundle savedInstanceState) {
		setContentView(R.layout.activity_image_grid2);
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		dataList = (List<ImageItem>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);

		initView();
		btn_confirm = (TextView) findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Collection<String> c = adapter.map.values();
				Iterator<String> it = c.iterator();
				final ArrayList<String> list = new ArrayList<String>();
				for (; it.hasNext();) {
					list.add(it.next());
				}
				for (int i = 0; i < list.size(); i++) {
					if (Bimp.drr.size() < 6) {
						Bimp.drr.add(list.get(i));
					}
				}
				finish();
			}
		});
	}

	/**
	 * 压缩图片
	 */
	private void revitionImageSize() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (null != Bimp.drr) {
						if (Bimp.max == Bimp.drr.size()) {
							break;
						} else {
							try {
								String path = Bimp.drr.get(Bimp.max); // Bimp.drr 存储的是 图片的绝对地址/storage/sdcard0/DCIM/Camera/20170503_165018.jpg
								Bitmap bm = Bimp.revitionImageSize(path); //压缩后图片bitmap
								Bimp.bmp.add(bm);                       // 存储bitmap集合
								String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));// 图片地址拆分 => 20170427_205252.jpg
								String newPath = FileUtils.saveBitmap(bm, "" + newStr); //压缩图片后得到图片地址
								Log.d("Dong", "newStr ====" + newStr + " ?????????????????? newPath == " + newPath);
								Bimp.drr.remove(Bimp.max); //移除之前的原图
								Bimp.drr.add(Bimp.max, newPath); //加入压缩后的图片
								Bimp.max += 1;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}).start();
	}
}
