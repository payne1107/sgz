package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/22.
 */

public class DefaultProjectOrderBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":1,"regulatoryagency":"合肥一建","headman":"王兵兵","mobile":"15155148936","address":"高河东路绿地蓝海C座","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":1527836023000,"starttime":1530428140000,"startworktime":"07:30","endworktime":"17:30","status":0,"worknum":30,"merchantid":2}
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
         * id : 1
         * userid : 1
         * name : 绿怡居改造工程
         * categoryid : 1
         * regulatoryagency : 合肥一建
         * headman : 王兵兵
         * mobile : 15155148936
         * address : 高河东路绿地蓝海C座
         * clockrange : 500
         * lng : 117.247574
         * lat : 31.821125
         * createtime : 1527836023000
         * starttime : 1530428140000
         * startworktime : 07:30
         * endworktime : 17:30
         * status : 0
         * worknum : 30
         * merchantid : 2
         */

        private int id;
        private int userid;
        private String name;
        private int categoryid;
        private String regulatoryagency;
        private String headman;
        private String mobile;
        private String address;
        private int clockrange;
        private double lng;
        private double lat;
        private long createtime;
        private String starttime;
        private String startworktime;
        private String endworktime;
        private int status;
        private int worknum;
        private int merchantid;

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

        public int getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(int categoryid) {
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

        public int getClockrange() {
            return clockrange;
        }

        public void setClockrange(int clockrange) {
            this.clockrange = clockrange;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
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

        public int getWorknum() {
            return worknum;
        }

        public void setWorknum(int worknum) {
            this.worknum = worknum;
        }

        public int getMerchantid() {
            return merchantid;
        }

        public void setMerchantid(int merchantid) {
            this.merchantid = merchantid;
        }
    }
}
