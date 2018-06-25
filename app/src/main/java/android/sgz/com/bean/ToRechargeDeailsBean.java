package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/25.
 */

public class ToRechargeDeailsBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":7,"userid":1,"fromuserid":null,"type":3,"money":1000,"tjmoney":1000,"userflowno":"UFLOW1806241959590715M1","createtime":"2018-06-24 20:00:00","updatetime":null,"remark":null,"status":1,"sender":null,"receiver":"Lisa"}]}
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
         * list : [{"id":7,"userid":1,"fromuserid":null,"type":3,"money":1000,"tjmoney":1000,"userflowno":"UFLOW1806241959590715M1","createtime":"2018-06-24 20:00:00","updatetime":null,"remark":null,"status":1,"sender":null,"receiver":"Lisa"}]
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
             * id : 7
             * userid : 1
             * fromuserid : null
             * type : 3
             * money : 1000
             * tjmoney : 1000
             * userflowno : UFLOW1806241959590715M1
             * createtime : 2018-06-24 20:00:00
             * updatetime : null
             * remark : null
             * status : 1
             * sender : null
             * receiver : Lisa
             */

            private int id;
            private int userid;
            private Object fromuserid;
            private int type;
            private String money;
            private String tjmoney;
            private String userflowno;
            private String createtime;
            private Object updatetime;
            private Object remark;
            private int status;
            private Object sender;
            private String receiver;

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

            public Object getFromuserid() {
                return fromuserid;
            }

            public void setFromuserid(Object fromuserid) {
                this.fromuserid = fromuserid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTjmoney() {
                return tjmoney;
            }

            public void setTjmoney(String tjmoney) {
                this.tjmoney = tjmoney;
            }

            public String getUserflowno() {
                return userflowno;
            }

            public void setUserflowno(String userflowno) {
                this.userflowno = userflowno;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public Object getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(Object updatetime) {
                this.updatetime = updatetime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getSender() {
                return sender;
            }

            public void setSender(Object sender) {
                this.sender = sender;
            }

            public String getReceiver() {
                return receiver;
            }

            public void setReceiver(String receiver) {
                this.receiver = receiver;
            }
        }
    }
}
