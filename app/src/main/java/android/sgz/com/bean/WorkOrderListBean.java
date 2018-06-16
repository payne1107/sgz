package android.sgz.com.bean;

import java.util.List;

/**
 * Created by 92457 on 2018/6/16.
 */

public class WorkOrderListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":"王兵兵","mobile":null,"address":"高河东路绿地蓝海C座","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1530428140000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null},{"id":2,"userid":2,"name":"绿怡居改造二期","categoryid":null,"regulatoryagency":null,"headman":"李响","mobile":null,"address":"石台路绿怡居西区","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1533106819000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null},{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":"王兵兵","mobile":null,"address":"高河东路绿地蓝海C座","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1530428140000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null},{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":"王兵兵","mobile":null,"address":"高河东路绿地蓝海C座","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1530428140000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null}]}
     * exception : false
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private DataBean data;
    private boolean exception;

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

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public static class DataBean {
        /**
         * curPage : 1
         * coutpage : 1
         * pageSize : 15
         * list : [{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":"王兵兵","mobile":null,"address":"高河东路绿地蓝海C座","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1530428140000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null},{"id":2,"userid":2,"name":"绿怡居改造二期","categoryid":null,"regulatoryagency":null,"headman":"李响","mobile":null,"address":"石台路绿怡居西区","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1533106819000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null},{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":"王兵兵","mobile":null,"address":"高河东路绿地蓝海C座","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1530428140000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null},{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":"王兵兵","mobile":null,"address":"高河东路绿地蓝海C座","clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":1530428140000,"startworktime":null,"endworktime":null,"status":0,"worknum":null,"merchantid":null,"categoryname":"民建","merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":null,"workfriends":null,"projectcount":null,"income":null,"paymentsalary":null,"withdrawsalary":null}]
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
             * id : 1
             * userid : 1
             * name : 绿怡居改造工程
             * categoryid : null
             * regulatoryagency : null
             * headman : 王兵兵
             * mobile : null
             * address : 高河东路绿地蓝海C座
             * clockrange : null
             * lng : null
             * lat : null
             * createtime : null
             * starttime : 1530428140000
             * startworktime : null
             * endworktime : null
             * status : 0
             * worknum : null
             * merchantid : null
             * categoryname : 民建
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
             */

            private int id;
            private int userid;
            private String name;
            private Object categoryid;
            private Object regulatoryagency;
            private String headman;
            private Object mobile;
            private String address;
            private Object clockrange;
            private Object lng;
            private Object lat;
            private Object createtime;
            private long starttime;
            private Object startworktime;
            private Object endworktime;
            private int status;
            private Object worknum;
            private Object merchantid;
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

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
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

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }

            public long getStarttime() {
                return starttime;
            }

            public void setStarttime(long starttime) {
                this.starttime = starttime;
            }

            public Object getStartworktime() {
                return startworktime;
            }

            public void setStartworktime(Object startworktime) {
                this.startworktime = startworktime;
            }

            public Object getEndworktime() {
                return endworktime;
            }

            public void setEndworktime(Object endworktime) {
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
        }
    }
}
