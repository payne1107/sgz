package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/16.
 */

public class TopInfoBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":null,"userid":null,"name":null,"categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":4,"workfriends":2,"projectcount":2,"income":1465,"paymentsalary":null,"withdrawsalary":null}
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
         * id : null
         * userid : null
         * name : null
         * categoryid : null
         * regulatoryagency : null
         * headman : null
         * mobile : null
         * address : null
         * clockrange : null
         * lng : null
         * lat : null
         * createtime : null
         * starttime : null
         * startworktime : null
         * endworktime : null
         * status : null
         * worknum : null
         * merchantid : null
         * categoryname : null
         * merchantname : null
         * salary : null
         * addsalary : null
         * allowance : null
         * addtime : null
         * workdays : 4
         * workfriends : 2
         * projectcount : 2
         * income : 1465
         * paymentsalary : null
         * withdrawsalary : null
         */

        private Object id;
        private Object userid;
        private Object name;
        private Object categoryid;
        private Object regulatoryagency;
        private Object headman;
        private Object mobile;
        private Object address;
        private Object clockrange;
        private Object lng;
        private Object lat;
        private Object createtime;
        private Object starttime;
        private Object startworktime;
        private Object endworktime;
        private Object status;
        private Object worknum;
        private Object merchantid;
        private Object categoryname;
        private Object merchantname;
        private Object salary;
        private Object addsalary;
        private Object allowance;
        private Object addtime;
        private int workdays;
        private int workfriends;
        private int projectcount;
        private double income;
        private Object paymentsalary;
        private Object withdrawsalary;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
            this.userid = userid;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public Object getHeadman() {
            return headman;
        }

        public void setHeadman(Object headman) {
            this.headman = headman;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
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

        public Object getStarttime() {
            return starttime;
        }

        public void setStarttime(Object starttime) {
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

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
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

        public Object getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(Object categoryname) {
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

        public int getWorkdays() {
            return workdays;
        }

        public void setWorkdays(int workdays) {
            this.workdays = workdays;
        }

        public int getWorkfriends() {
            return workfriends;
        }

        public void setWorkfriends(int workfriends) {
            this.workfriends = workfriends;
        }

        public int getProjectcount() {
            return projectcount;
        }

        public void setProjectcount(int projectcount) {
            this.projectcount = projectcount;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
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
