package android.sgz.com.utils;

import android.app.Activity;
import android.sgz.com.activity.LoginActivity;
import android.sgz.com.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by KevinLi on 2016/1/25.
 */
public class AppManager {
    private static AppManager instance = null;
    private ArrayList<Activity> activities;
    private Activity mainUIActivity;
    private AppManager() {
        activities = new ArrayList<Activity>();
    }

    public static synchronized AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public Activity getCurrActivity() {
        if (activities.size() > 0) {
            return activities.get(0);
        } else {
            return mainUIActivity;
        }
    }

    public Activity getLastActivity() {
        if (activities.size() > 0) {
            return activities.get(activities.size() - 1);
        } else {
            return mainUIActivity;
        }
    }

    /**
     * 获取上一个activity
     *
     * @return
     */
    public Activity getPreviousActivity() {
        if (activities.size() > 1) {
            return activities.get(1);
        } else {
            return mainUIActivity;
        }
    }

    public void PushActivity(Activity activity) {
        if (activity instanceof MainActivity) {
            this.mainUIActivity = activity;
        }
        if(!activities.contains(activity)){
            activities.add(activity);
        }
    }

    public void PopActivity() {
        if (activities.size() > 0) {
            activities.remove(activities.size() - 1);
            activities.add(mainUIActivity);
        }
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**跳转关闭除首页外的其他程序*/
    public void finishOtherActivity(){
        ArrayList<Activity> activitiesTemp = new ArrayList<Activity>(activities);
        for(Activity activity : activitiesTemp){
            if(activity instanceof MainActivity){
                continue;
            }
            if(null != activity){
                activity.finish();
            }
        }
    }

    /**跳转关闭除登录界面其他程序*/
    public void finishLoginOtherActivity(){
        ArrayList<Activity> activitiesTemp = new ArrayList<Activity>(activities);
        for(Activity activity : activitiesTemp){
            if(activity instanceof LoginActivity){
                continue;
            }
            if(null != activity){
                activity.finish();
            }
        }
    }


    /**退出程序*/
    public void finishActivity(){
        for(Activity activity : activities){
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    //拒绝维修订单页面
    public void finishMaintainActivity() {
        ArrayList<Activity> activitiesTemp = new ArrayList<Activity>(activities);
        for (Activity activity : activitiesTemp) {
            finishActivity();
        }
    }
}
