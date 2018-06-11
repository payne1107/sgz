package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/11.
 */

public class ProjectIncomeBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":null,"userid":null,"name":null,"categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":null,"addsalary":null,"allowance":null,"addtime":null,"workdays":0}
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
         * workdays : 0
         */

        private String id;
        private String userid;
        private String name;
        private String categoryid;
        private String regulatoryagency;
        private String headman;
        private String mobile;
        private String address;
        private String lng;
        private String lat;
        private String createtime;
        private String starttime;
        private String startworktime;
        private String endworktime;
        private String status;
        private String worknum;
        private String merchantid;
        private String categoryname;
        private String merchantname;
        private String salary;
        private String addsalary;
        private String allowance;
        private String addtime;
        private int workdays;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }

        public String getRegulatoryagency() {
            return regulatoryagency;
        }

        public void setRegulatoryagency(String regulatoryagency) {
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

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWorknum() {
            return worknum;
        }

        public void setWorknum(String worknum) {
            this.worknum = worknum;
        }

        public String getMerchantid() {
            return merchantid;
        }

        public void setMerchantid(String merchantid) {
            this.merchantid = merchantid;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }

        public String getMerchantname() {
            return merchantname;
        }

        public void setMerchantname(String merchantname) {
            this.merchantname = merchantname;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getAddsalary() {
            return addsalary;
        }

        public void setAddsalary(String addsalary) {
            this.addsalary = addsalary;
        }

        public String getAllowance() {
            return allowance;
        }

        public void setAllowance(String allowance) {
            this.allowance = allowance;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getWorkdays() {
            return workdays;
        }

        public void setWorkdays(int workdays) {
            this.workdays = workdays;
        }
    }
}
