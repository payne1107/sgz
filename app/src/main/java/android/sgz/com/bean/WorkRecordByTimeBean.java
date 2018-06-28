package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/28.
 */

public class WorkRecordByTimeBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":null,"userid":null,"projectid":null,"startrecordtime":null,"endrecordtime":null,"startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":0,"allowance":0,"startstatus":4,"endstatus":4}
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
         * projectid : null
         * startrecordtime : null
         * endrecordtime : null
         * startrecordaddress : null
         * endrecordaddress : null
         * startlat : null
         * startlng : null
         * endlat : null
         * endlng : null
         * daysalary : 0
         * allowance : 0
         * startstatus : 4
         * endstatus : 4
         */

        private String id;
        private String userid;
        private String projectid;
        private String startrecordtime;
        private String endrecordtime;
        private String startrecordaddress;
        private String endrecordaddress;
        private String startlat;
        private String startlng;
        private String endlat;
        private String endlng;
        private int daysalary;
        private int allowance;
        private int startstatus;
        private int endstatus;

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

        public String getProjectid() {
            return projectid;
        }

        public void setProjectid(String projectid) {
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

        public String getStartlat() {
            return startlat;
        }

        public void setStartlat(String startlat) {
            this.startlat = startlat;
        }

        public String getStartlng() {
            return startlng;
        }

        public void setStartlng(String startlng) {
            this.startlng = startlng;
        }

        public String getEndlat() {
            return endlat;
        }

        public void setEndlat(String endlat) {
            this.endlat = endlat;
        }

        public String getEndlng() {
            return endlng;
        }

        public void setEndlng(String endlng) {
            this.endlng = endlng;
        }

        public int getDaysalary() {
            return daysalary;
        }

        public void setDaysalary(int daysalary) {
            this.daysalary = daysalary;
        }

        public int getAllowance() {
            return allowance;
        }

        public void setAllowance(int allowance) {
            this.allowance = allowance;
        }

        public int getStartstatus() {
            return startstatus;
        }

        public void setStartstatus(int startstatus) {
            this.startstatus = startstatus;
        }

        public int getEndstatus() {
            return endstatus;
        }

        public void setEndstatus(int endstatus) {
            this.endstatus = endstatus;
        }
    }
}
