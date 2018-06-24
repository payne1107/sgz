package android.sgz.com.bean;

import java.util.List;

/**
 * Created by 92457 on 2018/6/16.
 */

public class ContactsBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":7,"username":"15955978338","mobile":"15955978338","wxcode":null,"realname":"Rebecca","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":"水电工","friendsid":null,"provincename":null,"cityname":null,"professionlevelname":null,"name":null,"allowance":null,"paymentsalary":null,"allsalary":null,"alladdsalary":null,"workdays":null,"projectid":null},{"id":8,"username":"15856505441","mobile":"15856505441","wxcode":null,"realname":"1111","photo":null,"provinceid":null,"cityid":null,"msalary":"111.00","dsalary":"1111.00","addsalary":"111.00","birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":"水电工","friendsid":null,"provincename":null,"cityname":null,"professionlevelname":null,"name":null,"allowance":null,"paymentsalary":null,"allsalary":null,"alladdsalary":null,"workdays":null,"projectid":null}]}
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
         * curPage : 1
         * coutpage : 1
         * pageSize : 15
         * list : [{"id":7,"username":"15955978338","mobile":"15955978338","wxcode":null,"realname":"Rebecca","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":"水电工","friendsid":null,"provincename":null,"cityname":null,"professionlevelname":null,"name":null,"allowance":null,"paymentsalary":null,"allsalary":null,"alladdsalary":null,"workdays":null,"projectid":null},{"id":8,"username":"15856505441","mobile":"15856505441","wxcode":null,"realname":"1111","photo":null,"provinceid":null,"cityid":null,"msalary":"111.00","dsalary":"1111.00","addsalary":"111.00","birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null,"vipuserid":null,"profession":"水电工","friendsid":null,"provincename":null,"cityname":null,"professionlevelname":null,"name":null,"allowance":null,"paymentsalary":null,"allsalary":null,"alladdsalary":null,"workdays":null,"projectid":null}]
         */

        private int curPage;
        private int coutpage;
        private int pageSize;
        private List<ListBean> list;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getCoutpage() {
            return coutpage;
        }

        public void setCoutpage(int coutpage) {
            this.coutpage = coutpage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 7
             * username : 15955978338
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
             * provincename : null
             * cityname : null
             * professionlevelname : null
             * name : null
             * allowance : null
             * paymentsalary : null
             * allsalary : null
             * alladdsalary : null
             * workdays : null
             * projectid : null
             */

            private int id;
            private String username;
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
            private String friendsid;
            private String provincename;
            private String cityname;
            private String professionlevelname;
            private String name;
            private String allowance;
            private String paymentsalary;
            private String allsalary;
            private String alladdsalary;
            private String workdays;
            private String projectid;

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

            public String getFriendsid() {
                return friendsid;
            }

            public void setFriendsid(String friendsid) {
                this.friendsid = friendsid;
            }

            public String getProvincename() {
                return provincename;
            }

            public void setProvincename(String provincename) {
                this.provincename = provincename;
            }

            public String getCityname() {
                return cityname;
            }

            public void setCityname(String cityname) {
                this.cityname = cityname;
            }

            public String getProfessionlevelname() {
                return professionlevelname;
            }

            public void setProfessionlevelname(String professionlevelname) {
                this.professionlevelname = professionlevelname;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAllowance() {
                return allowance;
            }

            public void setAllowance(String allowance) {
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

            public String getWorkdays() {
                return workdays;
            }

            public void setWorkdays(String workdays) {
                this.workdays = workdays;
            }

            public String getProjectid() {
                return projectid;
            }

            public void setProjectid(String projectid) {
                this.projectid = projectid;
            }
        }
    }
}
