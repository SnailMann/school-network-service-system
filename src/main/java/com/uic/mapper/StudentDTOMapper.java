package com.uic.mapper;

import com.uic.po.StudentDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生与宽带的联合信息管理
 */
public interface StudentDTOMapper {
    /**
     * 遍历学生信息与宽带信息（两表）
     * student、studentBroadband
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAndBroadband()throws Exception;

    /**
     * 根据学生学号遍历学生信息与宽带信息（两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<StudentDTO> findStudentAndBroadbandById(String studentId)throws Exception;

    /**
     * 嵌套查询,只能查询到订单表存在的学号的学生信息和宽带信息（两表）
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAndBroadbandByInOrder()throws Exception;

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
     * 遍历学生、宽带、用户的联合信息
     * student、studentBroadband、user(三表联合查询)
     * @return
     * @throws Exception
     */
    public List<StudentDTO> queryStudentAllInfo() throws Exception;

    /**
     * 根据学号来遍历学生与宽带的联合信息
     * student、studentBroadband、user(三表联合查询)
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<StudentDTO> findStudentAllInfoById(String studentId)throws Exception;


    /**
     * 根据学生学号来修改学生的基本信息和宽带信息
     * student、studentBroadband（两表）
     * @param studentId 需要修改的学生的学号
     * @param studentDTO 被修改的内容
     * @throws Exception
     */
    public void updateStudentAllInfoById(@Param("studentId")String studentId,@Param("studentDTO") StudentDTO studentDTO)throws Exception;

    /**
     *根据学生学号来删除学生的基本信息和宽带信息
     * student、studentBroadband（两表）
     * @param studentId
     * @throws Exception
     */
    public void deleteStudentAllInfoById(String studentId)throws Exception;
}
