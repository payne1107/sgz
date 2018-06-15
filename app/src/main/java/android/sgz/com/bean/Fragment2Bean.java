package android.sgz.com.bean;

import java.util.List;

/**
 * Created by WD on 2018/6/15.
 */

public class Fragment2Bean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"curPage":1,"coutpage":1,"pageSize":15,"list":[{"id":1,"title":"电建一公司安全培训","cover":null,"video":null,"isfree":1,"price":null,"userid":1,"createtime":1528190616000},{"id":3,"title":"民建基础","cover":null,"video":null,"isfree":0,"price":null,"userid":3,"createtime":1528354609000},{"id":4,"title":"路桥基础教程","cover":null,"video":null,"isfree":1,"price":20,"userid":6,"createtime":1528354616000},{"id":5,"title":"建筑师素养教程","cover":null,"video":null,"isfree":0,"price":null,"userid":7,"createtime":1528360515000},{"id":6,"title":"个人素养教程","cover":null,"video":null,"isfree":0,"price":null,"userid":1,"createtime":1528420903000}]}
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
         * list : [{"id":1,"title":"电建一公司安全培训","cover":null,"video":null,"isfree":1,"price":null,"userid":1,"createtime":1528190616000},{"id":3,"title":"民建基础","cover":null,"video":null,"isfree":0,"price":null,"userid":3,"createtime":1528354609000},{"id":4,"title":"路桥基础教程","cover":null,"video":null,"isfree":1,"price":20,"userid":6,"createtime":1528354616000},{"id":5,"title":"建筑师素养教程","cover":null,"video":null,"isfree":0,"price":null,"userid":7,"createtime":1528360515000},{"id":6,"title":"个人素养教程","cover":null,"video":null,"isfree":0,"price":null,"userid":1,"createtime":1528420903000}]
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
             * title : 电建一公司安全培训
             * cover : null
             * video : null
             * isfree : 1
             * price : null
             * userid : 1
             * createtime : 1528190616000
             */

            private int id;
            private String title;
            private String cover;
            private String video;
            private int isfree;
            private String price;
            private int userid;
            private long createtime;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public int getIsfree() {
                return isfree;
            }

            public void setIsfree(int isfree) {
                this.isfree = isfree;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }
        }
    }
}
