package com.uic.service;


import com.uic.po.Student;
import com.uic.po.StudentBroadband;
import com.uic.po.StudentDTO;

import java.util.List;

/**
 * StudentDTO业务层
 * 实现学生基本信息与宽带信息的操作
 */
public interface StudentDTOService {
    /**
     * 上传Excel文件，读取数据
     * 通过插入Excel录入学生基本信息和宽带信息和密码信息
     * @param filePath
     * @throws Exception
     */
    public void uploadExcel(String filePath)throws Exception;

    /**
     * 导出Excel文件到服务器
     * 从服务器下载文件到本地
     * @param fileName
     * @return
     * @throws Exception
     */
    public void downloadExcel(String fileName,String filePath)throws Exception;

    /**
     * 遍历学生信息与宽带信息（两表）
     * student、studentBroadband
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAndBroadband()throws Exception;

    /**
     * 根据学生学号遍历学生信息与宽带信息（两表）
     * student、studentBroadband
     * @param StudentId
     * @return
     * @throws Exception
     */
    public List<StudentDTO> findStudentAndBroadbandById(String StudentId)throws Exception;

    /**
     * 嵌套查询,只能查询到订单表存在的学号的学生信息和宽带信息（两表）
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAndBroadbandByOrder()throws Exception;

    /**
     * 根据学号嵌套查询,只能查询到订单表存在的学号的学生信息和宽带信息（两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<StudentDTO> findStudentAndBroadbandByInOrder(String studentId)throws Exception;

    /**
     * 嵌套查询,只能查询到订单表不存在的学号的学生信息和宽带信息（两表）
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAndBroadbandByNotInOrder()throws Exception;

    /**
     * 根据学号嵌套查询,只能查询到订单表不存在的学号的学生信息和宽带信息（两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<StudentDTO> findStudentAndBroadbandByNotInOrder(String studentId)throws Exception;

    /**
     * 根据学号判断该生的联合信息是否已经存在(两表)
     * student、studentBroadband
     * @param studentId
     * @return
     * @throws Exception
     */
    public boolean findIsStudentDTOById(String studentId)throws Exception;

    /**
     * 遍历学生、宽带、用户的联合信息
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAllInfo() throws Exception;

    /**
     * 根据学号来遍历学生、宽带、用户的联合信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<StudentDTO> findStudentAllInfoById(String studentId)throws Exception;

    /**
     * 录入学生基本信息和宽带信息
     * @param student
     * @param studentBroadband
     * @throws Exception
     */
    public void insertStudentAllInfo(Student student, StudentBroadband studentBroadband)throws Exception;

    /**
     * 根据Id来更新学生的联合信息
     * @param studentId
     * @param studentDTO
     * @throws Exception
     */
    public void updateStudentAllInfoById(String studentId,StudentDTO studentDTO)throws Exception;

    /**
     * 根据Id来删除学生的联合信息
     * @param studentId
     * @throws Exception
     */
    public void deleteStudentAllInfoById(String studentId)throws Exception;
}
