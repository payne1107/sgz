package android.sgz.com.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.sgz.com.httpstack.OkHttpStack;
import android.sgz.com.utils.CacheImgUtil;
import android.sgz.com.utils.SPUtil;
import android.text.format.DateFormat;
import android.util.Log;

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
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import java.io.File;
import java.util.Date;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

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
    public static String currentCity = "";//只有城市不包含区域

    private static MyApplication mInstance;
    //是否需要强制更新
    public static boolean isForUpdate = false;
    public static String SERVER_VERSION_NAME = "";
    public static boolean isClickUpdateVersionBtn = false;
    public static String userPhone ="";
    public static final String wxAppID = "wx94db1f1acddbfcd6";
    public static final String MCH_ID = "1509458841";
    public static IWXAPI iwxapi;
    public static String rongCloudToken = "";//融云的token

    public static MyApplication getApplication() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        rongCloudToken = SPUtil.getString(this, "rongCloudToken");
        initImageLoader(this);
        createVolley();
        ///xutils3初始化
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        CacheImgUtil.getInstance(this);
        //注册Crash接口(必选) 蒲公英
        PgyCrashManager.register(this);
        JPushInterface.init(this);
        //初始化
        UMConfigure.init(this, "5b348af1f43e480284000042", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //设置全局的异常捕获器
       //CatchExceptionHandler.getInstance().setDefaultUnCachExceptionHandler();
        iwxapi = WXAPIFactory.createWXAPI(getApplicationContext(), wxAppID,true);
        iwxapi.registerApp(wxAppID);
        //融云初始化
        RongIM.init(this);
        Log.d("Dong", "rongCloudToken------------------------>" + rongCloudToken);
        connect(rongCloudToken);

        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            @Override
            public Message onSend(Message message) {
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                Log.e("Dong", "sentMessageErrorCode ==="+sentMessageErrorCode.getValue() +"message + "+ message.getTargetId());
                Message.SentStatus status = message.getSentStatus();
                Log.e("Dong", "sentMessageErrorCode ==="+sentMessageErrorCode.getMessage() +"message + "+status.getValue() );
                if(message.getSentStatus()== Message.SentStatus.FAILED){
                    if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_CHATROOM){
                        //不在聊天室
                    }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_DISCUSSION){
                        //不在讨论组
                    }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_GROUP){
                        //不在群组
                    }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.REJECTED_BY_BLACKLIST){
                        //你在他的黑名单中
                    }
                    Log.d("Dong", "消息发送失败了");
                }

                MessageContent messageContent = message.getContent();

                if (messageContent instanceof TextMessage) {//文本消息
                    TextMessage textMessage = (TextMessage) messageContent;
                    Log.d("Dong", "onSent-TextMessage:" + textMessage.getContent());
                } else {
                    Log.d("Dong", "onSent-其他消息，自己来判断处理");
                }

                return false;
            }
        });
    }

    //配置网络框架
    private void createVolley() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }
    }

    /** 友盟三方登录 Id & key 入口 **/
    {
        PlatformConfig.setWeixin("wx94db1f1acddbfcd6","2ccceed033eda7939323cdf4703fe000");
        PlatformConfig.setQQZone("1106919069","8BuoORvKxidGie64");
        //PlatformConfig.setSinaWeibo("2597775029","0e89b0d8b79b943615ed541b4bfda00c","http://www.sina.com");
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



    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link #init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @param callback 连接回调。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token) {
        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {
            Log.d("Dong", "进来了吗--------》" + getApplicationInfo().packageName);
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("Dong", "--onTokenIncorrect" );
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("Dong", "----------------------------------------------------------------onSuccess" + userid);
                    //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                   // finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("Dong", "RongIMClient.ErrorCode-------》" + errorCode);
                }
            });
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
