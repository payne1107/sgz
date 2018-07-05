package android.sgz.com.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WD on 2017-03-08.
 */

public class StringUtils {

    public static boolean isEmpty(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString());
    }
    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    /****
     * @param numberFlag true 生成的是1-9的随机数  | false 生成的是1-9 a-z的随机数
     * @param length  表示生成的数据长度
     * @author Weidong
     * @return 生成的验证码
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }


    /** dp转换px**/
    public static int dip2px(Context context, float dpValue) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } catch (Exception ex) {

        }
        return 0;
    }

    /****
     * 将List集合转成String 并以","号分割
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /****
     * 将String 转成list
     * @param str
     * @return
     */
    public static List<String> stringsToList(String str) {
        String[] strings = str.split(",");
        return  Arrays.asList(strings);
    }

    /***
     * 截取空格前的字符串
     * @param string
     * @return
     */
    public static String stringSpacingToString(String string) {
        if (!isEmpty(string)) {
            return string.substring(0,string.indexOf(" "));
        }
        return "";
    }

    /****
     * 获取/1000 保留两位小数
     * @return
     */
    public static String getFloat(int num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float fl = (float) num / 1000;
        String dist = decimalFormat.format(fl);
        return dist + "km";
    }
    /****
     * 获取/1000 保留两位小数
     * @return
     */
    public static String getFloat(float num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float fl = num / 1000;
        String dist = decimalFormat.format(fl);
        return dist + "km";
    }

    /****
     * 获取/1000 保留四位小数
     * @param num2 被除数
     * @return
     */
    public static String getFloat(int num, int num2) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        float fl = (float) num / num2;
        String dist = decimalFormat.format(fl);
        return dist + "";
    }

    /****
     *  保留几位小数
     * @param num 除数
     * @param num2 被除数
     * @param decimal 保留几位小数
     * @return
     */
    public static String getFloat(int num, int num2, String decimal) {
        DecimalFormat decimalFormat = new DecimalFormat(decimal);
        float fl = (float) num / num2;
        String numValue = decimalFormat.format(fl);
        return numValue + "";
    }

    /******
     * 保留两位小数
     */
    public static String getDouble(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String strNum = decimalFormat.format(num);
        return strNum;
    }


    /****
     * 获取有效期
     * @param endTime  结束时间
     * @return 22 这种格式
     */
    public static String getValidDate(String endTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date beginTime = simpleDateFormat.parse(getCurrentTime()); //当前时间
            Date enTime = simpleDateFormat.parse(endTime);
            long diff = (enTime.getTime() - beginTime.getTime()) / (86400 * 1000);  //返回有效期
            return Long.toString(diff);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*****
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /***
     * 需要给的日期往后推多少天
     * @param d  需要添加的日期
     * @param day 添加的天数
     * @return 添加后的日期
     * @throws ParseException
     */
    public static Date addDate(Date d, long day) throws ParseException {
        long time = d.getTime();
        day = day*24*60*60*1000;
        time+=day;
        return new Date(time);
    }

    /****
     * 获取推迟的天数 转成string
     * @param time 用户给的起止时间
     * @param day 添加的天数
     * @return 日期string   格式：2017-07-06
     */
    public static String getDelayDate(String time, long day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            Date newDate = addDate(date, day);
            String newDa = sdf.format(newDate);
            return newDa;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /***
     * 验证密码是否是纯数字
     * @param txt
     */
    public static boolean verifyPwdIsTrue(String txt) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);
        if (m.matches()) { //纯数字
            return true;
        }
        p= Pattern.compile("[a-zA-Z]");
        m=p.matcher(txt);
        Log.d("Dong", "验证的密码格式正确么？？？？ ");
        if (m.matches()) { //英文字母
            return false;
        }
        p= Pattern.compile("[\u4e00-\u9fa5]");
        m=p.matcher(txt);
        if(m.matches()) { //中文
            return false;
        }
        return false;
    }

    /***
     * 验证密码是否包含字母
     * @param txt
     */
    public static boolean   isContainLetter(String txt) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return txt.matches(regex);
    }

    /****
     * 去除字符串的最后一个字符
     * @param text
     * @return
     */
    public static String deleteLastCharacter(String text) {
        return text.substring(0, text.length() - 1);
    }

    /****
     * 计算两个整数的整数百分比
     * @param num1
     * @param num2
     * @return
     */
    public static int calcuPercent(int num1,int num2) {
        //创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        //保留0位小数
        numberFormat.setMaximumFractionDigits(0);
        String num = numberFormat.format((float) num1 / (float) num2 * 100);
        return Integer.parseInt(num);
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "," + hour + "," + min + "," + sec;
    }

    /****
     * 获取有效期时间 精准到毫秒
     * @param endDate 有效期结束日
     * @return
     */
    public static long getValidityTime(String endDate) {
        //获取当前时间的毫秒值
        long currentTime = System.currentTimeMillis();
        long endTime = strDateToLong(endDate);
        long validTime = endTime - currentTime;
        Log.d("Dong", "currentTime = " + currentTime + " ,, " + endTime +",," +validTime);
        return validTime;
    }

    /***
     * 将字符串转成毫秒数
     * @param strTime
     * @return
     */
    public static long strDateToLong(String strTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(strTime);
            return  date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /****
     * 两个double数相加
     * @param dou1
     * @param dou2
     * @return
     */
    public static Double doubleAdd(double dou1, double dou2) {
        DecimalFormat df  = new DecimalFormat("0.0000");
        BigDecimal b1 = new BigDecimal(df.format(dou1));
        BigDecimal b2 = new BigDecimal(df.format(dou2));
        double doubleValue = b1.add(b2).doubleValue();
        return doubleValue;
    }


    /****
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
