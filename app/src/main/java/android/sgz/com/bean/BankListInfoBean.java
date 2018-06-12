package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/12.
 */

public class BankListInfoBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"code":"BOC","name":"中国银行","status":1},{"id":2,"code":"CCB","name":"中国建设银行","status":1},{"id":3,"code":"ABC","name":"中国农业银行","status":1},{"id":4,"code":"ICBC","name":"中国工商银行","status":1},{"id":5,"code":"CMBC","name":"中国民生银行","status":1},{"id":8,"code":"BOCOM","name":"中国交通银行","status":1},{"id":9,"code":"PBOC","name":"中国人民银行","status":null}]
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
         * code : BOC
         * name : 中国银行
         * status : 1
         */

        private int id;
        private String code;
        private String name;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
