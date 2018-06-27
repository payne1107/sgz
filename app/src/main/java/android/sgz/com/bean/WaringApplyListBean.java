package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/27.
 */

public class WaringApplyListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"user":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":2,"professionlevelid":2,"type":"member","projectnum":null},"remark":"上班补卡申请","replay":"下次请记得打卡","project":{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":1,"regulatoryagency":"合肥一建","headman":"王兵兵","mobile":"15155148936","address":"高河东路绿地蓝海C座","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":"2018-06-01 14:53","starttime":"2018-07-01 14:55","startworktime":"07:30","endworktime":"17:30","status":0,"worknum":30,"merchantid":2},"handleuserid":1,"createtime":1530001955000,"status":1,"type":1,"applytime":1529942400000,"handletime":1530005072000}]}
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
         * list : [{"id":1,"user":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":2,"professionlevelid":2,"type":"member","projectnum":null},"remark":"上班补卡申请","replay":"下次请记得打卡","project":{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":1,"regulatoryagency":"合肥一建","headman":"王兵兵","mobile":"15155148936","address":"高河东路绿地蓝海C座","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":"2018-06-01 14:53","starttime":"2018-07-01 14:55","startworktime":"07:30","endworktime":"17:30","status":0,"worknum":30,"merchantid":2},"handleuserid":1,"createtime":1530001955000,"status":1,"type":1,"applytime":1529942400000,"handletime":1530005072000}]
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
             * id : 1
             * user : {"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":2,"professionlevelid":2,"type":"member","projectnum":null}
             * remark : 上班补卡申请
             * replay : 下次请记得打卡
             * project : {"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":1,"regulatoryagency":"合肥一建","headman":"王兵兵","mobile":"15155148936","address":"高河东路绿地蓝海C座","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":"2018-06-01 14:53","starttime":"2018-07-01 14:55","startworktime":"07:30","endworktime":"17:30","status":0,"worknum":30,"merchantid":2}
             * handleuserid : 1
             * createtime : 1530001955000
             * status : 1
             * type : 1
             * applytime : 1529942400000
             * handletime : 1530005072000
             */

            private int id;
            private UserBean user;
            private String remark;
            private String replay;
            private ProjectBean project;
            private int handleuserid;
            private long createtime;
            private int status;
            private int type;
            private long applytime;
            private long handletime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getReplay() {
                return replay;
            }

            public void setReplay(String replay) {
                this.replay = replay;
            }

            public ProjectBean getProject() {
                return project;
            }

            public void setProject(ProjectBean project) {
                this.project = project;
            }

            public int getHandleuserid() {
                return handleuserid;
            }

            public void setHandleuserid(int handleuserid) {
                this.handleuserid = handleuserid;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getApplytime() {
                return applytime;
            }

            public void setApplytime(long applytime) {
                this.applytime = applytime;
            }

            public long getHandletime() {
                return handletime;
            }

            public void setHandletime(long handletime) {
                this.handletime = handletime;
            }

            public static class UserBean {
                /**
                 * id : 1
                 * username : 18130055543
                 * mobile : 18130055543
                 * realname : 369
                 * photo : 2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png
                 * provinceid : 320000
                 * cityid : 320100
                 * msalary : 10000.00
                 * dsalary : 300.00
                 * addsalary : 30.00
                 * birthday : 1988-07-02 23:00
                 * sign : 6来看看
                 * professionid : 2
                 * professionlevelid : 2
                 * type : member
                 * projectnum : null
                 */

                private int id;
                private String username;
                private String mobile;
                private String realname;
                private String photo;
                private int provinceid;
                private int cityid;
                private String msalary;
                private String dsalary;
                private String addsalary;
                private String birthday;
                private String sign;
                private int professionid;
                private int professionlevelid;
                private String type;
                private Object projectnum;

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

                public int getProvinceid() {
                    return provinceid;
                }

                public void setProvinceid(int provinceid) {
                    this.provinceid = provinceid;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
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

                public int getProfessionid() {
                    return professionid;
                }

                public void setProfessionid(int professionid) {
                    this.professionid = professionid;
                }

                public int getProfessionlevelid() {
                    return professionlevelid;
                }

                public void setProfessionlevelid(int professionlevelid) {
                    this.professionlevelid = professionlevelid;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Object getProjectnum() {
                    return projectnum;
                }

                public void setProjectnum(Object projectnum) {
                    this.projectnum = projectnum;
                }
            }

            public static class ProjectBean {
                /**
                 * id : 1
                 * userid : 1
                 * name : 绿怡居改造工程
                 * categoryid : 1
                 * regulatoryagency : 合肥一建
                 * headman : 王兵兵
                 * mobile : 15155148936
                 * address : 高河东路绿地蓝海C座
                 * clockrange : 500
                 * lng : 117.247574
                 * lat : 31.821125
                 * createtime : 2018-06-01 14:53
                 * starttime : 2018-07-01 14:55
                 * startworktime : 07:30
                 * endworktime : 17:30
                 * status : 0
                 * worknum : 30
                 * merchantid : 2
                 */

                private int id;
                private int userid;
                private String name;
                private int categoryid;
                private String regulatoryagency;
                private String headman;
                private String mobile;
                private String address;
                private int clockrange;
                private double lng;
                private double lat;
                private String createtime;
                private String starttime;
                private String startworktime;
                private String endworktime;
                private int status;
                private int worknum;
                private int merchantid;

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getCategoryid() {
                    return categoryid;
                }

                public void setCategoryid(int categoryid) {
                    this.categoryid = categoryid;
                }

                public String getRegulatoryagency() {
                    return regulatoryagency;
                }

                public void setRegulatoryagency(String regulatoryagency) {
                    this.regulatoryagency = regulatoryagency;
                }

                public String getHeadman() {
                    return headman;
                }

                public void setHeadman(String headman) {
                    this.headman = headman;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getClockrange() {
                    return clockrange;
                }

                public void setClockrange(int clockrange) {
                    this.clockrange = clockrange;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getStarttime() {
                    return starttime;
                }

                public void setStarttime(String starttime) {
                    this.starttime = starttime;
                }

                public String getStartworktime() {
                    return startworktime;
                }

                public void setStartworktime(String startworktime) {
                    this.startworktime = startworktime;
                }

                public String getEndworktime() {
                    return endworktime;
                }

                public void setEndworktime(String endworktime) {
                    this.endworktime = endworktime;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getWorknum() {
                    return worknum;
                }

                public void setWorknum(int worknum) {
                    this.worknum = worknum;
                }

                public int getMerchantid() {
                    return merchantid;
                }

                public void setMerchantid(int merchantid) {
                    this.merchantid = merchantid;
                }
            }
        }
    }
}
