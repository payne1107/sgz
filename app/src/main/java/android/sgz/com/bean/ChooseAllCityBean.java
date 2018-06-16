package android.sgz.com.bean;

import java.util.List;

/**
 * Created by 92457 on 2018/6/16.
 */

public class ChooseAllCityBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : [{"id":110000,"name":"北京市","sort":null,"cityid":null,"cityname":null,"cityList":[{"id":110100,"provinceid":null,"name":"市辖区","sort":null},{"id":110200,"provinceid":null,"name":"县","sort":null}]},{"id":130000,"name":"河北省","sort":null,"cityid":null,"cityname":null,"cityList":[{"id":130100,"provinceid":null,"name":"石家庄市","sort":null},{"id":130200,"provinceid":null,"name":"唐山市","sort":null}]},{"id":140000,"name":"山西省","sort":null,"cityid":null,"cityname":null,"cityList":[{"id":140100,"provinceid":null,"name":"太原市","sort":null},{"id":141100,"provinceid":null,"name":"吕梁市","sort":null}]}]
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
         * id : 110000
         * name : 北京市
         * sort : null
         * cityid : null
         * cityname : null
         * cityList : [{"id":110100,"provinceid":null,"name":"市辖区","sort":null},{"id":110200,"provinceid":null,"name":"县","sort":null}]
         */

        private int id;
        private String name;
        private String sort;
        private String cityid;
        private String cityname;
        private List<CityListBean> cityList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public List<CityListBean> getCityList() {
            return cityList;
        }

        public void setCityList(List<CityListBean> cityList) {
            this.cityList = cityList;
        }

        public static class CityListBean {
            /**
             * id : 110100
             * provinceid : null
             * name : 市辖区
             * sort : null
             */

            private int id;
            private String provinceid;
            private String name;
            private String sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProvinceid() {
                return provinceid;
            }

            public void setProvinceid(String provinceid) {
                this.provinceid = provinceid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }
        }
    }
}
