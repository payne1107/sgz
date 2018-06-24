package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class PaymentByProjectBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"projectid":1,"projectname":"绿怡居改造工程","address":"高河东路绿地蓝海C座","starttime":1530428140000,"workercount":3,"allworkdays":5,"alladdworkdays":4,"avgsalay":360,"allsalay":1590,"allpaysalary":1500},{"projectid":3,"projectname":"测试2","address":"安徽合肥政务区新城国际","starttime":1529337600000,"workercount":1,"allworkdays":0,"alladdworkdays":0,"avgsalay":220,"allsalay":null,"allpaysalary":0}]}
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
         * list : [{"projectid":1,"projectname":"绿怡居改造工程","address":"高河东路绿地蓝海C座","starttime":1530428140000,"workercount":3,"allworkdays":5,"alladdworkdays":4,"avgsalay":360,"allsalay":1590,"allpaysalary":1500},{"projectid":3,"projectname":"测试2","address":"安徽合肥政务区新城国际","starttime":1529337600000,"workercount":1,"allworkdays":0,"alladdworkdays":0,"avgsalay":220,"allsalay":null,"allpaysalary":0}]
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
             * projectid : 1
             * projectname : 绿怡居改造工程
             * address : 高河东路绿地蓝海C座
             * starttime : 1530428140000
             * workercount : 3
             * allworkdays : 5
             * alladdworkdays : 4
             * avgsalay : 360
             * allsalay : 1590
             * allpaysalary : 1500
             */

            private int projectid;
            private String projectname;
            private String address;
            private String starttime;
            private int workercount;
            private int allworkdays;
            private int alladdworkdays;
            private String avgsalay;
            private String allsalay;
            private String allpaysalary;

            public int getProjectid() {
                return projectid;
            }

            public void setProjectid(int projectid) {
                this.projectid = projectid;
            }

            public String getProjectname() {
                return projectname;
            }

            public void setProjectname(String projectname) {
                this.projectname = projectname;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public int getWorkercount() {
                return workercount;
            }

            public void setWorkercount(int workercount) {
                this.workercount = workercount;
            }

            public int getAllworkdays() {
                return allworkdays;
            }

            public void setAllworkdays(int allworkdays) {
                this.allworkdays = allworkdays;
            }

            public int getAlladdworkdays() {
                return alladdworkdays;
            }

            public void setAlladdworkdays(int alladdworkdays) {
                this.alladdworkdays = alladdworkdays;
            }

            public String getAvgsalay() {
                return avgsalay;
            }

            public void setAvgsalay(String avgsalay) {
                this.avgsalay = avgsalay;
            }

            public String getAllsalay() {
                return allsalay;
            }

            public void setAllsalay(String allsalay) {
                this.allsalay = allsalay;
            }

            public String getAllpaysalary() {
                return allpaysalary;
            }

            public void setAllpaysalary(String allpaysalary) {
                this.allpaysalary = allpaysalary;
            }
        }
    }
}
