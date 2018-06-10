package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/10.
 */

public class VipMemberListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"type":1,"money":80,"amount":null,"month":6},{"id":2,"type":2,"money":120,"amount":null,"month":12}]
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
         * type : 1
         * money : 80.0
         * amount : null
         * month : 6
         */

        private int id;
        private int type;
        private double money;
        private Object amount;
        private int month;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }
    }
}
