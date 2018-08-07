package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/8/5.
 */

public class MineApplyOrderListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":2,"applyUser":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":1,"professionlevelid":2,"type":"member","projectnum":null,"referee":null},"project":{"id":3,"userid":1,"name":"测试2","categoryid":null,"regulatoryagency":"测试1","headman":"张三","mobile":null,"address":"安徽合肥政务区新城国际","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":null,"starttime":"2018-06-19 00:00","startworktime":"08:00","endworktime":"18:00","status":null,"worknum":null,"merchantid":null},"createtime":"2018-07-01 21:44","status":2,"handleUser":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":1,"professionlevelid":2,"type":"member","projectnum":null,"referee":null},"replay":null,"handletime":null,"salary":0,"overworksalary":0,"allowance":0}]}
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
         * list : [{"id":2,"applyUser":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":1,"professionlevelid":2,"type":"member","projectnum":null,"referee":null},"project":{"id":3,"userid":1,"name":"测试2","categoryid":null,"regulatoryagency":"测试1","headman":"张三","mobile":null,"address":"安徽合肥政务区新城国际","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":null,"starttime":"2018-06-19 00:00","startworktime":"08:00","endworktime":"18:00","status":null,"worknum":null,"merchantid":null},"createtime":"2018-07-01 21:44","status":2,"handleUser":{"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":1,"professionlevelid":2,"type":"member","projectnum":null,"referee":null},"replay":null,"handletime":null,"salary":0,"overworksalary":0,"allowance":0}]
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
             * id : 2
             * applyUser : {"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":1,"professionlevelid":2,"type":"member","projectnum":null,"referee":null}
             * project : {"id":3,"userid":1,"name":"测试2","categoryid":null,"regulatoryagency":"测试1","headman":"张三","mobile":null,"address":"安徽合肥政务区新城国际","clockrange":500,"lng":117.247574,"lat":31.821125,"createtime":null,"starttime":"2018-06-19 00:00","startworktime":"08:00","endworktime":"18:00","status":null,"worknum":null,"merchantid":null}
             * createtime : 2018-07-01 21:44
             * status : 2
             * handleUser : {"id":1,"username":"18130055543","mobile":"18130055543","realname":"369","photo":"2018/06/26/e4641bf3a3154c669feb79c50dd81de4.png","provinceid":320000,"cityid":320100,"msalary":"10000.00","dsalary":"300.00","addsalary":"30.00","birthday":"1988-07-02 23:00","sign":"6来看看","professionid":1,"professionlevelid":2,"type":"member","projectnum":null,"referee":null}
             * replay : null
             * handletime : null
             * salary : 0
             * overworksalary : 0
             * allowance : 0
             */

            private int id;
            private ApplyUserBean applyUser;
            private ProjectBean project;
            private String createtime;
            private int status;
            private HandleUserBean handleUser;
            private Object replay;
            private Object handletime;
            private int salary;
            private int overworksalary;
            private int allowance;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public ApplyUserBean getApplyUser() {
                return applyUser;
            }

            public void setApplyUser(ApplyUserBean applyUser) {
                this.applyUser = applyUser;
            }

            public ProjectBean getProject() {
                return project;
            }

            public void setProject(ProjectBean project) {
                this.project = project;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public HandleUserBean getHandleUser() {
                return handleUser;
            }

            public void setHandleUser(HandleUserBean handleUser) {
                this.handleUser = handleUser;
            }

            public Object getReplay() {
                return replay;
            }

            public void setReplay(Object replay) {
                this.replay = replay;
            }

            public Object getHandletime() {
                return handletime;
            }

            public void setHandletime(Object handletime) {
                this.handletime = handletime;
            }

            public int getSalary() {
                return salary;
            }

            public void setSalary(int salary) {
                this.salary = salary;
            }

            public int getOverworksalary() {
                return overworksalary;
            }

            public void setOverworksalary(int overworksalary) {
                this.overworksalary = overworksalary;
            }

            public int getAllowance() {
                return allowance;
            }

            public void setAllowance(int allowance) {
                this.allowance = allowance;
            }

            public static class ApplyUserBean {
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
                 * professionid : 1
                 * professionlevelid : 2
                 * type : member
                 * projectnum : null
                 * referee : null
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

                public Object getReferee() {
                    return referee;
                }

                public void setReferee(Object referee) {
                    this.referee = referee;
                }
            }

            public static class ProjectBean {
                /**
                 * id : 3
                 * userid : 1
                 * name : 测试2
                 * categoryid : null
                 * regulatoryagency : 测试1
                 * headman : 张三
                 * mobile : null
                 * address : 安徽合肥政务区新城国际
                 * clockrange : 500
                 * lng : 117.247574
                 * lat : 31.821125
                 * createtime : null
                 * starttime : 2018-06-19 00:00
                 * startworktime : 08:00
                 * endworktime : 18:00
                 * status : null
                 * worknum : null
                 * merchantid : null
                 */

                private int id;
                private int userid;
                private String name;
                private Object categoryid;
                private String regulatoryagency;
                private String headman;
                private Object mobile;
                private String address;
                private int clockrange;
                private double lng;
                private double lat;
                private Object createtime;
                private String starttime;
                private String startworktime;
                private String endworktime;
                private Object status;
                private Object worknum;
                private Object merchantid;

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

                public Object getCategoryid() {
                    return categoryid;
                }

                public void setCategoryid(Object categoryid) {
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

                public Object getMobile() {
                    return mobile;
                }

                public void setMobile(Object mobile) {
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

                public Object getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(Object createtime) {
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

                public Object getStatus() {
                    return status;
                }

                public void setStatus(Object status) {
                    this.status = status;
                }

                public Object getWorknum() {
                    return worknum;
                }

                public void setWorknum(Object worknum) {
                    this.worknum = worknum;
                }

                public Object getMerchantid() {
                    return merchantid;
                }

                public void setMerchantid(Object merchantid) {
                    this.merchantid = merchantid;
                }
            }

            public static class HandleUserBean {
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
                 * professionid : 1
                 * professionlevelid : 2
                 * type : member
                 * projectnum : null
                 * referee : null
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

                public Object getReferee() {
                    return referee;
                }

                public void setReferee(Object referee) {
                    this.referee = referee;
                }
            }
        }
    }
}
