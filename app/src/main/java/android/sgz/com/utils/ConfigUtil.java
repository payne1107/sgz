package android.sgz.com.utils;


import android.sgz.com.application.MyApplication;

/**
 * Created by Administrator on 2017/9/29 0029.
 * 网络地址存放
 */

public class ConfigUtil {
    public static long TenYears8 = 10L * 365 * 1000 * 60 * 60 * 24L * 80;
    public static long TenYears = 10L * 365 * 1000 * 60 * 60 * 24L * 3;
    public static String sessionId;

    /****
     * 登录接口
     */
    public static final String LOGIN_URL = MyApplication.REQUEST_URL + "login";
    public static final int LOGIN_URL_ACTION = 1;
    /****
     * 修改昵称
     */
    public static final String UPDATE_NICK_NAME_URL = MyApplication.REQUEST_URL + "personal/editName";
    public static final int UPDATE_NICK_NAME_URL_ACTION = 2;
    /***
     * 获取所有职业
     */
    public static final String QUERY_ALL_PROFESSION_URL = MyApplication.REQUEST_URL + "personal/getAllProfession";
    public static final int QUERY_ALL_PROFESSION_URL_ACTION = 3;
    /***
     * 保存用户职业
     */
    public static final String SAVE_USER_PROFESSION_URL = MyApplication.REQUEST_URL + "personal/saveProfession";
    public static final int SAVE_USER_PROFESSION_URL_ACTION = 4;
    /****
     * 获取所有职称
     */
    public static final String QUERY_ALL_PROFESSION_LEVEL_URL = MyApplication.REQUEST_URL + "personal/getAllProfessionLevel";
    public static final int QUERY_ALL_PROFESSION_LEVEL_URL_ACTION = 5;
    /****
     * 保存职称信息
     */
    public static final String SAVE_PROFESSION_LEVEL_URL = MyApplication.REQUEST_URL + "personal/saveProfessionLevel";
    public static final int SAVE_PROFESSION_LEVEL_URL_ACTION = 6;
    /***
     * 保存工资
     */
    public static final String SAVE_SALARY_URL = MyApplication.REQUEST_URL + "personal/saveSalary";
    public static final int SAVE_SALARY_URL_ACTION = 7;
    /***
     * 保存城市
     */
    public static final String SAVE_CITY_URL = MyApplication.REQUEST_URL + "personal/saveCity";
    public static final int SAVE_CITY_URL_ACTION = 8;
    /****
     * 保存生日
     */
    public static final String SAVE_BIRTHDAY_URL = MyApplication.REQUEST_URL + "personal/saveBirthday";
    public static final int SAVE_BIRTHDAY_URL_ACTION = 9;
    /****
     * 保存个性签名
     */
    public static final String SAVE_PERSONAILZED_SIGNATURE_URL = MyApplication.REQUEST_URL + "personal/saveSign";
    public static final int SAVE_PERSONAILZED_SIGNATURE_URL_ACTION = 10;
    /***
     * VIP会员页面基本资料获取
     */
    public static final String QUERY_VIP_BASIC_INFO_URL = MyApplication.REQUEST_URL + "personal/getBasicInfo";
    public static final int QUERY_VIP_BASIC_INFO_URL_ACTION = 11;
    /***
     * VIP会员套餐列表获取
     */
    public static final String QUERY_VIP_LIST_URL = MyApplication.REQUEST_URL + "personal/getVips";
    public static final int QUERY_VIP_LIST_URL_ACTION = 12;
    /****
     * 获取所有城市接口
     */
    public static final String QUERY_ALL_CITY_URL = MyApplication.REQUEST_URL + "personal/getAllCity";
    public static final int QUERY_ALL_CITY_URL_ACTION = 13;
    /***
     * 获取总共的工资，津贴，加班费
     */
    public static final String QUERY_PROJECT_INCOM_URL = MyApplication.REQUEST_URL + "salary/getProjectIncome";
    public static final int QUERY_PROJECT_INCOME_URL_ACTION = 14;
    /***
     * 我的工资列表
     */
    public static final String QUERY_PROJECT_SALARY_LIST_URL = MyApplication.REQUEST_URL + "salary/getProjectSalayList";
    public static final int QUERY_PROJECT_SALARY_LIST_URL_ACTION = 15;
    /****
     * 我的具体某个工单的工资
     */
    public static final String QUERY_PROJECT_INCOME_BY_PROJECT_ID_URL = MyApplication.REQUEST_URL + "salary/getMyProjectIncomebyProjectid";
    public static final int QUERY_PROJECT_INCOME_BY_PROJECT_ID_URL_ACTION = 16;
    /****
     * 查询银行信息列表
     */
    public static final String QUERY_BANK_INFO_LIST_URL = MyApplication.REQUEST_URL + "salary/getAllBankCode";
    public static final int QUERY_BANK_INFO_LIST_URL_ACTION = 17;

}
