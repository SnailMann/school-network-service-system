package com.uic.service;

import com.uic.po.StudentBroadband;

/**
 * 学生宽带信息管理业务层
 */
public interface StudentBroadbandService {
    /**
     * 根据学号来查询学生的宽带信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public StudentBroadband findStudentBroadbandById(String studentId)throws Exception;

    /**
     * 插入学生宽带信息
     * @param studentBroadband
     * @throws Exception
     */
    public void insertStudentBroadband(StudentBroadband studentBroadband)throws Exception;

    /**
     * 不存在则插入，存在则更新学生宽带信息
     * @param studentBroadband
     * @throws Exception
     */
    public void insertOrUpdateStudentBroadband(StudentBroadband studentBroadband)throws Exception;

    /**
     * 根据学号来更新学生的宽带信息（除了接入号和账号）
     * @param studentId
     * @param studentBroadband
     * @throws Exception
     */
    public void updateStudentBroadbandSomeById(String studentId, StudentBroadband studentBroadband)throws Exception;

    /**
     * 根据学号前缀真实删除学生的宽带信息
     * @param id
     * @throws Exception
     */
    public void deleteStudentBroadbandTrue(String id)throws Exception;
}
