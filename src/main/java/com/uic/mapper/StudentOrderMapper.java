package com.uic.mapper;

import com.uic.po.StudentOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 订单信息管理
 */
public interface StudentOrderMapper {

    /**
     * 遍历所有学生的订单信息(排除已经作废的订单)
     * （两表，订单表，和订单作废表）
     * @return
     * @throws Exception
     */
    public List<StudentOrder> queryStudentOrder()throws Exception;

    /**
     * 根据学号来查找该学生的所有订单记录(排除已经作废的订单)
     * （两表，订单表，和订单作废表）
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<StudentOrder> findStudentOrderById(String studentId)throws Exception;

    /**
     * 根据学号来查询排序第一的那条订单记录（排除已经作废的订单）
     * @param studentId
     * @return
     * @throws Exception
     */
    public StudentOrder findStudentOrderTopById(String studentId)throws Exception;

    /**
     * 根据订单号来查找订单信息(排除已经作废的订单)
     * (两表，订单表，和订单作废表）
     * @param orderId
     * @return
     * @throws Exception
     */
    public List<StudentOrder> findStudentOrderByOrderId(String orderId)throws Exception;

    /**
     * 根据日期来查询当天的订单记录(排除已经作废的订单)
     * (两表，订单表，和订单作废表）
     * 根据日期从近到远排列
     * @param topDate
     * @param buttomDate
     * @return
     * @throws Exception
     */
    public List<StudentOrder> findStudentOrderByDate(@Param("topDate") Date topDate, @Param("buttomDate") Date buttomDate)throws Exception;

    /**
     * 根据工号、时间段来查询订单记录（排除已经作废的订单）
     * @param workerId
     * @param topDate
     * @param buttomDate
     * @return
     */
    public List<StudentOrder> findStudentOrderByDateAndWorkerId(@Param("workerId")String workerId,@Param("topDate")String topDate,
                                                                @Param("buttomDate") String buttomDate);

    /**
     * 根据日期来查询当天的订单记录的总数
     * @param topDate
     * @param buttomDate
     * @return
     * @throws Exception
     */
    public int findStudentOrderCountByDate(@Param("topDate") Date topDate, @Param("buttomDate") Date buttomDate)throws Exception;

    /**
     *遍历查询被作废的订单的信息
     * （studentOrder、AccountCancel两表）
     * @return
     * @throws Exception
     */
    public List<StudentOrder> queryStudentOrderCancel()throws Exception;

    /**
     * 根据学号查询被作废的订单的信息
     * （studentOrder、AccountCancel两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    public StudentOrder findStudentOrderCancelById(String studentId)throws Exception;

    /**
     * 根据订单号查询被作废的订单的信息
     * （studentOrder、AccountCancel两表）
     * @param orderId
     * @return
     * @throws Exception
     */
    public StudentOrder findStudentOrderCancelByOrderId(String orderId)throws Exception;

    /**
     *
     * 插入订单信息
     * @param studentOrder
     * @throws Exception
     */
    public void insertStudentOrder(StudentOrder studentOrder)throws Exception;

    /**
     * 根据学号来更新订单信息
     * @param orderId
     * @param studentOrder
     * @throws Exception
     */
    public void updateStudentOrderByOrderId(@Param("orderId")String orderId,@Param("studentOrder")StudentOrder studentOrder)throws Exception;

    /**
     * 根据学生学号删除学生相关的订单信息
     * @param studentId
     * @throws Exception
     */
    public void deleteStudentOrderById(String studentId)throws Exception;

    /**
     * 根据订单号删除学生相关的订单信息
     * @param orderId
     * @throws Exception
     */
    public void deleteStudentOrderByOrderId(String orderId)throws Exception;

    /**
     * 根据学号来更新订单的部分信息（姓名和宿舍号）
     * @param studentId
     * @param studentOrder
     * @throws Exception
     */
    public void updateStudentOrderSomeById(@Param("studentId")String studentId,@Param("studentOrder")StudentOrder studentOrder)throws Exception;

    /**
     * 根据学号物理删除该学生的所有订单信息
     * @param id
     * @throws Exception
     */
    public void deleteStudentOrderTrue(String id)throws Exception;

    /**
     * 根据学号查询该学号存在多少有效订单
     * @param id
     * @return
     * @throws Exception
     */
    public Integer countStudentOrderBySid(String id) throws Exception;
}
