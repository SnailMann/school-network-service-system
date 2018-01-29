package com.uic.service;

import com.uic.po.RepairRecord;
import com.uic.po.RepairRecordCustom;

import java.util.List;

/**
 * 学生报修信息管理业务层
 * Created by JKtim on 2017/7/15.
 */
public interface StudentRepairService {

    /**
     * 遍历所有学生维修信息
     *
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> queryRepairRecord() throws Exception;

    /**
     * 遍历所有未处理的学生维修信息
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> queryNoDealRepairRecord() throws Exception;

    /**
     * 遍历所有未处理的学生维修信息
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> queryDealRepairRecord() throws Exception;

    /**
     * 学生报修信息录入
     *
     * @param
     * @return List<RepairRecord>
     * @throws Exception
     */
    public void insertRepairRecord(RepairRecord repairRecord) throws Exception;

    /**
     * 根据学生Id查询报修信息
     * @param studentId
     * @return List<RepairRecordCustom>
     * @throws Exception
     */
    public List<RepairRecordCustom> findRepairRecordByStudentId(String studentId) throws Exception;

    /**
     * 根据studentId查询未处理的报修信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findNoDealRepairRecoreByStudentId(String studentId) throws Exception;

    /**
     * 根据studentId查询处理的报修信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findDealRepairRecoreByStudentId(String studentId) throws Exception;

    /**
     * 根据学生Id删除学生报修信息
     * @throws Exception
     */
    public void deleteRepairRecordByStudentId(String studentId)throws Exception;

    /**
     * 根据学生维修内容修改学生报修信息及结果录入
     * @param repairContent
     * @param advice
     * @throws Exception
     */
    public void updateRepairRecordByStudentRepairContent(String repairContent,String advice)throws Exception;

    /**
     * 根据sudentId与addTime来查找唯一报修记录
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findRepairRecordByStudentIdAndrepairContent(String studentId,String repairContent)throws Exception;

    /**
     * 根据sudentId与resultFlag来查找对应的报修记录
     * @param studentId
     * @param resultFlag
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findRepairRecordByStudentIdAndresultFlag(String studentId,int resultFlag)throws Exception;

    /**
     * 根据学号前缀删除所有的用户报修信息
     * @param id
     * @throws Exception
     */
    public void deleteRepairRecordTrue(String id)throws Exception;

    /**
     * 根据studentID 与报修内容删除 对应报修信息
     * @param studentId
     * @param repairContent
     * @throws Exception
     */
    public void deleteRepairRecordByStudentIdAndrepairContent(String studentId,String repairContent)throws Exception;
}
