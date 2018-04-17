package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/3/19.
 */

public class UploadImgBean {


    /**
     * flag : 0
     * data : {"path":"http://120.79.0.163:206//upload/20180319/1ad28500e32640e3b6e0c0471b6863f8.png"}
     * msg : null
     */

    private int flag;
    private DataBean data;
    private Object msg;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * path : http://120.79.0.163:206//upload/20180319/1ad28500e32640e3b6e0c0471b6863f8.png
         */

        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
