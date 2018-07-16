package android.sgz.com.bean;

/**
 * Created by WD on 2018/7/15.
 */

public class RongCloudBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"code":200,"msg":null,"token":"mySMQB+QshEBzlgzcn6QGeVvuJUBzrrNB02J0OkmS3mfP97gL4QziaNRlFRF4MGCRtvnt/hHQC06tKhTABgxIA==","userId":"32"}
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
         * code : 200
         * msg : null
         * token : mySMQB+QshEBzlgzcn6QGeVvuJUBzrrNB02J0OkmS3mfP97gL4QziaNRlFRF4MGCRtvnt/hHQC06tKhTABgxIA==
         * userId : 32
         */

        private int code;
        private Object msg;
        private String token;
        private String userId;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
