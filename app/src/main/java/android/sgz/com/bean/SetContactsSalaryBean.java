package android.sgz.com.bean;

/**
 * Created by WD on 2018/7/1.
 */

public class SetContactsSalaryBean {
    private int userid;
    private String salary;
    private String overworksalary;
    private String allowance;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getOverworksalary() {
        return overworksalary;
    }

    public void setOverworksalary(String overworksalary) {
        this.overworksalary = overworksalary;
    }

    public String getAllowance() {
        return allowance;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }
}
