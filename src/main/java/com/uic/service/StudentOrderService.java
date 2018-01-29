package com.uic.service;

import com.uic.po.StudentOrder;

import java.util.Date;
import java.util.List;

/**
 * 学生订单管理业务层
 */
public interface StudentOrderService {

    /**
     * 遍历所有学生的订单信息(排除已经作废的订单)
     * @return
     * @throws Exception
     */
    public List<StudentOrder> queryStudentOrder()throws Exception;

    /**
     * 根据学号来查找该学生的所有订单记录(排除已经作废的订单)
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
     * 根据日期来查询当天的订单记录(排除已经作废的订单)
     * 根据日期升序列排列
     * @param topDate
     * @param buttomDate
     * @return
     * @throws Exception
     */
    public List<StudentOrder> findStudentOrderByDate(Date topDate,Date buttomDate)throws Exception;

    /**
     * 根据订单号来查找订单信息(排除已经作废的订单)
     * (两表，订单表，和订单作废表）
     * @param orderId
     * @return
     * @throws Exception
     */
    public List<StudentOrder> findStudentOrderByOrderId(String orderId)throws Exception;

    /**
     * 根据工号、时间段来查询订单记录（排除已经作废的订单）
     * @param workerId
     * @param topDate
     * @param buttomDate
     * @return
     */
    public List<StudentOrder> findStudentOrderByDateAndWorkerId(String workerId,String topDate, String buttomDate);

    /**
     * 根据日期来查询当天的订单记录的总数
     * @param topDate
     * @param buttomDate
     * @return
     * @throws Exception
     */
    public int findStudentOrderCountByDate(Date topDate,Date buttomDate)throws Exception;

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
     * 插入订单信息
     * @param studentOrder
     * @throws Exception
     */
    public void insertStudentOrder(StudentOrder studentOrder)throws Exception;

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
     * 生成订单号
     * @throws Exception
     */
    public String createOrderId(String nowDate,String year,String month,String day,String workerId) throws Exception;

    /**
     * 根据学号来更新订单的部分信息（姓名和宿舍号）
     * @param studentId
     * @param studentOrder
     * @throws Exception
     */
    public void updateStudentOrderSomeById(String studentId,StudentOrder studentOrder)throws Exception;

    /**
     * 根据学号物理删除该学生的所有订单信息
     * @param id
     * @throws Exception
     */
    public void deleteStudentOrderTrue(String id)throws Exception;


    /**
     * 根据订单判断用户是否已开通宽带
     * @param id
     * @return
     * @throws Exception
     */
    public boolean hasBeenOpen(String id) throws Exception;
}
