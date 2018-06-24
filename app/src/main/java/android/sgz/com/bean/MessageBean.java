package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/24.
 */

public class MessageBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"title":"测试标题","content":"测试测试","sender":2,"receiver":1,"ifread":0,"createtime":"2018-06-24 10:35"}]}
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
         * list : [{"id":1,"title":"测试标题","content":"测试测试","sender":2,"receiver":1,"ifread":0,"createtime":"2018-06-24 10:35"}]
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
             * title : 测试标题
             * content : 测试测试
             * sender : 2
             * receiver : 1
             * ifread : 0
             * createtime : 2018-06-24 10:35
             */

            private int id;
            private String title;
            private String content;
            private int sender;
            private int receiver;
            private int ifread;
            private String createtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getSender() {
                return sender;
            }

            public void setSender(int sender) {
                this.sender = sender;
            }

            public int getReceiver() {
                return receiver;
            }

            public void setReceiver(int receiver) {
                this.receiver = receiver;
            }

            public int getIfread() {
                return ifread;
            }

            public void setIfread(int ifread) {
                this.ifread = ifread;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }
        }
    }
}
