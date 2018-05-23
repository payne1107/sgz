package android.sgz.com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.sgz.com.R;
import android.sgz.com.utils.Bimp;
import android.sgz.com.utils.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by 92457 on 2018/5/23.
 */

public class HorizontalListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater; // 视图容器
    public void update() {
        loading();
    }

    public HorizontalListViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return Bimp.bmp.size();
    }

    @Override
    public Object getItem(int i) {
        return Bimp.bmp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, viewGroup, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("Dong", "Bimp.bmp.get(position) ====" + Bimp.max +"Bimp.bmp.get(position)"+Bimp.bmp.get(position));
        holder.image.setImageBitmap(Bimp.bmp.get(position));
        return convertView;
    }

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (null != Bimp.drr) {
                        Log.d("Dong", "Bimp.max ====" + Bimp.max +"Bimp.drr.size()" + Bimp.drr.size());
                        if (Bimp.max == Bimp.drr.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            try {
                                String path = Bimp.drr.get(Bimp.max); /// Bimp.drr 存储的是 图片的绝对地址/storage/sdcard0/DCIM/Camera/20170503_165018.jpg
                                Log.d("Dong", "path ====" + path);  /// 图片地址
                                Bitmap bm = Bimp.revitionImageSize(path); //压缩后图片bitmap
                                Bimp.bmp.add(bm);                       // 存储bitmap集合
                                String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));// 图片地址拆分 => 20170427_205252.jpg
                                String newPath = FileUtils.saveBitmap(bm, "" + newStr); //压缩图片后得到图片地址
                                Log.d("Dong", "newStr ====" + newStr + " ?????????????????? newPath == " + newPath);
                                Bimp.drr.remove(Bimp.max); //移除之前的原图
                                Bimp.drr.add(Bimp.max, newPath); //加入压缩后的图片
                                Bimp.max += 1;
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public class ViewHolder {
        public ImageView image;
    }
}
