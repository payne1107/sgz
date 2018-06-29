package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/7.
 */

public class LoginSucessBean {


    /**
     * data : {"refreshtoken":"+ku9OwFKzLiOnkHp+stiLNSm4AuO3yZd","token":"eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNSIsImV4cCI6MTUzMDMyNTcwNn0.vY9ytEgAXh-ad7PLDBCKicE8wZmYh8MX6LCIehFMmLxOOHRB1SxKDJLyhNKCeh2vfVB1XPPyCXEQ2CEfwoqOMg","userid":15}
     * resultCode : 1
     * success : true
     */

    private DataBean data;
    private String resultCode;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * refreshtoken : +ku9OwFKzLiOnkHp+stiLNSm4AuO3yZd
         * token : eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNSIsImV4cCI6MTUzMDMyNTcwNn0.vY9ytEgAXh-ad7PLDBCKicE8wZmYh8MX6LCIehFMmLxOOHRB1SxKDJLyhNKCeh2vfVB1XPPyCXEQ2CEfwoqOMg
         * userid : 15
         */

        private String refreshtoken;
        private String token;
        private String userid;

        public String getRefreshtoken() {
            return refreshtoken;
        }

        public void setRefreshtoken(String refreshtoken) {
            this.refreshtoken = refreshtoken;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
