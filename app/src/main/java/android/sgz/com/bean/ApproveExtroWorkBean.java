package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/21.
 */

public class ApproveExtroWorkBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":2,"userid":null,"projectid":null,"starttime":"2018-06-03 18:36","endtime":"2018-06-03 21:36","extraworkmoney":75,"extraworktime":3,"status":2,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":"Lisa","overworksalary":25,"projectleadid":1,"projectleadname":null},{"id":5,"userid":null,"projectid":null,"starttime":"2018-06-13 18:45","endtime":"2018-06-13 22:00","extraworkmoney":0,"extraworktime":null,"status":2,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":"Lisa","overworksalary":25,"projectleadid":1,"projectleadname":null}]}
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
         * list : [{"id":2,"userid":null,"projectid":null,"starttime":"2018-06-03 18:36","endtime":"2018-06-03 21:36","extraworkmoney":75,"extraworktime":3,"status":2,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":"Lisa","overworksalary":25,"projectleadid":1,"projectleadname":null},{"id":5,"userid":null,"projectid":null,"starttime":"2018-06-13 18:45","endtime":"2018-06-13 22:00","extraworkmoney":0,"extraworktime":null,"status":2,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":"Lisa","overworksalary":25,"projectleadid":1,"projectleadname":null}]
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
             * userid : null
             * projectid : null
             * starttime : 2018-06-03 18:36
             * endtime : 2018-06-03 21:36
             * extraworkmoney : 75.0
             * extraworktime : 3
             * status : 2
             * approveuserid : null
             * approvetime : null
             * projectname : 绿怡居改造工程
             * workname : Lisa
             * overworksalary : 25.0
             * projectleadid : 1
             * projectleadname : null
             */

            private int id;
            private String userid;
            private String projectid;
            private String starttime;
            private String endtime;
            private double extraworkmoney;
            private int extraworktime;
            private int status;
            private String approveuserid;
            private String approvetime;
            private String projectname;
            private String workname;
            private double overworksalary;
            private int projectleadid;
            private String projectleadname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getProjectid() {
                return projectid;
            }

            public void setProjectid(String projectid) {
                this.projectid = projectid;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public double getExtraworkmoney() {
                return extraworkmoney;
            }

            public void setExtraworkmoney(double extraworkmoney) {
                this.extraworkmoney = extraworkmoney;
            }

            public int getExtraworktime() {
                return extraworktime;
            }

            public void setExtraworktime(int extraworktime) {
                this.extraworktime = extraworktime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getApproveuserid() {
                return approveuserid;
            }

            public void setApproveuserid(String approveuserid) {
                this.approveuserid = approveuserid;
            }

            public String getApprovetime() {
                return approvetime;
            }

            public void setApprovetime(String approvetime) {
                this.approvetime = approvetime;
            }

            public String getProjectname() {
                return projectname;
            }

            public void setProjectname(String projectname) {
                this.projectname = projectname;
            }

            public String getWorkname() {
                return workname;
            }

            public void setWorkname(String workname) {
                this.workname = workname;
            }

            public double getOverworksalary() {
                return overworksalary;
            }

            public void setOverworksalary(double overworksalary) {
                this.overworksalary = overworksalary;
            }

            public int getProjectleadid() {
                return projectleadid;
            }

            public void setProjectleadid(int projectleadid) {
                this.projectleadid = projectleadid;
            }

            public String getProjectleadname() {
                return projectleadname;
            }

            public void setProjectleadname(String projectleadname) {
                this.projectleadname = projectleadname;
            }
        }
    }
}
