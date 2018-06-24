package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/24.
 */

public class AddOrderContactsBean {
    private int userId;
    private String salary;
    private String overWorkSalary;
    private String allowance;
    private String realName;
    private String profession;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

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
