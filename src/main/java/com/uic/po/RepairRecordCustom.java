package com.uic.po;

/**
 * 学生维修信息扩展类
 * Created by JKtim on 2017/7/15.
 */
public class RepairRecordCustom extends RepairRecord {
    private String name;
    private String phone;
    private String dorm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    @Override

    public String toString(){
        return "RepairRecordCustom{" +
                "studentName='" + name + '\'' +
                ", studentPhone='" + phone + '\'' +
                ", studentDorm='" + dorm + '\'' +
                super.toString()+
                '}';
    }
}
