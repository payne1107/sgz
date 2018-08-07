package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/28.
 */

public class WorkRecordByTimeBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"workRecord":{"id":58,"userid":1,"projectid":3,"startrecordtime":"2018-07-29 11:16:47","endrecordtime":null,"startrecordaddress":"合肥市茗香苑(茗香路)","endrecordaddress":null,"startlat":31.82097,"startlng":117.247413,"endlat":null,"endlng":null,"daysalary":0,"allowance":0,"createtime":"2018-07-29","startstatus":2,"endstatus":4,"remark":null,"operaremark":null},"extraworkRecords":[{"id":37,"userid":1,"projectid":3,"starttime":"2018-07-29 16:00","endtime":"2018-07-29 21:00","createtime":"2018-07-29 17:20:10","extraworkmoney":250,"extraworktime":5,"status":1,"approveuserid":1,"approvetime":"2018-07-29 17:20:53"}]}
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
         * workRecord : {"id":58,"userid":1,"projectid":3,"startrecordtime":"2018-07-29 11:16:47","endrecordtime":null,"startrecordaddress":"合肥市茗香苑(茗香路)","endrecordaddress":null,"startlat":31.82097,"startlng":117.247413,"endlat":null,"endlng":null,"daysalary":0,"allowance":0,"createtime":"2018-07-29","startstatus":2,"endstatus":4,"remark":null,"operaremark":null}
         * extraworkRecords : [{"id":37,"userid":1,"projectid":3,"starttime":"2018-07-29 16:00","endtime":"2018-07-29 21:00","createtime":"2018-07-29 17:20:10","extraworkmoney":250,"extraworktime":5,"status":1,"approveuserid":1,"approvetime":"2018-07-29 17:20:53"}]
         */

        private WorkRecordBean workRecord;
        private List<ExtraworkRecordsBean> extraworkRecords;

        public WorkRecordBean getWorkRecord() {
            return workRecord;
        }

        public void setWorkRecord(WorkRecordBean workRecord) {
            this.workRecord = workRecord;
        }

        public List<ExtraworkRecordsBean> getExtraworkRecords() {
            return extraworkRecords;
        }

        public void setExtraworkRecords(List<ExtraworkRecordsBean> extraworkRecords) {
            this.extraworkRecords = extraworkRecords;
        }

        public static class WorkRecordBean {
            /**
             * id : 58
             * userid : 1
             * projectid : 3
             * startrecordtime : 2018-07-29 11:16:47
             * endrecordtime : null
             * startrecordaddress : 合肥市茗香苑(茗香路)
             * endrecordaddress : null
             * startlat : 31.82097
             * startlng : 117.247413
             * endlat : null
             * endlng : null
             * daysalary : 0
             * allowance : 0
             * createtime : 2018-07-29
             * startstatus : 2
             * endstatus : 4
             * remark : null
             * operaremark : null
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
            private String remark;
            private String operaremark;

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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getOperaremark() {
                return operaremark;
            }

            public void setOperaremark(String operaremark) {
                this.operaremark = operaremark;
            }
        }

        public static class ExtraworkRecordsBean {
            /**
             * id : 37
             * userid : 1
             * projectid : 3
             * starttime : 2018-07-29 16:00
             * endtime : 2018-07-29 21:00
             * createtime : 2018-07-29 17:20:10
             * extraworkmoney : 250
             * extraworktime : 5
             * status : 1
             * approveuserid : 1
             * approvetime : 2018-07-29 17:20:53
             */

            private int id;
            private int userid;
            private int projectid;
            private String starttime;
            private String endtime;
            private String createtime;
            private int extraworkmoney;
            private int extraworktime;
            private int status;
            private int approveuserid;
            private String approvetime;

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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getExtraworkmoney() {
                return extraworkmoney;
            }

            public void setExtraworkmoney(int extraworkmoney) {
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

            public int getApproveuserid() {
                return approveuserid;
            }

            public void setApproveuserid(int approveuserid) {
                this.approveuserid = approveuserid;
            }

            public String getApprovetime() {
                return approvetime;
            }

            public void setApprovetime(String approvetime) {
                this.approvetime = approvetime;
            }
        }
    }
}
