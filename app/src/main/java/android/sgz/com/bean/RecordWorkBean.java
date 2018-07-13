package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/27.
 */

public class RecordWorkBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"extraworktime":0,"leak":0,"leave":0,"late":0,"attendance":0,"projectid":1}
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
         * extraworktime : 0
         * leak : 0
         * leave : 0
         * late : 0
         * attendance : 0
         * projectid : 1
         */

        private int extraworktime;
        private int leak;
        private int leave;
        private int late;
        private int attendance;
        private int projectid;
        private String projectname;

        public String getProjectname() {
            return projectname;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public int getExtraworktime() {
            return extraworktime;
        }

        public void setExtraworktime(int extraworktime) {
            this.extraworktime = extraworktime;
        }

        public int getLeak() {
            return leak;
        }

        public void setLeak(int leak) {
            this.leak = leak;
        }

        public int getLeave() {
            return leave;
        }

        public void setLeave(int leave) {
            this.leave = leave;
        }

        public int getLate() {
            return late;
        }

        public void setLate(int late) {
            this.late = late;
        }

        public int getAttendance() {
            return attendance;
        }

        public void setAttendance(int attendance) {
            this.attendance = attendance;
        }

        public int getProjectid() {
            return projectid;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }
    }
}
