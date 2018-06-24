package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class MineHomePageBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":6,"userid":1,"content":"测试下","createtime":1529810581000,"imgs":"[\"2018/06/24/ca9e4a4108fc4a4fb7a5958480524119.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/ca9e4a4108fc4a4fb7a5958480524119.jpg"],"user":{"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}},{"id":5,"userid":1,"content":"测试下","createtime":1529809602000,"imgs":"[\"2018/06/24/187253143e954a3b906906b58a7b1217.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/187253143e954a3b906906b58a7b1217.jpg"],"user":{"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}},{"id":2,"userid":2,"content":"今天天气不错，继续加油！","createtime":1529395835000,"imgs":"[\"2018/06/24/187253143e954a3b906906b58a7b1217.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/187253143e954a3b906906b58a7b1217.jpg"],"user":{"id":2,"username":"18130055568","mobile":"18130055568","wxcode":null,"realname":"MQ","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}},{"id":1,"userid":1,"content":"我就是我，不一样的烟火！","createtime":1529309413000,"imgs":"[\"2018/06/24/187253143e954a3b906906b58a7b1217.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/187253143e954a3b906906b58a7b1217.jpg"],"user":{"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}}]}
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
         * list : [{"id":6,"userid":1,"content":"测试下","createtime":1529810581000,"imgs":"[\"2018/06/24/ca9e4a4108fc4a4fb7a5958480524119.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/ca9e4a4108fc4a4fb7a5958480524119.jpg"],"user":{"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}},{"id":5,"userid":1,"content":"测试下","createtime":1529809602000,"imgs":"[\"2018/06/24/187253143e954a3b906906b58a7b1217.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/187253143e954a3b906906b58a7b1217.jpg"],"user":{"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}},{"id":2,"userid":2,"content":"今天天气不错，继续加油！","createtime":1529395835000,"imgs":"[\"2018/06/24/187253143e954a3b906906b58a7b1217.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/187253143e954a3b906906b58a7b1217.jpg"],"user":{"id":2,"username":"18130055568","mobile":"18130055568","wxcode":null,"realname":"MQ","photo":null,"provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":null,"professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}},{"id":1,"userid":1,"content":"我就是我，不一样的烟火！","createtime":1529309413000,"imgs":"[\"2018/06/24/187253143e954a3b906906b58a7b1217.jpg\"]","photolist":["http://47.101.46.2/2018/06/24/187253143e954a3b906906b58a7b1217.jpg"],"user":{"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}}]
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
             * id : 6
             * userid : 1
             * content : 测试下
             * createtime : 1529810581000
             * imgs : ["2018/06/24/ca9e4a4108fc4a4fb7a5958480524119.jpg"]
             * photolist : ["http://47.101.46.2/2018/06/24/ca9e4a4108fc4a4fb7a5958480524119.jpg"]
             * user : {"id":1,"username":"18130055543","mobile":"18130055543","wxcode":null,"realname":"Lisa","photo":"2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg","provinceid":null,"cityid":null,"msalary":null,"dsalary":null,"addsalary":null,"birthday":null,"sign":"www","professionid":null,"professionlevelid":null,"locked":null,"type":null,"createtime":null,"projectnum":null,"roles":null}
             */

            private int id;
            private int userid;
            private String content;
            private String createtime;
            private String imgs;
            private UserBean user;
            private List<String> photolist;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<String> getPhotolist() {
                return photolist;
            }

            public void setPhotolist(List<String> photolist) {
                this.photolist = photolist;
            }

            public static class UserBean {
                /**
                 * id : 1
                 * username : 18130055543
                 * mobile : 18130055543
                 * wxcode : null
                 * realname : Lisa
                 * photo : 2018/06/20/650d706912074f6ab5d9922e29b4efe5.jpg
                 * provinceid : null
                 * cityid : null
                 * msalary : null
                 * dsalary : null
                 * addsalary : null
                 * birthday : null
                 * sign : www
                 * professionid : null
                 * professionlevelid : null
                 * locked : null
                 * type : null
                 * createtime : null
                 * projectnum : null
                 * roles : null
                 */

                private int id;
                private String username;
                private String mobile;
                private Object wxcode;
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
                private Object locked;
                private Object type;
                private Object createtime;
                private Object projectnum;
                private Object roles;

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
            }
        }
    }
}
