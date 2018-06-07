package android.sgz.com.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.sgz.com.httpstack.OkHttpStack;
import android.sgz.com.utils.CacheImgUtil;
import android.text.format.DateFormat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.pgyersdk.crash.PgyCrashManager;

import org.xutils.x;

import java.io.File;
import java.util.Date;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 92457 on 2018/1/12.
 */

public class MyApplication extends Application {
    public static final String REQUEST_URL = "http://47.101.46.2:90/";
    public static final int IMAGE_ITEM_ADD = -1;

    //是否正在下载app
    public static boolean IS_DOWNLOAD = false;
    //网络框架
    public static RequestQueue mRequestQueue;

    public static ImageLoader imageLoader;
    //图片缓存路径
    public static final String dirname = "sgz";
    public static final String dirname_cache = "/" + dirname + "/cache/";
    /** 图片存储路基 **/
    public static final String PHOTO_PATH = MyApplication.getImageFolderPath() +
            DateFormat.format("yyyy-MM-dd-hh-mm-ss", new Date()) + ".png";

    //是否已经登陆
    public static String isLogin = "";
    //刷新token需要的值
    public static String refreshToken = "";
    //用户的userId
    public static String userId = "";

    /** 相册选择 */
    public static final int SELECT_PICTURE_CODE = 1;
    public static final int SLECT_CARMEA_CODE = 2;
    public static final int REQUEST_CODE_RESULT = 3;
    public static final int REQUEST_CODE_PREVIEW = 4;


    public static double currentLon = 0.0;
    public static double currentLat = 0.0;
    public static String currentArea;

    private static MyApplication mInstance;
    //是否需要强制更新
    public static boolean isForUpdate = false;
    public static String SERVER_VERSION_NAME = "";
    public static boolean isClickUpdateVersionBtn = false;

    public static MyApplication getApplication() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
        createVolley();
        ///xutils3初始化
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        CacheImgUtil.getInstance(this);
        //注册Crash接口(必选) 蒲公英
        PgyCrashManager.register(this);
        JPushInterface.init(this);
        //设置全局的异常捕获器
       //CatchExceptionHandler.getInstance().setDefaultUnCachExceptionHandler();
    }

    //配置网络框架
    private void createVolley() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }
    }

    /***
     * 初始化图片框架
     * @param context
     * @return
     */
    public static ImageLoader initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, dirname_cache);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCacheExtraOptions(480, 800)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .threadPoolSize(3)//线程池内加载的数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheExtraOptions(480, 320, null)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        return imageLoader;
    }

    /***
     * @author Dong
     * 创建图片的存储路径WBShareActivity
     */
    public static String getImageFolderPath() {
        String path = Environment.getExternalStorageDirectory() + File.separator + "sgz"
                + File.separator + "data" +
                File.separator + "APP_FOLDER_NAME" +
                File.separator + "MY_FLODER_NAME"
                + File.separator;
        if (makeDirs(path)) {
            return path;
        }
        return null;
    }

    public static boolean makeDirs(String path) {
        File dir = new File(path);
        return dir.exists() || dir.mkdirs();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
