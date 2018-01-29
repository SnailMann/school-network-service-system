package com.uic.service.impl;

import com.uic.mapper.StudentOrderMapper;
import com.uic.po.StudentOrder;
import com.uic.service.StudentOrderService;
import com.uic.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 学生订单管理业务层实现类
 */
public class StudentOrderServiceImpl implements StudentOrderService {

    @Autowired
    private StudentOrderMapper studentOrderMapper;


    /**
     * 遍历所有学生的订单信息(排除已经作废的订单)
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentOrder> queryStudentOrder() throws Exception {
        return studentOrderMapper.queryStudentOrder();
    }

    /**
     * 根据学号来查找该学生的所有订单记录(排除已经作废的订单)
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentOrder> findStudentOrderById(String studentId) throws Exception {
        return studentOrderMapper.findStudentOrderById(studentId);
    }

    /**
     * 根据学号来查询排序第一的那条订单记录（排除已经作废的订单）
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public StudentOrder findStudentOrderTopById(String studentId) throws Exception {
        return studentOrderMapper.findStudentOrderTopById(studentId);
    }

    /**
     * 根据订单号来查找订单信息(排除已经作废的订单)
     * (两表，订单表，和订单作废表）
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentOrder> findStudentOrderByOrderId(String orderId) throws Exception {
        return studentOrderMapper.findStudentOrderByOrderId(orderId);
    }

    /**
     * 根据日期来查询当天的订单记录(排除已经作废的订单)
     * 根据日期升序列排列
     * @param topDate
     * @param buttomDate
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentOrder> findStudentOrderByDate(Date topDate,Date buttomDate)throws Exception {
        return studentOrderMapper.findStudentOrderByDate(topDate,buttomDate);
    }

    /**
     * 根据日期来查询当天的订单记录的总数
     * @param topDate
     * @param buttomDate
     * @return
     * @throws Exception
     */
    @Override
    public int findStudentOrderCountByDate(Date topDate,Date buttomDate) throws Exception {
        return studentOrderMapper.findStudentOrderCountByDate(topDate,buttomDate);
    }

    /**
     * 根据工号、时间段来查询订单记录（排除已经作废的订单）
     * @param workerId
     * @param topDate
     * @param buttomDate
     * @return
     */
    @Override
    public List<StudentOrder> findStudentOrderByDateAndWorkerId(String workerId, String topDate, String buttomDate) {
        return studentOrderMapper.findStudentOrderByDateAndWorkerId(workerId,topDate,buttomDate);
    }

    /**
     *遍历查询被作废的订单的信息
     * （studentOrder、AccountCancel两表）
     * @return
     * @throws Exception
     */
    @Override
    public List<StudentOrder> queryStudentOrderCancel() throws Exception {
        return studentOrderMapper.queryStudentOrderCancel();
    }

    /**
     * 根据学号查询被作废的订单的信息
     * （studentOrder、AccountCancel两表）
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public StudentOrder findStudentOrderCancelById(String studentId) throws Exception {
        return studentOrderMapper.findStudentOrderCancelById(studentId);
    }

    /**
     * 根据订单号查询被作废的订单的信息
     * （studentOrder、AccountCancel两表）
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public StudentOrder findStudentOrderCancelByOrderId(String orderId) throws Exception {
        return studentOrderMapper.findStudentOrderCancelByOrderId(orderId);
    }

    /**
     * 插入订单信息
     * @param studentOrder
     * @throws Exception
     */
    @Transactional
    @Override
    public void insertStudentOrder(StudentOrder studentOrder) throws Exception {
        studentOrderMapper.insertStudentOrder(studentOrder);

    }

    /**
     * 根据学生学号删除学生相关的订单信息
     * @param studentId
     * @throws Exception
     */
    @Override
    public void deleteStudentOrderById(String studentId) throws Exception {
        studentOrderMapper.deleteStudentOrderById(studentId);
    }

    /**
     * 根据订单号删除学生相关的订单信息
     * @param orderId
     * @throws Exception
     */
    @Override
    public void deleteStudentOrderByOrderId(String orderId) throws Exception {
        studentOrderMapper.deleteStudentOrderById(orderId);

    }

    /**
     * /**
     * 生成订单号
     * 单据编号说明：如：16  1011  06  0001（161011060001）
     * 16为年份
     * 1011为当天日期
     * 06为工号
     * 0001当天生成打印号码
     * @param nowDate
     * @param year
     * @param month
     * @param day
     * @param workerId
     * @return
     * @throws Exception
     */
    @Override
    public String createOrderId(String nowDate,String year,String month,String day,String workerId) throws Exception {
        Date topDate=DateUtil.addStartTime(DateUtil.convertStringToDate(nowDate));
        Date buttomDate=DateUtil.addEndTime(DateUtil.convertStringToDate(nowDate));
        //查找当天存在多少天订单数据，订单号尾号则在这条数据上加1
        int numToDay=studentOrderMapper.findStudentOrderCountByDate(topDate,buttomDate);
        numToDay++;
        String numToDayStr=String.format("%04d",numToDay);
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String dayStr=String.format("%02d",Integer.valueOf(day));
        String orderId=year.substring(2,4)+month+dayStr+workerId+numToDayStr;

        return orderId;

    }

    /**
     * 根据学号来更新订单的部分信息（姓名和宿舍号）
     * @param studentId
     * @param studentOrder
     * @throws Exception
     */
    @Override
    public void updateStudentOrderSomeById(String studentId, StudentOrder studentOrder) throws Exception {
        studentOrderMapper.updateStudentOrderSomeById(studentId,studentOrder);
    }

    /**
     * 根据学号物理删除该学生的所有订单信息
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteStudentOrderTrue(String id) throws Exception {
        id=id+"%";
        studentOrderMapper.deleteStudentOrderTrue(id);

    }

    /**
     * 根据订单判断用户是否已开通宽带
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean hasBeenOpen(String id){
        Integer count = null;
        try {
            count = studentOrderMapper.countStudentOrderBySid(id);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
