package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/16.
 */

public class ContactsDetailsBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"id":7,"username":"15955978338","password":null,"mobile":"15955978338","wxcode":null,"realname":"Rebecca","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":"水电工","friendsid":null,"enabled":true,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true}
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
         * id : 7
         * username : 15955978338
         * password : null
         * mobile : 15955978338
         * wxcode : null
         * realname : Rebecca
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
         * friendsid : null
         * enabled : true
         * accountNonExpired : true
         * accountNonLocked : true
         * credentialsNonExpired : true
         */

        private int id;
        private String username;
        private Object password;
        private String mobile;
        private Object wxcode;
        private String realname;
        private Object photo;
        private Object provinceid;
        private Object cityid;
        private Object msalary;
        private Object dsalary;
        private Object addsalary;
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
        private String profession;
        private Object friendsid;
        private boolean enabled;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;

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

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public Object getFriendsid() {
            return friendsid;
        }

        public void setFriendsid(Object friendsid) {
            this.friendsid = friendsid;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
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
    }
}
