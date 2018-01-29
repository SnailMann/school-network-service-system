package com.uic.po.String;

/**
 * 报修记录的str格式
 */
public class RepairRecordCustomStr {
    private String studentId;
    private String addTime;
    private String contactTime;
    private String repairContent;
    private String advice;
    private String resultFlag;
    private String name;
    private String phone;
    private String dorm;
    private String toNow;//addTime至今的时间

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
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

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

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

    public String getToNow() {
        return toNow;
    }

    public void setToNow(String toNow) {
        this.toNow = toNow;
    }

    @Override
    public String toString() {
        return "RepairRecordCustomStr{" +
                "studentId='" + studentId + '\'' +
                ", addTime='" + addTime + '\'' +
                ", contactTime='" + contactTime + '\'' +
                ", repairContent='" + repairContent + '\'' +
                ", advice='" + advice + '\'' +
                ", resultFlag='" + resultFlag + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dorm='" + dorm + '\'' +
                ", toNow='" + toNow + '\'' +
                '}';
    }
}
