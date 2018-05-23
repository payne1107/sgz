package android.sgz.com.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.sgz.com.R;
import android.sgz.com.base.BaseActivity;
import android.sgz.com.utils.Bimp;
import android.sgz.com.utils.FileUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 92457 on 2018/3/17.
 * 查看大图....带删除页面
 */
public class PhotoActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private Button btnExit;
    private Button btnDel;
    private Button btnEnter;
    private RelativeLayout photoRelativeLayout;
    private ArrayList<View> listViews = null;
    private int count;


    public List<Bitmap> bmp = new ArrayList<Bitmap>();
    public List<String> drr = new ArrayList<String>();
    public List<String> del = new ArrayList<String>();
    public int max;
    private MyPageAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < Bimp.bmp.size(); i++) {
            bmp.add(Bimp.bmp.get(i));
        }
        for (int i = 0; i < Bimp.drr.size(); i++) {
            drr.add(Bimp.drr.get(i));
        }
        max = Bimp.max;

        for (int i = 0; i < bmp.size(); i++) {
            initListViews(bmp.get(i));//
        }
        adapter = new MyPageAdapter(listViews);// 构造adapter
        viewPager.setAdapter(adapter);// 设置适配器

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        viewPager.setCurrentItem(id);
    }

    private void initListViews(Bitmap bitmap) {
        if (listViews == null)
            listViews = new ArrayList<View>();
        ImageView img = new ImageView(this);// 构造textView对象
        img.setBackgroundColor(0xff000000);
        img.setImageBitmap(bitmap);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        listViews.add(img);// 添加view
    }

    @Override
    protected void initView() {
        super.initView();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btnExit = (Button) findViewById(R.id.photo_bt_exit);
        btnDel = (Button) findViewById(R.id.photo_bt_del);
        btnEnter = (Button) findViewById(R.id.photo_bt_enter);
        photoRelativeLayout = (RelativeLayout) findViewById(R.id.photo_relativeLayout);
        photoRelativeLayout.setBackgroundColor(0x70000000);
        setListener();
    }

    private void setListener() {
        btnDel.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_bt_del:
                //删除
                if (null != listViews && listViews.size() == 1) {
                    Bimp.bmp.clear();
                    Bimp.drr.clear();
                    Bimp.max = 0;
                    FileUtils.deleteDir();
                    finish();
                } else {
                    String newStr = drr.get(count).substring(
                            drr.get(count).lastIndexOf("/") + 1,
                            drr.get(count).lastIndexOf("."));
                    bmp.remove(count);
                    drr.remove(count);
                    del.add(newStr);
                    max--;
                    viewPager.removeAllViews();
                    listViews.remove(count);
                    adapter.setListViews(listViews);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.photo_bt_enter:
                //确定
                Bimp.bmp = bmp;
                Bimp.drr = drr;
                Bimp.max = max;
                for (int i = 0; i < del.size(); i++) {
                    FileUtils.delFile(del.get(i) + ".JPEG");
                }
                finish();
                break;
            case R.id.photo_bt_exit:
                //返回
                finish();
                break;
        }
    }


    /***
     * ViewPager监听器
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            count = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /****
     * ViewPager适配器
     */
    class MyPageAdapter extends PagerAdapter{
        private List<View> listViews;
        private int size;//页数
        public MyPageAdapter(List<View> listViews) {
            this.listViews = listViews;
            this.size = listViews == null ? 0 : listViews.size();
        }

        /***
         * 填充数据
         * @param listViews
         */
        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(listViews.get(position % size));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                ((ViewPager) container).addView(listViews.get(position % size), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listViews.get(position % size);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return  view == object;
        }
    }
}
