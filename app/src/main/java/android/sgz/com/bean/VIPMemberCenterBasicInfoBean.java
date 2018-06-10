package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/10.
 */

public class VIPMemberCenterBasicInfoBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":8,"username":null,"password":null,"mobile":null,"wxcode":null,"realname":"1111","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":"水电工","accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
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
         * id : 8
         * username : null
         * password : null
         * mobile : null
         * wxcode : null
         * realname : 1111
         * photo : null
         * provinceid : null
         * cityid : null
         * msalary : null
         * dsalary : null
         * addsalary : null
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
         * profession : 水电工
         * accountNonExpired : true
         * accountNonLocked : true
         * credentialsNonExpired : true
         * enabled : true
         */

        private int id;
        private String username;
        private String password;
        private String mobile;
        private String wxcode;
        private String realname;
        private String photo;
        private String provinceid;
        private String cityid;
        private String msalary;
        private String dsalary;
        private String addsalary;
        private String birthday;
        private String sign;
        private String professionid;
        private String professionlevelid;
        private String locked;
        private String type;
        private String createtime;
        private String projectnum;
        private String roles;
        private String vipuserid;
        private String profession;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWxcode() {
            return wxcode;
        }

        public void setWxcode(String wxcode) {
            this.wxcode = wxcode;
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

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getMsalary() {
            return msalary;
        }

        public void setMsalary(String msalary) {
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getProfessionid() {
            return professionid;
        }

        public void setProfessionid(String professionid) {
            this.professionid = professionid;
        }

        public String getProfessionlevelid() {
            return professionlevelid;
        }

        public void setProfessionlevelid(String professionlevelid) {
            this.professionlevelid = professionlevelid;
        }

        public String getLocked() {
            return locked;
        }

        public void setLocked(String locked) {
            this.locked = locked;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getProjectnum() {
            return projectnum;
        }

        public void setProjectnum(String projectnum) {
            this.projectnum = projectnum;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getVipuserid() {
            return vipuserid;
        }

        public void setVipuserid(String vipuserid) {
            this.vipuserid = vipuserid;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
