package com.uic.po;

/**
 *  订单作废信息实体类
 *  id:数据库序列号
 *  orderId:订单号
 *  cancelReason:订单作废原因
 *  resultFlag:订单是否作废的逻辑判断（0是不作废，1是作废）
 *  flag:判断是否已逻辑删除
 */
public class AccoutCancel {
    private int id;
    private String studentId;
    private String orderId;
    private String cancelReason;
    private int resultFlag;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
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
        return "AccoutCancel{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", resultFlag=" + resultFlag +
                ", flag=" + flag +
                '}';
    }
}
