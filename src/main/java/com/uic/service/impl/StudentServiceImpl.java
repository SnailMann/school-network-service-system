package com.uic.service.impl;

import com.uic.mapper.StudentMapper;
import com.uic.po.Student;
import com.uic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 学生基本信息管理业务层实现类
 */
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    /**
     * 遍历搜索学生的基本信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Student> queryStudent() throws Exception {
        return studentMapper.queryStudent();
    }

    /**
     * 根据学号来查询学生的信息
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public Student findStudentById(String studentId) throws Exception {
        return studentMapper.findStudentById(studentId);
    }

    /**
     * 根据学号来查询，存在多少条记录
     * @param studentId
     * @throws Exception
     */
    @Override
    public int findStudentCountById(String studentId) throws Exception {
        return studentMapper.findStudentCountById(studentId);
    }

    /**
     * 插入学生基本信息
     * student
     * @param student
     * @throws Exception
     */
    @Override
    public void insertStudent(Student student) throws Exception {
        studentMapper.insertStudent(student);
    }

    /**
     * 不存在则插入，存在则更新学生基本信息
     * @param student
     * @throws Exception
     */
    @Override
    public void insertOrUpdateStudent(Student student) throws Exception {
        studentMapper.insertOrUpdateStudent(student);

    }

    /**
     * 根据学号查询学生基本信息(针对于报修)
     * @param studentId
     * @throws Exception
     */
    @Override
    public Student findrepairStudent(String studentId)throws Exception{
        Student student=studentMapper.findrepairStudent(studentId);
        return student;
    }

    /**
     * 根据学号的前缀彻底删除数据
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteStudentTrue(String id) throws Exception {
        id=id+"%";
        studentMapper.deleteStudentTrue(id);
    }
}
