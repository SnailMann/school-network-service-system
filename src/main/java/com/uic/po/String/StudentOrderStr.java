package com.uic.po.String;

/**
 * 订单类的str格式
 * 全部类型改为String ,方便前后端传值
 * 主要是为了时间在前端的显示格式
 */
public class StudentOrderStr {
    private String studentId;
    private String name;
    private String dorm;
    private String userId;
    private String userPassword;
    private String speed;
    private String lastTime;
    private String money;
    private String printTime;
    private String endTime;
    private String orderId;
    private String workerId;
    private String accessNumber;

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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    @Override
    public String toString() {
        return "StudentOrderStr{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", dorm='" + dorm + '\'' +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", speed='" + speed + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", money='" + money + '\'' +
                ", printTime='" + printTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", workerId='" + workerId + '\'' +
                ", accessNumber='" + accessNumber + '\'' +
                '}';
    }
}
