package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/7/3.
 */

public class AllPorjectOrderBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"vip":false,"list":{"curPage":1,"coutpage":10,"pageSize":15,"list":[{"id":240,"userid":593,"name":"至精运检\u2015\u2015华能营口电厂维护项目部","categoryid":null,"regulatoryagency":null,"headman":"宋万龙","mobile":"17356996260","address":"辽宁省营口市辽宁省营口市鲅鱼圈区北二路","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 15:28","starttime":"2018-09-21","startworktime":"08:00","endworktime":"16:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null},{"id":239,"userid":442,"name":"晋江热电主机维护项目部","categoryid":null,"regulatoryagency":null,"headman":"卢震华","mobile":"18558975086","address":"福建省泉州市福建省泉州市晋江市建成路靠近神华集团晋江热电公司","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 14:23","starttime":"2018-09-20","startworktime":"08:00","endworktime":"17:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null},{"id":238,"userid":693,"name":"致精运检华能营口维护项目部","categoryid":null,"regulatoryagency":null,"headman":"赵桂学","mobile":"15134228120","address":"辽宁省营口市辽宁省营口市鲅鱼圈区北二路靠近墩台山公园","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 13:27","starttime":"2018-09-21","startworktime":"08:00","endworktime":"16:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null}]}}
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * vip : false
         * list : {"curPage":1,"coutpage":10,"pageSize":15,"list":[{"id":240,"userid":593,"name":"至精运检\u2015\u2015华能营口电厂维护项目部","categoryid":null,"regulatoryagency":null,"headman":"宋万龙","mobile":"17356996260","address":"辽宁省营口市辽宁省营口市鲅鱼圈区北二路","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 15:28","starttime":"2018-09-21","startworktime":"08:00","endworktime":"16:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null},{"id":239,"userid":442,"name":"晋江热电主机维护项目部","categoryid":null,"regulatoryagency":null,"headman":"卢震华","mobile":"18558975086","address":"福建省泉州市福建省泉州市晋江市建成路靠近神华集团晋江热电公司","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 14:23","starttime":"2018-09-20","startworktime":"08:00","endworktime":"17:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null},{"id":238,"userid":693,"name":"致精运检华能营口维护项目部","categoryid":null,"regulatoryagency":null,"headman":"赵桂学","mobile":"15134228120","address":"辽宁省营口市辽宁省营口市鲅鱼圈区北二路靠近墩台山公园","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 13:27","starttime":"2018-09-21","startworktime":"08:00","endworktime":"16:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null}]}
         */

        private boolean vip;
        private ListBeanX list;

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * curPage : 1
             * coutpage : 10
             * pageSize : 15
             * list : [{"id":240,"userid":593,"name":"至精运检\u2015\u2015华能营口电厂维护项目部","categoryid":null,"regulatoryagency":null,"headman":"宋万龙","mobile":"17356996260","address":"辽宁省营口市辽宁省营口市鲅鱼圈区北二路","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 15:28","starttime":"2018-09-21","startworktime":"08:00","endworktime":"16:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null},{"id":239,"userid":442,"name":"晋江热电主机维护项目部","categoryid":null,"regulatoryagency":null,"headman":"卢震华","mobile":"18558975086","address":"福建省泉州市福建省泉州市晋江市建成路靠近神华集团晋江热电公司","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 14:23","starttime":"2018-09-20","startworktime":"08:00","endworktime":"17:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null},{"id":238,"userid":693,"name":"致精运检华能营口维护项目部","categoryid":null,"regulatoryagency":null,"headman":"赵桂学","mobile":"15134228120","address":"辽宁省营口市辽宁省营口市鲅鱼圈区北二路靠近墩台山公园","clockrange":null,"lng":null,"lat":null,"createtime":"2018-09-16 13:27","starttime":"2018-09-21","startworktime":"08:00","endworktime":"16:30","status":1,"worknum":null,"merchantid":null,"ifend":0,"endtime":null,"categoryname":"其他","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null,"realname":null}]
             */

            private int curPage;
            private int coutpage;
            private int pageSize;
            private List<ListBean> list;

            public int getCurPage() {
                return curPage;
            }

            public void setCurPage(int curPage) {
                this.curPage = curPage;
            }

            public int getCoutpage() {
                return coutpage;
            }

            public void setCoutpage(int coutpage) {
                this.coutpage = coutpage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 240
                 * userid : 593
                 * name : 至精运检――华能营口电厂维护项目部
                 * categoryid : null
                 * regulatoryagency : null
                 * headman : 宋万龙
                 * mobile : 17356996260
                 * address : 辽宁省营口市辽宁省营口市鲅鱼圈区北二路
                 * clockrange : null
                 * lng : null
                 * lat : null
                 * createtime : 2018-09-16 15:28
                 * starttime : 2018-09-21
                 * startworktime : 08:00
                 * endworktime : 16:30
                 * status : 1
                 * worknum : null
                 * merchantid : null
                 * ifend : 0
                 * endtime : null
                 * categoryname : 其他
                 * merchantname : null
                 * salary : null
                 * addsalary : null
                 * allowance : null
                 * addtime : null
                 * workdays : null
                 * workfriends : null
                 * projectcount : null
                 * income : null
                 * paymentsalary : null
                 * withdrawsalary : null
                 * realname : null
                 */

                private int id;
                private int userid;
                private String name;
                private Object categoryid;
                private Object regulatoryagency;
                private String headman;
                private String mobile;
                private String address;
                private Object clockrange;
                private Object lng;
                private Object lat;
                private String createtime;
                private String starttime;
                private String startworktime;
                private String endworktime;
                private int status;
                private Object worknum;
                private Object merchantid;
                private int ifend;
                private Object endtime;
                private String categoryname;
                private Object merchantname;
                private Object salary;
                private Object addsalary;
                private Object allowance;
                private Object addtime;
                private Object workdays;
                private Object workfriends;
                private Object projectcount;
                private Object income;
                private Object paymentsalary;
                private Object withdrawsalary;
                private Object realname;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getCategoryid() {
                    return categoryid;
                }

                public void setCategoryid(Object categoryid) {
                    this.categoryid = categoryid;
                }

                public Object getRegulatoryagency() {
                    return regulatoryagency;
                }

                public void setRegulatoryagency(Object regulatoryagency) {
                    this.regulatoryagency = regulatoryagency;
                }

                public String getHeadman() {
                    return headman;
                }

                public void setHeadman(String headman) {
                    this.headman = headman;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public Object getClockrange() {
                    return clockrange;
                }

                public void setClockrange(Object clockrange) {
                    this.clockrange = clockrange;
                }

                public Object getLng() {
                    return lng;
                }

                public void setLng(Object lng) {
                    this.lng = lng;
                }

                public Object getLat() {
                    return lat;
                }

                public void setLat(Object lat) {
                    this.lat = lat;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }

                public String getStartworktime() {
                    return startworktime;
                }

                public void setStartworktime(String startworktime) {
                    this.startworktime = startworktime;
                }

                public String getEndworktime() {
                    return endworktime;
                }

                public void setEndworktime(String endworktime) {
                    this.endworktime = endworktime;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public Object getWorknum() {
                    return worknum;
                }

                public void setWorknum(Object worknum) {
                    this.worknum = worknum;
                }

                public Object getMerchantid() {
                    return merchantid;
                }

                public void setMerchantid(Object merchantid) {
                    this.merchantid = merchantid;
                }

                public int getIfend() {
                    return ifend;
                }

                public void setIfend(int ifend) {
                    this.ifend = ifend;
                }

                public Object getEndtime() {
                    return endtime;
                }

                public void setEndtime(Object endtime) {
                    this.endtime = endtime;
                }

                public String getCategoryname() {
                    return categoryname;
                }

                public void setCategoryname(String categoryname) {
                    this.categoryname = categoryname;
                }

                public Object getMerchantname() {
                    return merchantname;
                }

                public void setMerchantname(Object merchantname) {
                    this.merchantname = merchantname;
                }

                public Object getSalary() {
                    return salary;
                }

                public void setSalary(Object salary) {
                    this.salary = salary;
                }

                public Object getAddsalary() {
                    return addsalary;
                }

                public void setAddsalary(Object addsalary) {
                    this.addsalary = addsalary;
                }

                public Object getAllowance() {
                    return allowance;
                }

                public void setAllowance(Object allowance) {
                    this.allowance = allowance;
                }

                public Object getAddtime() {
                    return addtime;
                }

                public void setAddtime(Object addtime) {
                    this.addtime = addtime;
                }

                public Object getWorkdays() {
                    return workdays;
                }

                public void setWorkdays(Object workdays) {
                    this.workdays = workdays;
                }

                public Object getWorkfriends() {
                    return workfriends;
                }

                public void setWorkfriends(Object workfriends) {
                    this.workfriends = workfriends;
                }

                public Object getProjectcount() {
                    return projectcount;
                }

                public void setProjectcount(Object projectcount) {
                    this.projectcount = projectcount;
                }

                public Object getIncome() {
                    return income;
                }

                public void setIncome(Object income) {
                    this.income = income;
                }

                public Object getPaymentsalary() {
                    return paymentsalary;
                }

                public void setPaymentsalary(Object paymentsalary) {
                    this.paymentsalary = paymentsalary;
                }

                public Object getWithdrawsalary() {
                    return withdrawsalary;
                }

                public void setWithdrawsalary(Object withdrawsalary) {
                    this.withdrawsalary = withdrawsalary;
                }

                public Object getRealname() {
                    return realname;
                }

                public void setRealname(Object realname) {
                    this.realname = realname;
                }
            }
        }
    }
}
