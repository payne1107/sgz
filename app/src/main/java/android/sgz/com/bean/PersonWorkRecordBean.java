package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/7/5.
 */

public class PersonWorkRecordBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"userid":1,"projectid":1,"startrecordtime":"2018-06-04 07:29:08","endrecordtime":"2018-06-04 17:32:18","startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":300,"allowance":30,"createtime":null,"startstatus":1,"endstatus":1},{"id":3,"userid":1,"projectid":1,"startrecordtime":"2018-06-02 08:24:57","endrecordtime":"2018-06-02 17:34:38","startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":240,"allowance":30,"createtime":null,"startstatus":2,"endstatus":1},{"id":5,"userid":1,"projectid":1,"startrecordtime":"2018-06-24 05:20:11","endrecordtime":"2018-06-24 15:01:48","startrecordaddress":"安徽合肥新城国际","endrecordaddress":"合肥市中共十里店社区支部委员会","startlat":31.821125,"startlng":117.247574,"endlat":31.859879,"endlng":117.223441,"daysalary":640,"allowance":30,"createtime":null,"startstatus":1,"endstatus":3},{"id":6,"userid":1,"projectid":1,"startrecordtime":"2017-01-18 07:26:19","endrecordtime":"2017-01-18 17:44:43","startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":300,"allowance":30,"createtime":null,"startstatus":1,"endstatus":1}]}
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
         * list : [{"id":1,"userid":1,"projectid":1,"startrecordtime":"2018-06-04 07:29:08","endrecordtime":"2018-06-04 17:32:18","startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":300,"allowance":30,"createtime":null,"startstatus":1,"endstatus":1},{"id":3,"userid":1,"projectid":1,"startrecordtime":"2018-06-02 08:24:57","endrecordtime":"2018-06-02 17:34:38","startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":240,"allowance":30,"createtime":null,"startstatus":2,"endstatus":1},{"id":5,"userid":1,"projectid":1,"startrecordtime":"2018-06-24 05:20:11","endrecordtime":"2018-06-24 15:01:48","startrecordaddress":"安徽合肥新城国际","endrecordaddress":"合肥市中共十里店社区支部委员会","startlat":31.821125,"startlng":117.247574,"endlat":31.859879,"endlng":117.223441,"daysalary":640,"allowance":30,"createtime":null,"startstatus":1,"endstatus":3},{"id":6,"userid":1,"projectid":1,"startrecordtime":"2017-01-18 07:26:19","endrecordtime":"2017-01-18 17:44:43","startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":300,"allowance":30,"createtime":null,"startstatus":1,"endstatus":1}]
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
             * userid : 1
             * projectid : 1
             * startrecordtime : 2018-06-04 07:29:08
             * endrecordtime : 2018-06-04 17:32:18
             * startrecordaddress : null
             * endrecordaddress : null
             * startlat : null
             * startlng : null
             * endlat : null
             * endlng : null
             * daysalary : 300
             * allowance : 30
             * createtime : null
             * startstatus : 1
             * endstatus : 1
             */

            private int id;
            private int userid;
            private int projectid;
            private String startrecordtime;
            private String endrecordtime;
            private String startrecordaddress;
            private String endrecordaddress;
            private String startlat;
            private String startlng;
            private String endlat;
            private String endlng;
            private int daysalary;
            private int allowance;
            private String createtime;
            private int startstatus;
            private int endstatus;

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

            public int getProjectid() {
                return projectid;
            }

            public void setProjectid(int projectid) {
                this.projectid = projectid;
            }

            public String getStartrecordtime() {
                return startrecordtime;
            }

            public void setStartrecordtime(String startrecordtime) {
                this.startrecordtime = startrecordtime;
            }

            public String getEndrecordtime() {
                return endrecordtime;
            }

            public void setEndrecordtime(String endrecordtime) {
                this.endrecordtime = endrecordtime;
            }

            public String getStartrecordaddress() {
                return startrecordaddress;
            }

            public void setStartrecordaddress(String startrecordaddress) {
                this.startrecordaddress = startrecordaddress;
            }

            public String getEndrecordaddress() {
                return endrecordaddress;
            }

            public void setEndrecordaddress(String endrecordaddress) {
                this.endrecordaddress = endrecordaddress;
            }

            public String getStartlat() {
                return startlat;
            }

            public void setStartlat(String startlat) {
                this.startlat = startlat;
            }

            public String getStartlng() {
                return startlng;
            }

            public void setStartlng(String startlng) {
                this.startlng = startlng;
            }

            public String getEndlat() {
                return endlat;
            }

            public void setEndlat(String endlat) {
                this.endlat = endlat;
            }

            public String getEndlng() {
                return endlng;
            }

            public void setEndlng(String endlng) {
                this.endlng = endlng;
            }

            public int getDaysalary() {
                return daysalary;
            }

            public void setDaysalary(int daysalary) {
                this.daysalary = daysalary;
            }

            public int getAllowance() {
                return allowance;
            }

            public void setAllowance(int allowance) {
                this.allowance = allowance;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getStartstatus() {
                return startstatus;
            }

            public void setStartstatus(int startstatus) {
                this.startstatus = startstatus;
            }

            public int getEndstatus() {
                return endstatus;
            }

            public void setEndstatus(int endstatus) {
                this.endstatus = endstatus;
            }
        }
    }
}
