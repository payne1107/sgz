package android.sgz.com.utils;

import android.os.Process;
import android.util.Log;

/**
 * Created by 92457 on 2018/3/28.
 * 全局的异常捕获器
 */

public class CatchExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static CatchExceptionHandler cauchExceptionHandler = null;

    private CatchExceptionHandler(){

    }

    public static CatchExceptionHandler getInstance() {
        if (cauchExceptionHandler == null) {
            synchronized (CatchExceptionHandler.class) {
                if (cauchExceptionHandler == null) {
                    cauchExceptionHandler = new CatchExceptionHandler();
                }
            }
        }
        return  cauchExceptionHandler;
    }

    //在Application开始时调用
    public void setDefaultUnCachExceptionHandler() {
        //设置应用默认的全局捕获异常器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable throwable) {
        Log.d("Dong", throwable.getMessage()); //异常信息

        Process.killProcess(Process.myPid());
        //关闭虚拟机，释放所有内存
        System.exit(0);
    }
}
