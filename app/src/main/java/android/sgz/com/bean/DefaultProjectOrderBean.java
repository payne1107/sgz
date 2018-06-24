package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/22.
 */

public class DefaultProjectOrderBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"project":{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":1,"regulatoryagency":"合肥一建","headman":"王兵兵","mobile":"15155148936","address":"高河东路绿地蓝海C座","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":"2018-06-01 14:53","starttime":"2018-07-01 14:55","startworktime":"07:30","endworktime":"17:30","status":0,"worknum":30,"merchantid":2},"workRecord":{"id":5,"userid":1,"projectid":1,"startrecordtime":"2018-06-24 05:20:11","endrecordtime":"2018-06-24 15:01:48","startrecordaddress":"安徽合肥新城国际","endrecordaddress":"合肥市中共十里店社区支部委员会","startlat":31.821125,"startlng":117.247574,"endlat":31.859879,"endlng":117.223441,"daysalary":640,"allowance":30}}
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
         * project : {"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":1,"regulatoryagency":"合肥一建","headman":"王兵兵","mobile":"15155148936","address":"高河东路绿地蓝海C座","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":"2018-06-01 14:53","starttime":"2018-07-01 14:55","startworktime":"07:30","endworktime":"17:30","status":0,"worknum":30,"merchantid":2}
         * workRecord : {"id":5,"userid":1,"projectid":1,"startrecordtime":"2018-06-24 05:20:11","endrecordtime":"2018-06-24 15:01:48","startrecordaddress":"安徽合肥新城国际","endrecordaddress":"合肥市中共十里店社区支部委员会","startlat":31.821125,"startlng":117.247574,"endlat":31.859879,"endlng":117.223441,"daysalary":640,"allowance":30}
         */

        private ProjectBean project;
        private WorkRecordBean workRecord;

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public WorkRecordBean getWorkRecord() {
            return workRecord;
        }

        public void setWorkRecord(WorkRecordBean workRecord) {
            this.workRecord = workRecord;
        }

        public static class ProjectBean {
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
             * createtime : 2018-06-01 14:53
             * starttime : 2018-07-01 14:55
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
            private String createtime;
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

        public static class WorkRecordBean {
            /**
             * id : 5
             * userid : 1
             * projectid : 1
             * startrecordtime : 2018-06-24 05:20:11
             * endrecordtime : 2018-06-24 15:01:48
             * startrecordaddress : 安徽合肥新城国际
             * endrecordaddress : 合肥市中共十里店社区支部委员会
             * startlat : 31.821125
             * startlng : 117.247574
             * endlat : 31.859879
             * endlng : 117.223441
             * daysalary : 640.0
             * allowance : 30.0
             */

            private int id;
            private int userid;
            private int projectid;
            private String startrecordtime;
            private String endrecordtime;
            private String startrecordaddress;
            private String endrecordaddress;
            private double startlat;
            private double startlng;
            private double endlat;
            private double endlng;
            private double daysalary;
            private double allowance;

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

            public int getProjectid() {
                return projectid;
            }

            public void setProjectid(int projectid) {
                this.projectid = projectid;
            }

            public String getStartrecordtime() {
                return startrecordtime;
            }

            public void setStartrecordtime(String startrecordtime) {
                this.startrecordtime = startrecordtime;
            }

            public String getEndrecordtime() {
                return endrecordtime;
            }

            public void setEndrecordtime(String endrecordtime) {
                this.endrecordtime = endrecordtime;
            }

            public String getStartrecordaddress() {
                return startrecordaddress;
            }

            public void setStartrecordaddress(String startrecordaddress) {
                this.startrecordaddress = startrecordaddress;
            }

            public String getEndrecordaddress() {
                return endrecordaddress;
            }

            public void setEndrecordaddress(String endrecordaddress) {
                this.endrecordaddress = endrecordaddress;
            }

            public double getStartlat() {
                return startlat;
            }

            public void setStartlat(double startlat) {
                this.startlat = startlat;
            }

            public double getStartlng() {
                return startlng;
            }

            public void setStartlng(double startlng) {
                this.startlng = startlng;
            }

            public double getEndlat() {
                return endlat;
            }

            public void setEndlat(double endlat) {
                this.endlat = endlat;
            }

            public double getEndlng() {
                return endlng;
            }

            public void setEndlng(double endlng) {
                this.endlng = endlng;
            }

            public double getDaysalary() {
                return daysalary;
            }

            public void setDaysalary(double daysalary) {
                this.daysalary = daysalary;
            }

            public double getAllowance() {
                return allowance;
            }

            public void setAllowance(double allowance) {
                this.allowance = allowance;
            }
        }
    }
}
