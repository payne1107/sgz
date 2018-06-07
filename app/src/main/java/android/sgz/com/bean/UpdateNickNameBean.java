package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/7.
 */

public class UpdateNickNameBean {

    /**
     * success : true
     * resultCode : 1
     * resultMsg : 修改成功
     * data : null
     * exception : false
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }
}
