package android.sgz.com.bean;

import java.util.List;

/**
 * Created by 92457 on 2018/6/16.
 */

public class TenderListBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"publishuserid":4,"title":"荷叶地社区医院建设","tenderno":"BS154587634524455","procurement":"合肥市荷叶地居委会","tendercompany":"合肥一建","linkman":3,"mobile":"15155148936","content":"这是测试标书，测试标书","starttime":1530416818000,"endtime":1538365525000,"linkname":"系统管理员"}]}
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
         * list : [{"id":1,"publishuserid":4,"title":"荷叶地社区医院建设","tenderno":"BS154587634524455","procurement":"合肥市荷叶地居委会","tendercompany":"合肥一建","linkman":3,"mobile":"15155148936","content":"这是测试标书，测试标书","starttime":1530416818000,"endtime":1538365525000,"linkname":"系统管理员"}]
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
             * publishuserid : 4
             * title : 荷叶地社区医院建设
             * tenderno : BS154587634524455
             * procurement : 合肥市荷叶地居委会
             * tendercompany : 合肥一建
             * linkman : 3
             * mobile : 15155148936
             * content : 这是测试标书，测试标书
             * starttime : 1530416818000
             * endtime : 1538365525000
             * linkname : 系统管理员
             */

            private int id;
            private int publishuserid;
            private String title;
            private String tenderno;
            private String procurement;
            private String tendercompany;
            private int linkman;
            private String mobile;
            private String content;
            private long starttime;
            private long endtime;
            private String linkname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPublishuserid() {
                return publishuserid;
            }

            public void setPublishuserid(int publishuserid) {
                this.publishuserid = publishuserid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTenderno() {
                return tenderno;
            }

            public void setTenderno(String tenderno) {
                this.tenderno = tenderno;
            }

            public String getProcurement() {
                return procurement;
            }

            public void setProcurement(String procurement) {
                this.procurement = procurement;
            }

            public String getTendercompany() {
                return tendercompany;
            }

            public void setTendercompany(String tendercompany) {
                this.tendercompany = tendercompany;
            }

            public int getLinkman() {
                return linkman;
            }

            public void setLinkman(int linkman) {
                this.linkman = linkman;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getStarttime() {
                return starttime;
            }

            public void setStarttime(long starttime) {
                this.starttime = starttime;
            }

            public long getEndtime() {
                return endtime;
            }

            public void setEndtime(long endtime) {
                this.endtime = endtime;
            }

            public String getLinkname() {
                return linkname;
            }

            public void setLinkname(String linkname) {
                this.linkname = linkname;
            }
        }
    }
}
