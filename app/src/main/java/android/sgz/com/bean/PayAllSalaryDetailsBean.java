package android.sgz.com.bean;

public class PayAllSalaryDetailsBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"projectid":45,"projectname":"安徽省跳湖项目","address":"北环路与枫落路交叉口西北50米","starttime":1532275200000,"workercount":4,"allworkdays":0,"alladdworkdays":0,"avgsalay":29.25,"allsalay":0,"allpaysalary":0,"alladdsalary":0,"userid":null}
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
         * projectid : 45
         * projectname : 安徽省跳湖项目
         * address : 北环路与枫落路交叉口西北50米
         * starttime : 1532275200000
         * workercount : 4
         * allworkdays : 0
         * alladdworkdays : 0
         * avgsalay : 29.25
         * allsalay : 0.0
         * allpaysalary : 0.0
         * alladdsalary : 0.0
         * userid : null
         */

        private int projectid;
        private String projectname;
        private String address;
        private long starttime;
        private int workercount;
        private int allworkdays;
        private int alladdworkdays;
        private double avgsalay;
        private double allsalay;
        private double allpaysalary;
        private double alladdsalary;
        private Object userid;

        public int getProjectid() {
            return projectid;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }

        public String getProjectname() {
            return projectname;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getStarttime() {
            return starttime;
        }

        public void setStarttime(long starttime) {
            this.starttime = starttime;
        }

        public int getWorkercount() {
            return workercount;
        }

        public void setWorkercount(int workercount) {
            this.workercount = workercount;
        }

        public int getAllworkdays() {
            return allworkdays;
        }

        public void setAllworkdays(int allworkdays) {
            this.allworkdays = allworkdays;
        }

        public int getAlladdworkdays() {
            return alladdworkdays;
        }

        public void setAlladdworkdays(int alladdworkdays) {
            this.alladdworkdays = alladdworkdays;
        }

        public double getAvgsalay() {
            return avgsalay;
        }

        public void setAvgsalay(double avgsalay) {
            this.avgsalay = avgsalay;
        }

        public double getAllsalay() {
            return allsalay;
        }

        public void setAllsalay(double allsalay) {
            this.allsalay = allsalay;
        }

        public double getAllpaysalary() {
            return allpaysalary;
        }

        public void setAllpaysalary(double allpaysalary) {
            this.allpaysalary = allpaysalary;
        }

        public double getAlladdsalary() {
            return alladdsalary;
        }

        public void setAlladdsalary(double alladdsalary) {
            this.alladdsalary = alladdsalary;
        }

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
            this.userid = userid;
        }
    }
}
