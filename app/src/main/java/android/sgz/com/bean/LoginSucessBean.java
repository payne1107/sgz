package android.sgz.com.bean;

/**
 * Created by 92457 on 2018/6/7.
 */

public class LoginSucessBean {

    /**
     * refreshMsg : D4jtPziJX9SzZGC2TOo93g==
     * resultCode : 1
     * resultMsg : eyJBVVRIT1JJVFkiOiI0Vk1GMm9vYm8vOD0iLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4IiwiZXhwIjoxNTI4NDM5NTQ1fQ.iBcCC8mfnKDyZvKnYi_W6qVmcAxb4LFlzYd63ZLR-BPWg1b5cxYAoHbBsbtHzdO0sVAnue5p93YvjxfDWb4fdA
     * success : true
     */

    private String refreshMsg;
    private String resultCode;
    private String resultMsg;
    private boolean success;

    public String getRefreshMsg() {
        return refreshMsg;
    }

    public void setRefreshMsg(String refreshMsg) {
        this.refreshMsg = refreshMsg;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
