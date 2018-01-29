package com.uic.po.String;

/**
 * 学生信息表和宽带信息表和用户表的信息
 * 全部类型改为String ,方便前后端传值
 * 主要是为了时间在前端的显示格式
 */
public class StudentDTOStr {
    private String studentId;
    private String name;
    private String idCard;
    private String dorm;
    private String phone;

    private String accessNumber;
    private String accoutNumber;
    private String speed;
    private String money;
    private String startTime;
    private String lastTime;
    private String endTime;

    private String userPassword;
    private String userRoleId;

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

    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public String getAccoutNumber() {
        return accoutNumber;
    }

    public void setAccoutNumber(String accoutNumber) {
        this.accoutNumber = accoutNumber;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public String toString() {
        return "StudentDTOStr{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", dorm='" + dorm + '\'' +
                ", phone='" + phone + '\'' +
                ", accessNumber='" + accessNumber + '\'' +
                ", accoutNumber='" + accoutNumber + '\'' +
                ", speed='" + speed + '\'' +
                ", money='" + money + '\'' +
                ", startTime='" + startTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRoleId='" + userRoleId + '\'' +
                '}';
    }
}
