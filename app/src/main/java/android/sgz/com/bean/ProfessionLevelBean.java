package android.sgz.com.bean;

import java.util.List;

/**
 * Created by 92457 on 2018/6/7.
 */

public class ProfessionLevelBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":1,"lable":"professionlevel","code":"junior","type":"professionLevel","descript":"初级职称","createtime":1527820492000,"sort":1,"status":1},{"id":2,"lable":"professionlevel","code":"middle","type":"professionLevel","descript":"中级职称","createtime":1527820495000,"sort":2,"status":1},{"id":3,"lable":"professionlevel","code":"senior","type":"professionLevel","descript":"高级职称","createtime":1527820531000,"sort":3,"status":1}]
     * exception : false
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private boolean exception;
    private List<DataBean> data;

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

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * lable : professionlevel
         * code : junior
         * type : professionLevel
         * descript : 初级职称
         * createtime : 1527820492000
         * sort : 1
         * status : 1
         */

        private int id;
        private String lable;
        private String code;
        private String type;
        private String descript;
        private long createtime;
        private int sort;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
