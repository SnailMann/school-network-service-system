package com.uic.service.impl;

import com.uic.mapper.StudentBroadbandMapper;
import com.uic.po.StudentBroadband;
import com.uic.service.StudentBroadbandService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生宽带信息管理业务层实现类
 */
public class StudentBroadbandServiceImpl implements StudentBroadbandService {

    @Autowired
    StudentBroadbandMapper studentBroadbandMapper;

    /**
     * 根据学号来查询学生的宽带信息
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public StudentBroadband findStudentBroadbandById(String studentId) throws Exception {
        return studentBroadbandMapper.findStudentBroadbandById(studentId);
    }

    /**
     * 插入学生宽带信息
     * @param studentBroadband
     * @throws Exception
     */
    @Override
    public void insertStudentBroadband(StudentBroadband studentBroadband) throws Exception {
        studentBroadbandMapper.insertStudentBroadband(studentBroadband);

    }

    /**
     * 不存在则插入，存在则更新学生宽带信息
     * @param studentBroadband
     * @throws Exception
     */
    @Override
    public void insertOrUpdateStudentBroadband(StudentBroadband studentBroadband) throws Exception {
        studentBroadbandMapper.insertOrUpdateStudentBroadband(studentBroadband);
    }

    /**
     * 根据学号来更新学生的宽带信息（除了接入号和账号）
     * @param studentId
     * @param studentBroadband
     * @throws Exception
     */
    @Override
    public void updateStudentBroadbandSomeById(String studentId, StudentBroadband studentBroadband) throws Exception {
        studentBroadbandMapper.updateStudentBroadbandSomeById(studentId,studentBroadband);
    }

    /**
     * 根据学号前缀真实删除学生的宽带信息
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteStudentBroadbandTrue(String id) throws Exception {
        id=id+"%";
        studentBroadbandMapper.deleteStudentBroadbandTrue(id);
    }
}
