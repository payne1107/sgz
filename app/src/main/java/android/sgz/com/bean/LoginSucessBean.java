package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/7.
 */

public class LoginSucessBean {


    /**
     * data : {"refreshtoken":"4l9uONmrRiIcd2eZkL/NdQ==","token":"eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTMwMjg0MjU1fQ.iA2xbOi48NKH3kTvxzzN2k3VLacrgjAK4awGR3q-sjlKXpZSHF3p4XH_s_QZBOQ6wGi6XBMX8NvOrxc-MGMlZQ","userid":1}
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
         * refreshtoken : 4l9uONmrRiIcd2eZkL/NdQ==
         * token : eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTMwMjg0MjU1fQ.iA2xbOi48NKH3kTvxzzN2k3VLacrgjAK4awGR3q-sjlKXpZSHF3p4XH_s_QZBOQ6wGi6XBMX8NvOrxc-MGMlZQ
         * userid : 1
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
