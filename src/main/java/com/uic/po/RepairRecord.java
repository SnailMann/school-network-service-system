package com.uic.po;

import java.util.Date;

/**
 *  报修记录实体类
 *  id:数据库序列号
 *  studentId:学生学号
 *  addTime:该条报修记录提交时间
 *  contactTime:联系时间
 *  repairContent:报修内容
 *  advice:已处理的意见
 *  resultFlag:该条记录是否已被处理
 *  flag:判断是否已逻辑删除
 */
public class RepairRecord {
    private int id;
    private String studentId;
    private Date addTime;
    private String contactTime;
    private String repairContent;
    private String advice;
    private int resultFlag;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(int resultFlag) {
        this.resultFlag = resultFlag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "repairRecord{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", addTime=" + addTime +
                ", contactTime='" + contactTime + '\'' +
                ", repairContent='" + repairContent + '\'' +
                ", advice='" + advice + '\'' +
                ", resultFlag=" + resultFlag +
                ", flag=" + flag +
                '}';
    }
}
