package com.uic.service.impl;

import com.uic.mapper.AccoutCancelMapper;
import com.uic.po.AccoutCancel;
import com.uic.service.AccoutCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单作废管理业务层实现类
 */
public class AccoutCancelServiceImpl implements AccoutCancelService{
    @Autowired
    private AccoutCancelMapper accoutCancelMapper;

    /**
     * 根据订单号来查询订单作废表的信息
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public AccoutCancel findAccoutCancelByOrderId(String orderId) throws Exception {
        return accoutCancelMapper.findAccoutCancelByOrderId(orderId);
    }

    /**
     * 根据订单号来查询作废订单的作废原因
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public String findAccoutCancelReasonByOrderId(String orderId) throws Exception {
        return accoutCancelMapper.findAccoutCancelReasonByOrderId(orderId);
    }

    /**
     * 根据学号来查找对应的没有被作废的订单的数量
     * @param studentId
     * @return
     * @throws Exception
     */
    @Override
    public int findAccoutNotCancelCountById(String studentId) throws Exception {
        return accoutCancelMapper.findAccoutNotCancelCountById(studentId);
    }

    /**
     * 插入订单作废表的信息
     * @param accoutCancel
     * @throws Exception
     */
    @Transactional
    @Override
    public void insertAccoutCancel(AccoutCancel accoutCancel) throws Exception {
        accoutCancelMapper.insertAccoutCancel(accoutCancel);
    }

    /**
     * 根据学号来删除订单作废的信息
     * @param studentId
     * @throws Exception
     */
    @Override
    public void deleteAccoutCancelById(String studentId) throws Exception {
        accoutCancelMapper.deleteAccoutCancelById(studentId);

    }

    /**
     * 根据订单号来删除订单作废的信息
     * @param orderId
     * @throws Exception
     */
    @Override
    public void deleteAccoutCancelByOrderId(String orderId) throws Exception {
        accoutCancelMapper.deleteAccoutCancelByOrderId(orderId);

    }

    /**
     * 根据订单号来更新订单的作废flag为1，就是作废目标订单
     * @param orderId
     * @param cancelReason
     * @throws Exception
     */
    @Override
    public void updateAccoutCancelByOrderId(String orderId, String cancelReason) throws Exception {
        accoutCancelMapper.updateAccoutCancelByOrderId(orderId,cancelReason);

    }

    /**
     * 根据学号前缀真实物理删除订单作废表的信息
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteAccoutCancelTrue(String id) throws Exception {
        id=id+"%";
        accoutCancelMapper.deleteAccoutCancelTrue(id);
    }
}
