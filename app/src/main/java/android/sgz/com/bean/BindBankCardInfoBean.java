package android.sgz.com.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WD on 2018/6/13.
 */

public class BindBankCardInfoBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"userid":1,"bankcard":"6225758309579778","bankcode":"ICBC","subbankname":"中国工商银行政务区支行","idcard":"34242219880828674X","realname":"李木子"},{"id":3,"userid":1,"bankcard":"11","bankcode":"ABC","subbankname":"1111","idcard":"11","realname":"中国农业银行"},{"id":4,"userid":1,"bankcard":"1212313","bankcode":"ABC","subbankname":"131231313","idcard":"1313123","realname":"中国农业银行"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * userid : 1
         * bankcard : 6225758309579778
         * bankcode : ICBC
         * subbankname : 中国工商银行政务区支行
         * idcard : 34242219880828674X
         * realname : 李木子
         */

        private int id;
        private int userid;
        private String bankcard;
        private String bankcode;
        private String subbankname;
        private String idcard;
        private String realname;

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

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }

        public String getBankcode() {
            return bankcode;
        }

        public void setBankcode(String bankcode) {
            this.bankcode = bankcode;
        }

        public String getSubbankname() {
            return subbankname;
        }

        public void setSubbankname(String subbankname) {
            this.subbankname = subbankname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }
}
