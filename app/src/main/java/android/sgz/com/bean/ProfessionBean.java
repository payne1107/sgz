package android.sgz.com.bean;

import java.util.List;

/**
 * Created by 92457 on 2018/6/7.
 */

public class ProfessionBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"profession":"工程师","status":1},{"id":2,"profession":"水电工","status":1},{"id":3,"profession":"机械工","status":1},{"id":4,"profession":"瓦工","status":1},{"id":5,"profession":"油漆工","status":1},{"id":6,"profession":"木工","status":1},{"id":7,"profession":"架手工","status":1},{"id":8,"profession":"塔吊工","status":1}]
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
         * id : 1
         * profession : 工程师
         * status : 1
         */

        private int id;
        private String profession;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
