package com.uic.service;

import com.uic.po.Role;
import com.uic.po.User;
import com.uic.po.Worker;

import java.util.List;

/**
 * 用户管理ervice
 */
public interface UserService {
    /**
     * 遍历所有用户
     * @return
     * @throws Exception
     */
    public List<User> queryUser(User user)throws Exception;

    /**
     * 根据登录id查找用户
     * @param userId
     * @return
     * @throws Exception
     */
    public User findUserByUserId(String userId)throws Exception;

    /**
     * 根据角色id查找角色信息
     * @param roleId
     * @return
     * @throws Exception
     */
    public Role findRoleByRoleId(String roleId)throws Exception;

    /**
     * 新增用户信息
     * @param user
     * @throws Exception
     */
    public void insertUser(User user)throws Exception;

    /**
     * 存在则插入，不存在则更新用户信息
     * @param user
     * @throws Exception
     */
    public void insertOrUpdateUser(User user)throws Exception;

    /**
     * 根据用户id修改用户密码
     * @param password
     * @param userId
     * @throws Exception
     */
    public void updateUserPasswordByUserId(String password,String userId)throws Exception;

    /**
     * 根据职工id查询职工信息
     * @param workerId
     * @return
     * @throws Exception
     */
    public Worker findWorkerByWorkerId(String workerId)throws Exception;

    /**
     * 职工修改个人信息
     * @param worker
     * @throws Exception
     */
    public void updateWorkerByWorkerId(Worker worker)throws Exception;

    /**
     * 查询所有职工信息
     * @return
     * @throws Exception
     */
    public List<Worker> queryWorker()throws Exception;

    /**
     * 插入职工信息
     * @param worker
     * @throws Exception
     */
    public void insertWorker(Worker worker)throws Exception;

    /**
     * 逻辑删除用户信息
     * @param userId
     * @throws Exception
     */
    public void deleteUser(String userId)throws Exception;

    /**
     * 逻辑删除职工信息
     * @param workerId
     * @throws Exception
     */
    public void deleteWorker(String workerId)throws Exception;

    /**
     * 根据用户Id真正的删除用户
     * @param id
     * @throws Exception
     */
    public void deleteUserTrue(String id)throws Exception;
}
