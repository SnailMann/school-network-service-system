package com.uic.po;

/**
 *  学生基本信息实体类
 *  id:数据库序列号
 *  studentId:学生学号
 *  name:学生姓名
 *  idCard:身份证号
 *  dorm:房号
 *  phone:联系电话
 *  flag:判断是否已逻辑删除
 */
public class Student {
    private int id;
    private String studentId;
    private String name;
    private String idCard;
    private String dorm;
    private String phone;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", dorm='" + dorm + '\'' +
                ", phone='" + phone + '\'' +
                ", flag=" + flag +
                '}';
    }
}
