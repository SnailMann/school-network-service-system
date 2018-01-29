package com.uic.service;

import com.uic.po.ChargeStrategy;

import java.util.List;

/**
 * 收费策略业务层
 */
public interface ChargeStrategyService {
   /**
     * 遍历收费策略信息
     * @return 收费策略的集合
     * @throws Exception
     */
    public List<ChargeStrategy> queryChargeStrategy()throws Exception;

    /**
    * 根据网速来查找宽带策略信息
    * @param speed
    * @return
    * @throws Exception
    */
    public ChargeStrategy findChargeStrategyBySpeed(int speed)throws Exception;

    /**
     * 修改收费策略信息
     * @param chargeStrategy 修改的内容及选择条件
     * @throws Exception
     */
    public void updateChargeStrategy(ChargeStrategy chargeStrategy)throws Exception;

    /**
     * 逻辑删除收费策略信息
     * @param id 按id选择
     * @throws Exception
     */
    public void deleteChargeStrategyById(String id)throws Exception;

    /**
     * 新增收费策略信息
     * @param chargeStrategy 新增内容，无id，id在数据库自增
     * @throws Exception
     */
    public void insertChargeStrategy(ChargeStrategy chargeStrategy)throws Exception;
}
