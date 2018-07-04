package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/7/4.
 */

public class WorkOrderDetailsBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"projectid":1,"userid":1,"salary":400,"overworksalary":25,"allowance":30,"paymentsalary":2189,"withdrawsalary":700,"defaultproject":0,"createtime":1530712732820,"user":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"我是假的","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"6来看看","professionid":null,"professionlevelid":null,"type":null,"projectnum":null,"referee":null}},{"id":3,"projectid":1,"userid":7,"salary":300,"overworksalary":30,"allowance":25,"paymentsalary":0,"withdrawsalary":0,"defaultproject":1,"createtime":1530712732826,"user":{"id":7,"username":"15955978338","mobile":"15955978338","realname":"Rebecca","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"type":null,"projectnum":null,"referee":null}},{"id":4,"projectid":1,"userid":8,"salary":300,"overworksalary":30,"allowance":25,"paymentsalary":522,"withdrawsalary":0,"defaultproject":1,"createtime":1530712732830,"user":{"id":8,"username":"15856505441","mobile":"15856505441","realname":"1111","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"1111111","professionid":null,"professionlevelid":null,"type":null,"projectnum":null,"referee":null}}]
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
         * projectid : 1
         * userid : 1
         * salary : 400
         * overworksalary : 25
         * allowance : 30
         * paymentsalary : 2189
         * withdrawsalary : 700
         * defaultproject : 0
         * createtime : 1530712732820
         * user : {"id":1,"username":"18130055543","mobile":"18130055543","realname":"我是假的","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"6来看看","professionid":null,"professionlevelid":null,"type":null,"projectnum":null,"referee":null}
         */

        private int id;
        private int projectid;
        private int userid;
        private String salary;
        private String overworksalary;
        private String allowance;
        private int paymentsalary;
        private int withdrawsalary;
        private int defaultproject;
        private long createtime;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProjectid() {
            return projectid;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getOverworksalary() {
            return overworksalary;
        }

        public void setOverworksalary(String overworksalary) {
            this.overworksalary = overworksalary;
        }

        public String getAllowance() {
            return allowance;
        }

        public void setAllowance(String allowance) {
            this.allowance = allowance;
        }

        public int getPaymentsalary() {
            return paymentsalary;
        }

        public void setPaymentsalary(int paymentsalary) {
            this.paymentsalary = paymentsalary;
        }

        public int getWithdrawsalary() {
            return withdrawsalary;
        }

        public void setWithdrawsalary(int withdrawsalary) {
            this.withdrawsalary = withdrawsalary;
        }

        public int getDefaultproject() {
            return defaultproject;
        }

        public void setDefaultproject(int defaultproject) {
            this.defaultproject = defaultproject;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 1
             * username : 18130055543
             * mobile : 18130055543
             * realname : 我是假的
             * photo : 2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png
             * provinceid : null
             * cityid : null
             * msalary : null
             * dsalary : null
             * addsalary : null
             * birthday : null
             * sign : 6来看看
             * professionid : null
             * professionlevelid : null
             * type : null
             * projectnum : null
             * referee : null
             */

            private int id;
            private String username;
            private String mobile;
            private String realname;
            private String photo;
            private Object provinceid;
            private Object cityid;
            private Object msalary;
            private Object dsalary;
            private Object addsalary;
            private Object birthday;
            private String sign;
            private Object professionid;
            private Object professionlevelid;
            private Object type;
            private Object projectnum;
            private Object referee;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public Object getProvinceid() {
                return provinceid;
            }

            public void setProvinceid(Object provinceid) {
                this.provinceid = provinceid;
            }

            public Object getCityid() {
                return cityid;
            }

            public void setCityid(Object cityid) {
                this.cityid = cityid;
            }

            public Object getMsalary() {
                return msalary;
            }

            public void setMsalary(Object msalary) {
                this.msalary = msalary;
            }

            public Object getDsalary() {
                return dsalary;
            }

            public void setDsalary(Object dsalary) {
                this.dsalary = dsalary;
            }

            public Object getAddsalary() {
                return addsalary;
            }

            public void setAddsalary(Object addsalary) {
                this.addsalary = addsalary;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public Object getProfessionid() {
                return professionid;
            }

            public void setProfessionid(Object professionid) {
                this.professionid = professionid;
            }

            public Object getProfessionlevelid() {
                return professionlevelid;
            }

            public void setProfessionlevelid(Object professionlevelid) {
                this.professionlevelid = professionlevelid;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getProjectnum() {
                return projectnum;
            }

            public void setProjectnum(Object projectnum) {
                this.projectnum = projectnum;
            }

            public Object getReferee() {
                return referee;
            }

            public void setReferee(Object referee) {
                this.referee = referee;
            }
        }
    }
}
