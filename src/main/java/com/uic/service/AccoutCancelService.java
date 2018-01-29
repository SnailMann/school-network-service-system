package com.uic.service;

import com.uic.po.AccoutCancel;

/**
 * 订单作废管理业务层
 */
public interface AccoutCancelService {
    /**
     * 根据订单号来查询订单作废表的信息
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public AccoutCancel findAccoutCancelByOrderId(String orderId)throws Exception;

    /**
     * 根据订单号来查询作废订单的作废原因
     * @param orderId
     * @return
     * @throws Exception
     */
    public String findAccoutCancelReasonByOrderId(String orderId)throws Exception;

    /**
     * 根据学号来查找对应的没有被作废的订单的数量
     * @param studentId
     * @return
     * @throws Exception
     */
    public int findAccoutNotCancelCountById(String studentId)throws Exception;

    /**
     * 插入订单作废表的信息
     * @param accoutCancel
     * @throws Exception
     */
    public void insertAccoutCancel(AccoutCancel accoutCancel)throws Exception;

    /**
     * 根据学号来删除订单作废的信息
     * @param studentId
     * @throws Exception
     */
    public void deleteAccoutCancelById(String studentId)throws Exception;

    /**
     * 根据订单号来删除订单作废的信息
     * @param orderId
     * @throws Exception
     */
    public void deleteAccoutCancelByOrderId(String orderId)throws Exception;

    /**
     * 根据订单号来更新订单的作废flag为1，就是作废目标订单
     * @param orderId
     * @param cancelReason
     * @throws Exception
     */
    public void updateAccoutCancelByOrderId(String orderId,String cancelReason)throws Exception;

    /**
     * 根据学号前缀真实物理删除订单作废表的信息
     * @param id
     * @throws Exception
     */
    public void deleteAccoutCancelTrue(String id)throws Exception;

}
