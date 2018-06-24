package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class MineExpendDetailsBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"username":null,"password":null,"mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":"400.00","addsalary":"25.00","birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":null,"friendsid":null,"provincename":null,"cityname":null,"professionlevelname":null,"name":null,"allowance":30,"paymentsalary":1000,"allsalary":1265,"alladdsalary":250,"workdays":4,"projectid":null,"enabled":true,"accountNonLocked":true,"accountNonExpired":true,"credentialsNonExpired":true}]
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
         * username : null
         * password : null
         * mobile : 18130055543
         * wxcode : null
         * realname : Lisa
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
         * locked : null
         * type : null
         * createtime : null
         * projectnum : null
         * roles : null
         * vipuserid : null
         * profession : null
         * friendsid : null
         * provincename : null
         * cityname : null
         * professionlevelname : null
         * name : null
         * allowance : 30
         * paymentsalary : 1000
         * allsalary : 1265
         * alladdsalary : 250
         * workdays : 4
         * projectid : null
         * enabled : true
         * accountNonLocked : true
         * accountNonExpired : true
         * credentialsNonExpired : true
         */

        private int id;
        private Object username;
        private Object password;
        private String mobile;
        private Object wxcode;
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
        private Object locked;
        private Object type;
        private Object createtime;
        private Object projectnum;
        private Object roles;
        private Object vipuserid;
        private Object profession;
        private Object friendsid;
        private Object provincename;
        private Object cityname;
        private Object professionlevelname;
        private Object name;
        private int allowance;
        private String paymentsalary;
        private String allsalary;
        private String alladdsalary;
        private int workdays;
        private Object projectid;
        private boolean enabled;
        private boolean accountNonLocked;
        private boolean accountNonExpired;
        private boolean credentialsNonExpired;

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

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getWxcode() {
            return wxcode;
        }

        public void setWxcode(Object wxcode) {
            this.wxcode = wxcode;
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

        public Object getLocked() {
            return locked;
        }

        public void setLocked(Object locked) {
            this.locked = locked;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public Object getProjectnum() {
            return projectnum;
        }

        public void setProjectnum(Object projectnum) {
            this.projectnum = projectnum;
        }

        public Object getRoles() {
            return roles;
        }

        public void setRoles(Object roles) {
            this.roles = roles;
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

        public String getPaymentsalary() {
            return paymentsalary;
        }

        public void setPaymentsalary(String paymentsalary) {
            this.paymentsalary = paymentsalary;
        }

        public String getAllsalary() {
            return allsalary;
        }

        public void setAllsalary(String allsalary) {
            this.allsalary = allsalary;
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

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }
    }
}
