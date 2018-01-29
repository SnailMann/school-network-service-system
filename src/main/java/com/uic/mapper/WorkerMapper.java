package com.uic.mapper;

import com.uic.po.User;
import com.uic.po.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职工基本信息管理
 */
public interface WorkerMapper {
    /**
     * 遍历
     * @return
     * @throws Exception
     */
    public List<Worker> queryWorker()throws Exception;

    /**
     * 根据职工id查找职工信息
     * @return
     * @throws Exception
     */
    public Worker findWorkerByWorkerId(@Param("workerId") String workerId)throws Exception;

    /**
     * 根据职工id修改职工信息
     * @param worker
     * @throws Exception
     */
    public void updateWorkerByWorkerId(Worker worker) throws Exception;

    /**
     * 插入职工
     * @param worker
     * @throws Exception
     */
    public void insertOrUpdateWorker(Worker worker)throws Exception;

    /**
     * 逻辑删除职工信息
     * @param workerId
     * @throws Exception
     */
    public void deleteWorkerByWorkerId(@Param("workerId") String workerId) throws Exception;
}
