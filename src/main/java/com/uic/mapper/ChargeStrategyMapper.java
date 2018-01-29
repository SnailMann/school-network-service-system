package com.uic.mapper;

import com.uic.po.ChargeStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *收费模块DAO
 */
public interface ChargeStrategyMapper {
    /**
     * 查询所有收费策略信息
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
     * 修改策略信息
     * @param chargeStrategy 存放条件及修改内容
     * @throws Exception
     */
    public void updateChargeStrategy(ChargeStrategy chargeStrategy)throws Exception;

    /**
     * 逻辑删除策略信息
     * @param id 根据id选择
     * @throws Exception
     */
    public void deleteChargeStrategyById(@Param("id")String id)throws Exception;

    /**
     * 新增策略信息
     * @param chargeStrategy 新增内容，无id，id在数据库自增
     * @throws Exception
     */
    public void insertChargeStrategy(ChargeStrategy chargeStrategy)throws Exception;
}
