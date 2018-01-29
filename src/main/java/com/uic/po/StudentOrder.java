package com.uic.po;

import java.util.Date;

/**
 *  学生订单信息
 *  id:数据库序列号
 *  studentId:学生学号
 *  name:学生姓名
 *  dorm:房号
 *  userId:学生账户（其实就是学号）
 *  userPassword:用户密码
 *  lastTime:开通月份（包了几个月）
 *  money:金额
 *  printTime:打印时间（开通时间）
 *  endTime:到期时间
 *  orderId:订单号
 *  workerId:工人工号
 *  flag:判断是否已逻辑删除
 */
public class StudentOrder {
    private int id;
    private String studentId;
    private String name;
    private String dorm;
    private String userId;
    private String userPassword;
    private int speed;
    private int lastTime;
    private Double money;
    private Date printTime;
    private Date endTime;
    private String orderId;
    private String workerId;

    private String accessNumber;
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

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "StudentOrder{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", dorm='" + dorm + '\'' +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", speed=" + speed +
                ", lastTime=" + lastTime +
                ", money=" + money +
                ", printTime=" + printTime +
                ", endTime=" + endTime +
                ", orderId='" + orderId + '\'' +
                ", workerId='" + workerId + '\'' +
                ", accessNumber='" + accessNumber + '\'' +
                ", flag=" + flag +
                '}';
    }
}
