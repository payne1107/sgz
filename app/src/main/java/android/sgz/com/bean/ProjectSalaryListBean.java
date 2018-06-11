package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/11.
 */

public class ProjectSalaryListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"userid":null,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":840,"addsalary":125,"allowance":90,"addtime":5,"workdays":3},{"id":2,"userid":null,"name":"绿怡居改造二期","categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":300,"addsalary":null,"allowance":35,"addtime":null,"workdays":1}]}
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
         * list : [{"id":1,"userid":null,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":840,"addsalary":125,"allowance":90,"addtime":5,"workdays":3},{"id":2,"userid":null,"name":"绿怡居改造二期","categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":300,"addsalary":null,"allowance":35,"addtime":null,"workdays":1}]
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
             * name : 绿怡居改造工程
             * categoryid : null
             * regulatoryagency : null
             * headman : null
             * mobile : null
             * address : null
             * lng : null
             * lat : null
             * createtime : null
             * starttime : null
             * startworktime : null
             * endworktime : null
             * status : null
             * worknum : null
             * merchantid : null
             * categoryname : null
             * merchantname : null
             * salary : 840
             * addsalary : 125
             * allowance : 90
             * addtime : 5
             * workdays : 3
             */

            private int id;
            private Object userid;
            private String name;
            private Object categoryid;
            private Object regulatoryagency;
            private Object headman;
            private Object mobile;
            private Object address;
            private Object lng;
            private Object lat;
            private Object createtime;
            private Object starttime;
            private Object startworktime;
            private Object endworktime;
            private Object status;
            private Object worknum;
            private Object merchantid;
            private Object categoryname;
            private Object merchantname;
            private int salary;
            private int addsalary;
            private int allowance;
            private int addtime;
            private int workdays;

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

            public Object getRegulatoryagency() {
                return regulatoryagency;
            }

            public void setRegulatoryagency(Object regulatoryagency) {
                this.regulatoryagency = regulatoryagency;
            }

            public Object getHeadman() {
                return headman;
            }

            public void setHeadman(Object headman) {
                this.headman = headman;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getLng() {
                return lng;
            }

            public void setLng(Object lng) {
                this.lng = lng;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }

            public Object getStarttime() {
                return starttime;
            }

            public void setStarttime(Object starttime) {
                this.starttime = starttime;
            }

            public Object getStartworktime() {
                return startworktime;
            }

            public void setStartworktime(Object startworktime) {
                this.startworktime = startworktime;
            }

            public Object getEndworktime() {
                return endworktime;
            }

            public void setEndworktime(Object endworktime) {
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

            public Object getCategoryname() {
                return categoryname;
            }

            public void setCategoryname(Object categoryname) {
                this.categoryname = categoryname;
            }

            public Object getMerchantname() {
                return merchantname;
            }

            public void setMerchantname(Object merchantname) {
                this.merchantname = merchantname;
            }

            public int getSalary() {
                return salary;
            }

            public void setSalary(int salary) {
                this.salary = salary;
            }

            public int getAddsalary() {
                return addsalary;
            }

            public void setAddsalary(int addsalary) {
                this.addsalary = addsalary;
            }

            public int getAllowance() {
                return allowance;
            }

            public void setAllowance(int allowance) {
                this.allowance = allowance;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getWorkdays() {
                return workdays;
            }

            public void setWorkdays(int workdays) {
                this.workdays = workdays;
            }
        }
    }
}
