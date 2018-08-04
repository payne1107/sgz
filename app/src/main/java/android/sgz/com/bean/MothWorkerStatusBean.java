package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/7/23.
 */

public class MothWorkerStatusBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"date":"1","startstatus":2,"endstatus":1,"ifextrawork":0,"projectid":25,"projectname":"魏"}]
     * exception : true
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private boolean exception;
    private List<DataBean> data;

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

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 1
         * startstatus : 2
         * endstatus : 1
         * ifextrawork : 0
         * projectid : 25
         * projectname : 魏
         */

        private String date;
        private int startstatus;
        private int endstatus;
        private int ifextrawork;
        private int projectid;
        private String projectname;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

        public int getIfextrawork() {
            return ifextrawork;
        }

        public void setIfextrawork(int ifextrawork) {
            this.ifextrawork = ifextrawork;
        }

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
    }
}
