package android.sgz.com.bean;

/**
 * Created by WD on 2018/7/1.
 */

public class SetContactsSalaryBean {
    private int userId;
    private String salary;
    private String overWorkSalary;
    private String allowance;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getOverWorkSalary() {
        return overWorkSalary;
    }

    public void setOverWorkSalary(String overWorkSalary) {
        this.overWorkSalary = overWorkSalary;
    }

    public String getAllowance() {
        return allowance;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }
}
