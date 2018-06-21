package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/21.
 */

public class RefreshTokenBean {

    /**
     * resultMsg : eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTI5NjUwMDA0fQ.Za0o9V5FwPFXnEkBXoHHQ9Ed9H3SYInwSasZtWycXHTJF6sXEaQD2V9xpUE91kFa663yM-2scWOqiT-BBakwaA
     * refreshMsg : N8/GnhJEha2uBP0518bAbQ==
     * success : true
     * resultCode : 1
     */

    private String resultMsg;
    private String refreshMsg;
    private boolean success;
    private String resultCode;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getRefreshMsg() {
        return refreshMsg;
    }

    public void setRefreshMsg(String refreshMsg) {
        this.refreshMsg = refreshMsg;
    }

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
}
