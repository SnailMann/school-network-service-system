package com.uic.po;

import java.util.Date;

/**
 *  学生宽带信息
 *  id:数据库序列号
 *  studentId:学生学号
 *  accessNumber:接入号
 *  accoutNumber:账号
 *  speed:带宽速率
 *  money:金额
 *  startTime:开通时间
 *  lastTime:开通月份（包了几个月）
 *  endTime:结束时间
 *  flag:判断是否已逻辑删除
 */

public class StudentBroadband {
    private int id;
    private String studentId;
    private String accessNumber;
    private String accoutNumber;
    private int speed;
    private double money;
    private Date startTime;
    private int lastTime;
    private Date endTime;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "StudentBroadband{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", accessNumber='" + accessNumber + '\'' +
                ", accoutNumber='" + accoutNumber + '\'' +
                ", speed=" + speed +
                ", money=" + money +
                ", startTime=" + startTime +
                ", lastTime=" + lastTime +
                ", endTime=" + endTime +
                ", flag=" + flag +
                '}';
    }
}
