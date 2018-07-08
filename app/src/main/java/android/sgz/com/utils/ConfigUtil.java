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
    //1.我的工单2.我发起的工单 3.显示提现按钮
    public static final String EXTRA_SET_DEFAULT_ORDER_KEY = "extra_set_default_order_key";
    //eventbus 事件类型
    public static final int EVENT_TYPE_CODE_ONE = 1;

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
    /****
     * 保存银行卡信息
     */
    public static final String SAVE_BANK_INFO_URL = MyApplication.REQUEST_URL + "/salary/saveBankCard";
    public static final int SAVE_BANK_INFO_URL_ACTION = 18;
    /****
     * 获取已经绑定的银行卡信息
     */
    public static final String QUERY_BIND_BANK_CARD_INFO_URL = MyApplication.REQUEST_URL + "salary/getAllBankCard";
    public static final int QUERY_BIND_BANK_CARD_INFO_URL_ACTION = 19;
    /****
     * 申请提现
     */
    public static final String APPLY_CASH_URL = MyApplication.REQUEST_URL + "salary/toApplyCash";
    public static final int APPLY_CASH_URL_ACTION = 20;
    /****
     * 获取当前用户可以观看的视频
     */
    public static final String QUERY_ALL_VIDEO_URL = MyApplication.REQUEST_URL + "course/getAllCourse";
    public static final int QUERY_ALL_VIDEO_URL_ACTION = 21;
    /****
     * 获取当前用户所有工单 & 废弃接口
     */
    public static final String QUERY_ALL_PROJECTS_ORDER_URL = MyApplication.REQUEST_URL + "project/getProjectLists";
    public static final int QUERY_ALL_PROJECTS_ORDER_URL_ACTION = 22;
    /***
     * 主页上信息获取
     */
    public static final String QUERY_INDEX_DATA_URL = MyApplication.REQUEST_URL + "index/getIndexData";
    public static final int QUERY_INDEX_DATA_URL_ACTION = 23;
    /****
     * 获取标书列表
     */
    public static final String QUERY_TENDER_LIST_URL = MyApplication.REQUEST_URL + "tender/getTenderList";
    public static final int QUERY_TENDER_LIST_URL_ACTION = 24;
    /****
     * 获取好友列表
     */
    public static final String QUERY_WORK_FRIEDNS_LIST_URL = MyApplication.REQUEST_URL + "index/getMyWorkfriends";
    public static final int QUERY_WORK_FRIEDNS_LIST_URL_ACTION = 25;

    /****
     * 注册发送验证码
     */
    public static final String QUERY_REGISTER_CODE_URL = MyApplication.REQUEST_URL + "user/showvalidphone";
    public static final int QUERY_REGISTER_CODE_URL_ACTION = 26;
    /***
     * 获取其他验证码
     */
    public static final String QUERY_OTHER_CODE_URL = MyApplication.REQUEST_URL + "user/showValidphoneAndSendCode";
    public static final int QUERY_OTHER_CODE_URL_ACTION = 27;

    /***
     * 加班列表
     */
    public static final String QUERY_EXTRA_WORK_LIST_URL = MyApplication.REQUEST_URL + "extrawork/getMyExtraworkList";
    public static final int QUERY_EXTRA_WORK_LIST_URL_ACTION = 28;
    /****
     * 注册接口
     */
    public static final String REGISTER_URL = MyApplication.REQUEST_URL + "reg/register";
    public static final int REGISTER_URL_ACTION = 29;
    /****
     * 找回密码
     */
    public static final String REMBER_PASSWORD_URL = MyApplication.REQUEST_URL + "set/setPassword";
    public static final int REMBER_PASSWORD_URL_ACTION = 30;
    /****
     * 工友动态
     */
    public static final String QUERY_FRIEND_DYNAMIC_URL = MyApplication.REQUEST_URL + "index/getFriendDynamic";
    public static final int QUERY_FRIEND_DYNAMIC_URL_ACTION = 31;
    /****
     * 需要我审批的加班
     */
    public static final String QUERY_TO_APPROVE_EXTRAWORK_LIST_URL = MyApplication.REQUEST_URL + "extrawork/getToApproveExtraworkList";
    public static final int QUERY_TO_APPROVE_EXTRAWORK_LIST_URL_ACTION = 32;
    /****
     * 提交加班申请
     */
    public static final String POST_APPLY_EXTRAWORK_RECORD_URL = MyApplication.REQUEST_URL + "extrawork/toApplyExtraworkrecord";
    public static final int POST_APPLY_EXTRAWORK_RECORD_URL_ACTION = 33;
    /****
     * 未审批前删除加班申请
     */
    public static final String DELETE_EXTRA_WORK_RECORD_URL = MyApplication.REQUEST_URL + "extrawork/deleteExtraworkrecord";
    public static final int DELETE_EXTRA_WORK_RECORD_URL_ACTION = 34;
    /****
     * 审批某个加班
     */
    public static final String APPROVE_EXTRA_WORK_URL = MyApplication.REQUEST_URL + "extrawork/toApproveExtrawork";
    public static final int APPROVE_EXTRA_WORK_URL_ACTION = 35;
    /****
     * 刷新token
     */
    public static final String REFRESH_TOKEN_URL = MyApplication.REQUEST_URL + "user/refresh";
    public static final int REFRESH_TOKEN_URL_ACTION = 36;
    /****
     * 获取监管单位
     */
    public static final String QUERY_MY_COMPANY_URL = MyApplication.REQUEST_URL + "project/getMyCompany";
    public static final int QUERY_MY_COMPANY_URL_ACTION = 37;
    /***
     * 新增工单
     */
    public static final String ADD_PROJECT_ORDER_URL = MyApplication.REQUEST_URL + "project/addProject";
    public static final int ADD_PROJECT_ORDER_URL_ACTION = 38;
    /***
     * 获取首页默认工单
     */
    public static final String QUERY_DEFAULT_PROJECT_URL = MyApplication.REQUEST_URL + "project/queryDefaultProject";
    public static final int QUERY_DEFAULT_PROJECT_URL_ACTION = 39;
    /****
     * 获取打卡按钮状态
     */
    public static final String QUERY_WORK_STATUS_URL = MyApplication.REQUEST_URL + "project/queryWorkStatus";
    public static final int QUERY_WORK_STATUS_URL_ACTION = 40;
    /***
     * 获取当天打卡记录 废弃接口
     */
    public static final String QUERY_DAY_WORK_RECORD_URL = MyApplication.REQUEST_URL + "project/queryWorkRecord";
    public static final int QUERY_DAY_WORK_RECORD_URL_ACTION = 41;
    /****
     * 打卡
     */
    public static final String ADD_WORK_RECORD_URL = MyApplication.REQUEST_URL + "project/addWorkRecord";
    public static final int ADD_WORK_RECORD_URL_ACTION = 42;
    /***
     * 设置默认工单
     */
    public static final String SET_DEFAUTL_PROJECT_ORDER_URL = MyApplication.REQUEST_URL + "project/setDefaultProject";
    public static final int SET_DEFAUTL_PROJECT_ORDER_URL_ACTION = 43;
    /****
     * 我的财务 获取当前账户余额
     */
    public static final String QUERY_MY_BALANCE_URL = MyApplication.REQUEST_URL + "pay/getMyBalance";
    public static final int QUERY_MY_BALANCE_URL_ACTION = 44;
    /****
     * 获取我创建的工单的支出情况列表
     */
    public static final String QUERY_PAYMENT_BY_PROJECT_ORDER_URL = MyApplication.REQUEST_URL + "pay/getPaymentByProject";
    public static final int QUERY_PAYMENT_BY_PROJECT_ORDER_URL_ACTION = 45;
    /****
     * 具体某个工单的花名册 projectid
     * 具体工单中具体某个工人的支出情况  projectid  & id
     */
    public static final String QUERY_PROJECT_WORK_SALARY_URL = MyApplication.REQUEST_URL + "pay/getProjectWorkSalary";
    public static final int QUERY_PROJECT_WORK_SALARY_URL_ACTION = 47;
    /****
     * 发布动态
     */
    public static final String RELEAST_DYNAMIC_URL = MyApplication.REQUEST_URL + "dynamic/saveDynamic";
    public static final int RELEAST_DYNAMIC_URL_ACTION = 48;
    /***
     * 获取主页动态
     */
    public static final String QUERY_DYNMAIC_LIST_URL = MyApplication.REQUEST_URL + "dynamic/dynamicList";
    public static final int QUERY_DYNMAIC_LIST_URL_ACTION = 49;
    /***
     * 消息推送接口
     */
    public static final String QUERY_MY_PUSH_MESSAGE_ULR = MyApplication.REQUEST_URL + "pushMessage/queryMyPushMessage";
    public static final int QUERY_MY_PUSH_MESSAGE_ULR_ACTION = 50;
    /****
     * 修改手机号
     */
    public static final String UPDATE_MOBILE_NUMBER_URL = MyApplication.REQUEST_URL + "personal/saveMobile";
    public static final int UPDATE_MOBILE_NUMBER_URL_ACTION = 51;
    /***
     * 一键发放工资
     */
    public static final String TO_PAY_ALL_SALARY_URL = MyApplication.REQUEST_URL + "pay/toPayForAll";
    public static final int TO_PAY_ALL_SALARY_URL_ACTION = 52;
    /****
     * 单个发工资
     */
    public static final String TO_PAY_FOR_ONE_SALARY_URL = MyApplication.REQUEST_URL + "pay/toPayForOne";
    public static final int TO_PAY_FOR_ONE_SALARY_URL_ACTION = 53;
    /****
     * 充值明细  page   type=3
     * 支出明细  page   type=1
     */
    public static final String QUERY_USER_FLOW_LIST_URL = MyApplication.REQUEST_URL + "pay/getUserFlowList";
    public static final int QUERY_USER_FLOW_LIST_URL_ACTION = 54;
    /****
     *根据日期获取打卡信息
     */
    public static final String QUERY_WORK_RECORD_BY_TIME_URL = MyApplication.REQUEST_URL + "project/queryWorkRecordByTime";
    public static final int QUERY_WORK_RECORD_BY_TIME_URL_ACTION = 55;
    /***
     * 获取我创建的工单
     */
    public static final String QUERY_MY_PROJECT_ORDER_URL = MyApplication.REQUEST_URL + "project/getMyProject";
    public static final int QUERY_MY_PROJECT_ORDER_URL_ACTION = 56;
    /****
     * 获取我参与的工单
     */
    public static final String QUERY_MY_JOIN_PROJECT_ORDER_URL = MyApplication.REQUEST_URL + "project/getMyJoinProject";
    public static final int QUERY_MY_JOIN_PROJECT_ORDER_URL_ACTION = 57;
    /****
     * 上传头像
     */
    public static final String UPLOAD_AVATAR_URL = MyApplication.REQUEST_URL + "personal/savePhoto";
    public static final int UPLOAD_AVATAR_URL_ACTION = 58;

    /****
     * 根据月份查询打卡情况
     */
    public static final String QUERY_RECORD_BY_MONTH_URL = MyApplication.REQUEST_URL + "statistic/clockRecord";
    public static final int QUERY_RECORD_BY_MONTH_URL_ACTION = 59;
    /****
     * 补卡申请
     */
    public static final String POST_MAKE_CARD_APPLY_URL = MyApplication.REQUEST_URL + "apply/apply";
    public static final int POST_MAKE_CARD_APPLY_URL_ACTION = 60;
    /****
     * 补卡申请列表  page  type=1  我的审核列表page  type=2
     */
    public static final String QUERY_MAKE_CARD_APPLY_LIST_URL = MyApplication.REQUEST_URL + "apply/applyList";
    public static final int QUERY_MAKE_CARD_APPLY_LIST_URL_ACTION = 61;
    /***
     * 审核补卡申请
     */
    public static final String CHECK_MAKE_CARD_APPLY_URL = MyApplication.REQUEST_URL + "apply/handle";
    public static final int CHECK_MAKE_CARD_APPLY_URL_ACTION = 62;
    /****
     * 获取所有工单接口
     */
    public static final String QUERY_PROJECT_LISTS_URL = MyApplication.REQUEST_URL + "project/getProjectLists";
    public static final int QUERY_PROJECT_LISTS_URL_ACTION = 63;
    /****
     * 工单申请列表  暂时废弃
     */
    public static final String QUERY_PROJECT_ORDER_APPLY_LIST_URL = MyApplication.REQUEST_URL + "projectApply/applyList";
    public static final int QUERY_PROJECT_ORDER_APPLY_LIST_URL_ACTION = 64;
    /****
     * 申请加入工单  暂时废弃
     */
    public static final String APPLY_IN_PROJECT_ORDER_URL = MyApplication.REQUEST_URL + "projectApply/apply";
    public static final int APPLY_IN_PROJECT_ORDER_URL_ACTION = 65;
    /****
     * 审核加入工单的申请  暂时废弃
     */
    public static final String CHECK_IN_PROJECT_ORDER_APPLY_URL = MyApplication.REQUEST_URL + "projectApply/handle";
    public static final int CHECK_IN_PROJECT_ORDER_APPLY_URL_ACTION = 66;
    /****
     * 改变工单状态
     */
    public static final String CHANGE_PROJECT_ORDER_STATUS_URL = MyApplication.REQUEST_URL + "project/toChangeProjectStatus";
    public static final int CHANGE_PROJECT_ORDER_STATUS_URL_ACTION = 67;
    /****
     * 给工单添加好友
     */
    public static final String ADD_PROJECT_ORDER_WORK_URL = MyApplication.REQUEST_URL + "project/toAddProjectWork";
    public static final int ADD_PROJECT_ORDER_WORK_URL_ACTION = 68;
    /****
     * 获取工程类别
     */
    public static final String QUERY_PROJECT_CATEGORY_URL = MyApplication.REQUEST_URL + "project/getProjectCategory";
    public static final int QUERY_PROJECT_CATEGORY_URL_ACTION = 69;
    /****
     * 获取所有用户
     */
    public static final String QUERY_ALL_USER_URL = MyApplication.REQUEST_URL + "user/getAllUser";
    public static final int QUERY_ALL_USER_URL_ACTION = 70;
    /****
     * 获取工单人员
     */
    public static final String QUERY_PROJECT_WORK_USER_URL = MyApplication.REQUEST_URL + "project/getProjectWorkUser";
    public static final int QUERY_PROJECT_WORK_USER_URL_ACTION = 71;
    /****
     * 删除工单好友
     */
    public static final String DELETE_ORDER_USER_URL = MyApplication.REQUEST_URL + "project/toReduceProjectWork";
    public static final int DELETE_ORDER_USER_URL_ACTION = 72;
    /****
     * 工单打卡记录
     */
    public static final String QUERY_WORK_RECORD_URL = MyApplication.REQUEST_URL + "workRecord/getWorkRecord";
    public static final int QUERY_WORK_RECORD_URL_ACTION = 73;
    /****
     * 修改工人工资
     */
    public static final String EDIT_PROJECT_WORK_SALARY_URL = MyApplication.REQUEST_URL + "project/editProjectWorkSalary";
    public static final int EDIT_PROJECT_WORK_SALARY_URL_ACTION = 74;

}
