package com.uic.mapper;


import com.uic.po.Student;

import java.util.List;

/**
 * 学生基本信息管理
 */


public interface StudentMapper {

    /**
     * 遍历搜索学生的基本信息
     * @return
     * @throws Exception
     */
    public List<Student> queryStudent()throws Exception;

    /**
     * 根据学号来查询学生的信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public Student findStudentById(String studentId)throws Exception;

    /**
     * 根据学号来查询，存在多少条记录
     * @param studentId
     * @throws Exception
     */
    public int findStudentCountById(String studentId)throws Exception;

    /**
     * 插入学生基本信息
     * student
     * @param student
     * @throws Exception
     */
    public void insertStudent(Student student)throws Exception;

    /**
     * 不存在则插入，存在则更新学生基本信息
     * @param student
     * @throws Exception
     */
    public void insertOrUpdateStudent(Student student)throws Exception;

    /**
     * 根据学号查询学生基本信息(针对于报修)
     * @param studentId
     * @throws Exception
     */
    public Student findrepairStudent(String studentId)throws Exception;

    /**
     * 根据学号的前缀彻底删除数据
     * @param id
     * @throws Exception
     */
    public void deleteStudentTrue(String id)throws Exception;


}
