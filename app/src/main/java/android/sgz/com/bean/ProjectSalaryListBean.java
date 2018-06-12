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
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":840,"addsalary":200,"allowance":90,"addtime":8,"workdays":3,"workfriends":null,"projectcount":null,"income":null}]}
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
         * list : [{"id":1,"userid":1,"name":"绿怡居改造工程","categoryid":null,"regulatoryagency":null,"headman":null,"mobile":null,"address":null,"clockrange":null,"lng":null,"lat":null,"createtime":null,"starttime":null,"startworktime":null,"endworktime":null,"status":null,"worknum":null,"merchantid":null,"categoryname":null,"merchantname":null,"salary":840,"addsalary":200,"allowance":90,"addtime":8,"workdays":3,"workfriends":null,"projectcount":null,"income":null}]
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
             * name : 绿怡居改造工程
             * categoryid : null
             * regulatoryagency : null
             * headman : null
             * mobile : null
             * address : null
             * clockrange : null
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
             * salary : 840.0
             * addsalary : 200.0
             * allowance : 90.0
             * addtime : 8
             * workdays : 3
             * workfriends : null
             * projectcount : null
             * income : null
             */

            private int id;
            private int userid;
            private String name;
            private String categoryid;
            private String regulatoryagency;
            private String headman;
            private String mobile;
            private String address;
            private String clockrange;
            private String lng;
            private String lat;
            private String createtime;
            private String starttime;
            private String startworktime;
            private String endworktime;
            private String status;
            private String worknum;
            private String merchantid;
            private String categoryname;
            private String merchantname;
            private double salary;
            private String addsalary;
            private double allowance;
            private int addtime;
            private int workdays;
            private String workfriends;
            private String projectcount;
            private String income;

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

            public String getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(String categoryid) {
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

            public String getClockrange() {
                return clockrange;
            }

            public void setClockrange(String clockrange) {
                this.clockrange = clockrange;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getWorknum() {
                return worknum;
            }

            public void setWorknum(String worknum) {
                this.worknum = worknum;
            }

            public String getMerchantid() {
                return merchantid;
            }

            public void setMerchantid(String merchantid) {
                this.merchantid = merchantid;
            }

            public String getCategoryname() {
                return categoryname;
            }

            public void setCategoryname(String categoryname) {
                this.categoryname = categoryname;
            }

            public String getMerchantname() {
                return merchantname;
            }

            public void setMerchantname(String merchantname) {
                this.merchantname = merchantname;
            }

            public double getSalary() {
                return salary;
            }

            public void setSalary(double salary) {
                this.salary = salary;
            }

            public String getAddsalary() {
                return addsalary;
            }

            public void setAddsalary(String addsalary) {
                this.addsalary = addsalary;
            }

            public double getAllowance() {
                return allowance;
            }

            public void setAllowance(double allowance) {
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

            public String getWorkfriends() {
                return workfriends;
            }

            public void setWorkfriends(String workfriends) {
                this.workfriends = workfriends;
            }

            public String getProjectcount() {
                return projectcount;
            }

            public void setProjectcount(String projectcount) {
                this.projectcount = projectcount;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }
        }
    }
}
