package com.uic.service.impl;

import com.uic.mapper.RepairRecordMapper;
import com.uic.po.RepairRecord;
import com.uic.po.RepairRecordCustom;
import com.uic.service.StudentRepairService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 学生报修管理业务层实现类
 * Created by JKtim on 2017/7/15.
 */
public class StudentRepairServiceImpl implements StudentRepairService {

    @Autowired
    private RepairRecordMapper repairRecordMapper;

    /**
     * 遍历所有学生维修信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<RepairRecordCustom> queryRepairRecord() throws Exception {
        return repairRecordMapper.queryRepairRecord();
    }

    /**
     * 遍历所有未处理的学生维修信息
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> queryNoDealRepairRecord() throws Exception{
        return repairRecordMapper.queryNoDealRepairRecord();
    }

    /**
     * 遍历所有处理的学生维修信息
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> queryDealRepairRecord() throws Exception{
        return repairRecordMapper.queryDealRepairRecord();
    }

    /**
     * 学生报修信息录入
     *
     * @param
     * @return List<RepairRecord>
     * @throws Exception
     */
    @Override
    public void insertRepairRecord(RepairRecord repairRecord) throws Exception {
      repairRecordMapper.insertRepairRecord(repairRecord);
    }

    /**
     * 根据学生Id查询报修信息
     * 根据日期进行排列
     *
     * @param studentId
     * @return List<RepairRecordCustom>
     * @throws Exception
     */
    @Override
    public List<RepairRecordCustom> findRepairRecordByStudentId(String studentId) throws Exception{
        return repairRecordMapper.findRepairRecordByStudentId(studentId);
    }

    /**
     * 根据studentId查询未处理的报修信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findNoDealRepairRecoreByStudentId(String studentId) throws Exception{
        return repairRecordMapper.findNoDealRepairRecoreByStudentId(studentId);
    }

    /**
     * 根据studentId查询处理的报修信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findDealRepairRecoreByStudentId(String studentId) throws Exception{
        return repairRecordMapper.findDealRepairRecoreByStudentId(studentId);
    }


    /**
     * 根据学生Id删除学生报修信息
     * @throws Exception
     */
    @Override
    public void deleteRepairRecordByStudentId(String studentId)throws Exception{
        repairRecordMapper.deleteRepairRecordByStudentId(studentId);
    }

    /**
     * 根据学生维修内容修改学生报修信息及结果录入
     * @param repairContent
     * @param advice
     * @throws Exception
     */
    public void updateRepairRecordByStudentRepairContent(String repairContent,String advice)throws  Exception{
        repairRecordMapper.updateRepairRecordByStudentRepairContent(repairContent,advice);
    }

    /**
     * 根据sudentId与addTime来查找唯一报修记录
     * @param studentId
     * @param repairContent
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findRepairRecordByStudentIdAndrepairContent(String studentId,String repairContent)throws Exception{
        return repairRecordMapper.findRepairRecordByStudentIdAndrepairContent(studentId,repairContent);
    }

    /**
     * 根据sudentId与resultFlag来查找对应的报修记录
     * @param studentId
     * @param resultFlag
     * @return
     * @throws Exception
     */
    public List<RepairRecordCustom> findRepairRecordByStudentIdAndresultFlag(String studentId,int resultFlag)throws Exception{
        return repairRecordMapper.findRepairRecordByStudentIdAndresultFlag(studentId,resultFlag);
    }

    /**
     * 根据学号前缀删除所有的用户报修信息
     * @param id
     * @throws Exception
     */

    @Override
    public void deleteRepairRecordTrue(String id) throws Exception {
        id=id+"%";
        repairRecordMapper.deleteRepairRecordTrue(id);
    }

    /**
     * 根据studentID 与报修内容删除 对应报修信息
     * @param studentId
     * @param repairContent
     * @throws Exception
     */
    public void deleteRepairRecordByStudentIdAndrepairContent(String studentId,String repairContent)throws Exception{
        repairRecordMapper.deleteRepairRecordByStudentIdAndrepairContent(studentId, repairContent);
    }
}