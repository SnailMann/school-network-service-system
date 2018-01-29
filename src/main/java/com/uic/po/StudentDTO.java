package com.uic.po;

/**
 * 学生基本信息、宽带信息扩展类
 */
public class StudentDTO extends Student{
    private StudentBroadband studentBroadband;
    private User user;

    public StudentBroadband getStudentBroadband() {
        return studentBroadband;
    }

    public void setStudentBroadband(StudentBroadband studentBroadband) {
        this.studentBroadband = studentBroadband;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +"student="+super.toString()+
                "studentBroadband=" + studentBroadband +
                ", user=" + user +
                '}';
    }
}
