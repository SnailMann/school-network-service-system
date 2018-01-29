package com.uic.service.impl;

import com.uic.mapper.ChargeStrategyMapper;
import com.uic.po.ChargeStrategy;
import com.uic.service.ChargeStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收费策略业务层实现类
 */
public class ChargeStrategyServiceImpl implements ChargeStrategyService{
    @Autowired
    private ChargeStrategyMapper chargeStrategyMapper;

    /**
     * 遍历收费策略信息
     * @return 收费策略的集合
     * @throws Exception
     */
    @Override
    public List<ChargeStrategy> queryChargeStrategy() throws Exception {
        return chargeStrategyMapper.queryChargeStrategy();
    }

    /**
     * 根据网速来查找宽带策略信息
     * @param speed
     * @return
     * @throws Exception
     */
    @Override
    public ChargeStrategy findChargeStrategyBySpeed(int speed) throws Exception {
        return chargeStrategyMapper.findChargeStrategyBySpeed(speed);
    }

    /**
     * 修改收费策略信息
     * @param chargeStrategy 修改的内容及选择条件
     * @throws Exception
     */
    @Override
    public void updateChargeStrategy(ChargeStrategy chargeStrategy) throws Exception {
        chargeStrategyMapper.updateChargeStrategy(chargeStrategy);
    }

    /**
     * 逻辑删除收费策略信息
     * @param id 按id选择
     * @throws Exception
     */
    @Override
    public void deleteChargeStrategyById(String id) throws Exception {
        chargeStrategyMapper.deleteChargeStrategyById(id);
    }

    /**
     * 新增收费策略信息
     * @param chargeStrategy 新增内容，无id，id在数据库自增
     * @throws Exception
     */
    @Override
    public void insertChargeStrategy(ChargeStrategy chargeStrategy) throws Exception {
        chargeStrategyMapper.insertChargeStrategy(chargeStrategy);
    }
}
