package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/24.
 */

public class MineBalaneBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":16,"userid":1,"balance":0,"updatetime":1528254318000}
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
         * id : 16
         * userid : 1
         * balance : 0
         * updatetime : 1528254318000
         */

        private int id;
        private int userid;
        private int balance;
        private long updatetime;

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

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public long getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(long updatetime) {
            this.updatetime = updatetime;
        }
    }
}
