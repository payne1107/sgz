package android.sgz.com.utils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.text.TextUtils;

/**共享存储, 工具类
 * <br/>1.1... 存入boolean {@link #putBoolean(Context context, String key, boolean value)}
 * <br/>1.2... 存入int {@link #putInt(Context, String, int)}
 * <br/>1.3... 存入float {@link #putFloat(Context context, String key, float value)}
 * <br/>1.4... 存入long {@link #putLong(Context context, String key, long value)}
 * <br/>1.5... 存入String {@link #putString(Context, String, String)}
 * <br/>2.1... 取出boolean {@link #getBoolean(Context context, String key)}
 * <br/>2.1... 取出bolean {@link #getBoolean(Context context, String key, boolean defaultValue)}
 * <br/>2.2... 取出int {@link #getInt(Context context, String key)}
 * <br/>2.2... 取出int {@link #getInt(Context context, String key, int defaultValue)}
 * <br/>2.3... 取出float {@link #getFloat(Context context, String key)}
 * <br/>2.3... 取出float {@link #getFloat(Context context, String key, float defaultValue)}
 * <br/>2.4... 取出long {@link #getLong(Context context, String key)}
 * <br/>2.4... 取出long {@link #getLong(Context context, String key, long defaultValue)}
 * <br/>2.5... 取出String {@link #getString(Context, String)}
 * <br/>2.5... 取出String {@link #getString(Context, String, String)}
 * <br/>3.1... 移除String {@link #remove(Context context, String key)}
 * <br/>2.5... 移除String {@link #}
 * <br/>2.5... 移除String {@link #}
 * <br/>2.5... 移除String {@link #}
 * <br/>2.5... 移除String {@link #}
 * <br/>2.5... 移除String {@link #}
 * */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SPUtil
{
    private static String FILE_NAME = "CoDrivermyConfig";
    /**存入String数据
     * <br/>失败, 返回false
     * @param key 键, !null, !""
     * @param value 值
     */
    public static boolean putString(Context context, String key, String value)
    {
        if(TextUtils.isEmpty(key) || TextUtils.isEmpty(value)){
            return false;
        }
        if (null == context) {
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**取出String, 失败, 返回null
     * @param key 键, !null, !""
     */
    public static String getString(Context context, String key) {
        if(TextUtils.isEmpty(key)){
            return null;
        }
        return getString(context, key, null);
    }

    /**取出String, 失败, 返回 defaultValue
     * @param key 键, !null, !""*/
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return settings.getString(key, defaultValue);
    }

    /**存入int, 失败, 返回, false
     * @param key 键, !null, !""
     */
    public static boolean putInt(Context context, String key, int value) {
        if(TextUtils.isEmpty(key)){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }
    /**取出int,  失败, 返回,  -2^31
     * @param key 键, !null, !""
     */
    public static int getInt(Context context, String key) {
        if(TextUtils.isEmpty(key)){
            return Integer.MIN_VALUE;
        }
        return getInt(context, key, -1);
    }
    /**取出int,  失败, 返回,  defaultValue
     * @param key 键, !null, !""
     */
    public static int getInt(Context context, String key, int defaultValue) {
        if(TextUtils.isEmpty(key)){
            return defaultValue;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return settings.getInt(key, defaultValue);
    }
    /**存入long,  失败, 返回,  false
     * @param key 键, !null, !""
     */
    public static boolean putLong(Context context, String key, long value) {
        if(TextUtils.isEmpty(key)){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }
    /**取出long,  失败, 返回,   - 2^63
     * @param key 键, !null, !""
     */
    public static long getLong(Context context, String key) {
        if(TextUtils.isEmpty(key)){
            return Long.MIN_VALUE;
        }
        return getLong(context, key, Long.MIN_VALUE);
    }
    /**取出long,  失败, 返回,   defaultValue
     * @param key 键, !null, !""
     */
    public static long getLong(Context context, String key, long defaultValue) {
        if(TextUtils.isEmpty(key)){
            return defaultValue;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return settings.getLong(key, defaultValue);
    }
    /**存入float,  失败, 返回,  false
     * @param key 键, !null, !""
     */
    public static boolean putFloat(Context context, String key, float value) {
        if(TextUtils.isEmpty(key)){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }
    /**取出float,  失败, 返回,   2^-149
     * @param key 键, !null, !""
     */
    public static float getFloat(Context context, String key) {
        if(TextUtils.isEmpty(key)){
            return Float.MIN_VALUE;
        }
        return getFloat(context, key, Float.MIN_VALUE);
    }
    /**取出float,  失败, 返回,   defaultValue
     * @param key 键, !null, !""
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        if(TextUtils.isEmpty(key)){
            return defaultValue;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return settings.getFloat(key, defaultValue);
    }
    /**存入boolean,  失败, 返回,  false
     * @param key 键, !null, !""
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        if(TextUtils.isEmpty(key)){
            return false;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }
    /**取出boolean,  失败, 返回,   false
     * @param key 键, !null, !""
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }
    /**取出boolean,  失败, 返回,   defaultValue
     * @param key 键, !null, !""
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        if(TextUtils.isEmpty(key)){
            return defaultValue;
        }
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return settings.getBoolean(key, defaultValue);
    }
    /**移除键值对, 失败, 返回false
     * @param key 键, !null, !""
     */
    public static boolean remove(Context context, String key){
        if(TextUtils.isEmpty(key)){
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        Editor editor = sp.edit();
        return editor.remove(key).commit();
    }


}
