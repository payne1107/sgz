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
     * data : [{"date":"1","startstatus":4,"endstatus":4},{"date":"2","startstatus":4,"endstatus":4},{"date":"3","startstatus":4,"endstatus":4},{"date":"4","startstatus":4,"endstatus":4},{"date":"5","startstatus":4,"endstatus":4},{"date":"6","startstatus":4,"endstatus":4},{"date":"7","startstatus":4,"endstatus":4},{"date":"8","startstatus":4,"endstatus":4},{"date":"9","startstatus":4,"endstatus":4},{"date":"10","startstatus":4,"endstatus":4},{"date":"11","startstatus":4,"endstatus":4},{"date":"12","startstatus":4,"endstatus":4},{"date":"13","startstatus":4,"endstatus":4},{"date":"14","startstatus":4,"endstatus":4},{"date":"15","startstatus":4,"endstatus":4},{"date":"16","startstatus":4,"endstatus":4},{"date":"17","startstatus":1,"endstatus":4},{"date":"18","startstatus":4,"endstatus":4},{"date":"19","startstatus":4,"endstatus":4},{"date":"20","startstatus":1,"endstatus":1},{"date":"21","startstatus":4,"endstatus":4},{"date":"22","startstatus":4,"endstatus":4},{"date":"23","startstatus":4,"endstatus":4}]
     * exception : false
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
         * startstatus : 4
         * endstatus : 4
         */

        private String date;
        private int startstatus;
        private int endstatus;

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
    }
}
