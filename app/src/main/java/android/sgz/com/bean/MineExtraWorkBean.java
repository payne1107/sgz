package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/19.
 */

public class MineExtraWorkBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"userid":null,"projectid":null,"starttime":1527935756000,"endtime":1527942969000,"extraworkmoney":50,"extraworktime":2,"status":1,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"},{"id":2,"userid":null,"projectid":null,"starttime":1528022217000,"endtime":1528033003000,"extraworkmoney":75,"extraworktime":3,"status":1,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"},{"id":3,"userid":null,"projectid":null,"starttime":1528800300000,"endtime":1528811100000,"extraworkmoney":75,"extraworktime":3,"status":1,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"},{"id":5,"userid":null,"projectid":null,"starttime":1528886700000,"endtime":1528898400000,"extraworkmoney":0,"extraworktime":null,"status":2,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"}]}
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
         * list : [{"id":1,"userid":null,"projectid":null,"starttime":1527935756000,"endtime":1527942969000,"extraworkmoney":50,"extraworktime":2,"status":1,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"},{"id":2,"userid":null,"projectid":null,"starttime":1528022217000,"endtime":1528033003000,"extraworkmoney":75,"extraworktime":3,"status":1,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"},{"id":3,"userid":null,"projectid":null,"starttime":1528800300000,"endtime":1528811100000,"extraworkmoney":75,"extraworktime":3,"status":1,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"},{"id":5,"userid":null,"projectid":null,"starttime":1528886700000,"endtime":1528898400000,"extraworkmoney":0,"extraworktime":null,"status":2,"approveuserid":null,"approvetime":null,"projectname":"绿怡居改造工程","workname":null,"overworksalary":25,"projectleadid":null,"projectleadname":"Lisa"}]
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
             * userid : null
             * projectid : null
             * starttime : 1527935756000
             * endtime : 1527942969000
             * extraworkmoney : 50.0
             * extraworktime : 2
             * status : 1
             * approveuserid : null
             * approvetime : null
             * projectname : 绿怡居改造工程
             * workname : null
             * overworksalary : 25.0
             * projectleadid : null
             * projectleadname : Lisa
             */

            private int id;
            private Object userid;
            private Object projectid;
            private String starttime;
            private String endtime;
            private double extraworkmoney;
            private int extraworktime;
            private int status;
            private Object approveuserid;
            private Object approvetime;
            private String projectname;
            private Object workname;
            private double overworksalary;
            private Object projectleadid;
            private String projectleadname;
            private String remark;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getUserid() {
                return userid;
            }

            public void setUserid(Object userid) {
                this.userid = userid;
            }

            public Object getProjectid() {
                return projectid;
            }

            public void setProjectid(Object projectid) {
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

            public Object getApproveuserid() {
                return approveuserid;
            }

            public void setApproveuserid(Object approveuserid) {
                this.approveuserid = approveuserid;
            }

            public Object getApprovetime() {
                return approvetime;
            }

            public void setApprovetime(Object approvetime) {
                this.approvetime = approvetime;
            }

            public String getProjectname() {
                return projectname;
            }

            public void setProjectname(String projectname) {
                this.projectname = projectname;
            }

            public Object getWorkname() {
                return workname;
            }

            public void setWorkname(Object workname) {
                this.workname = workname;
            }

            public double getOverworksalary() {
                return overworksalary;
            }

            public void setOverworksalary(double overworksalary) {
                this.overworksalary = overworksalary;
            }

            public Object getProjectleadid() {
                return projectleadid;
            }

            public void setProjectleadid(Object projectleadid) {
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
