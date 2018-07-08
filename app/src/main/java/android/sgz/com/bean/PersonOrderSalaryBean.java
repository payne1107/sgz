package android.sgz.com.bean;

/**
 * Created by WD on 2018/7/5.
 */

public class PersonOrderSalaryBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":1,"username":null,"mobile":"18130055543","realname":"我是假的","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":"400.00","addsalary":"25.00","birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"type":null,"projectnum":null,"referee":null,"vipuserid":null,"profession":null,"friendsid":null,"provincename":null,"cityname":null,"professionlevelname":null,"name":null,"allowance":30,"paymentsalary":2189,"allsalary":1480,"totalallowance":120,"alladdsalary":200,"workdays":4,"projectid":null,"alladdtime":8}
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
         * id : 1
         * username : null
         * mobile : 18130055543
         * realname : 我是假的
         * photo : null
         * provinceid : null
         * cityid : null
         * msalary : null
         * dsalary : 400.00
         * addsalary : 25.00
         * birthday : null
         * sign : null
         * professionid : null
         * professionlevelid : null
         * type : null
         * projectnum : null
         * referee : null
         * vipuserid : null
         * profession : null
         * friendsid : null
         * provincename : null
         * cityname : null
         * professionlevelname : null
         * name : null
         * allowance : 30
         * paymentsalary : 2189
         * allsalary : 1480
         * totalallowance : 120
         * alladdsalary : 200
         * workdays : 4
         * projectid : null
         * alladdtime : 8
         */

        private int id;
        private Object username;
        private String mobile;
        private String realname;
        private Object photo;
        private Object provinceid;
        private Object cityid;
        private Object msalary;
        private String dsalary;
        private String addsalary;
        private Object birthday;
        private Object sign;
        private Object professionid;
        private Object professionlevelid;
        private Object type;
        private Object projectnum;
        private Object referee;
        private Object vipuserid;
        private Object profession;
        private Object friendsid;
        private Object provincename;
        private Object cityname;
        private Object professionlevelname;
        private Object name;
        private int allowance;
        private double paymentsalary;
        private int allsalary;
        private String totalallowance;
        private String alladdsalary;
        private int workdays;
        private Object projectid;
        private int alladdtime;
        private double withdrawsalary;

        public double getWithdrawsalary() {
            return withdrawsalary;
        }

        public void setWithdrawsalary(double withdrawsalary) {
            this.withdrawsalary = withdrawsalary;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
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

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
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

        public String getDsalary() {
            return dsalary;
        }

        public void setDsalary(String dsalary) {
            this.dsalary = dsalary;
        }

        public String getAddsalary() {
            return addsalary;
        }

        public void setAddsalary(String addsalary) {
            this.addsalary = addsalary;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
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

        public Object getVipuserid() {
            return vipuserid;
        }

        public void setVipuserid(Object vipuserid) {
            this.vipuserid = vipuserid;
        }

        public Object getProfession() {
            return profession;
        }

        public void setProfession(Object profession) {
            this.profession = profession;
        }

        public Object getFriendsid() {
            return friendsid;
        }

        public void setFriendsid(Object friendsid) {
            this.friendsid = friendsid;
        }

        public Object getProvincename() {
            return provincename;
        }

        public void setProvincename(Object provincename) {
            this.provincename = provincename;
        }

        public Object getCityname() {
            return cityname;
        }

        public void setCityname(Object cityname) {
            this.cityname = cityname;
        }

        public Object getProfessionlevelname() {
            return professionlevelname;
        }

        public void setProfessionlevelname(Object professionlevelname) {
            this.professionlevelname = professionlevelname;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getAllowance() {
            return allowance;
        }

        public void setAllowance(int allowance) {
            this.allowance = allowance;
        }

        public double getPaymentsalary() {
            return paymentsalary;
        }

        public void setPaymentsalary(double paymentsalary) {
            this.paymentsalary = paymentsalary;
        }

        public int getAllsalary() {
            return allsalary;
        }

        public void setAllsalary(int allsalary) {
            this.allsalary = allsalary;
        }

        public String getTotalallowance() {
            return totalallowance;
        }

        public void setTotalallowance(String totalallowance) {
            this.totalallowance = totalallowance;
        }

        public String getAlladdsalary() {
            return alladdsalary;
        }

        public void setAlladdsalary(String alladdsalary) {
            this.alladdsalary = alladdsalary;
        }

        public int getWorkdays() {
            return workdays;
        }

        public void setWorkdays(int workdays) {
            this.workdays = workdays;
        }

        public Object getProjectid() {
            return projectid;
        }

        public void setProjectid(Object projectid) {
            this.projectid = projectid;
        }

        public int getAlladdtime() {
            return alladdtime;
        }

        public void setAlladdtime(int alladdtime) {
            this.alladdtime = alladdtime;
        }
    }
}
