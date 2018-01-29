package com.uic.po;

/**
 *  工人基本信息实体类
 *  id:数据库序列号
 *  workerId:工人工号
 *  name:工人姓名
 *  phone:联系电话
 *  flag:判断是否已逻辑删除
 */
public class Worker {
    private int id;
    private String workerId;
    private String name;
    private String phone;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "worker{" +
                "id=" + id +
                ", workerId='" + workerId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", flag=" + flag +
                '}';
    }
}
