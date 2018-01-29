package com.uic.service.impl;

import com.uic.mapper.UserMapper;
import com.uic.mapper.WorkerMapper;
import com.uic.po.Role;
import com.uic.po.User;
import com.uic.po.Worker;
import com.uic.service.UserService;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户认证授权Service实现类
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkerMapper workerMapper;

    /**
     * 遍历所有用户
     * @return
     * @throws Exception
     */
    @Override
    public List<User> queryUser(User user) throws Exception {
        return userMapper.queryUser(user);
    }

    /**
     * 根据登录id查找用户
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public User findUserByUserId(String userId) throws Exception {
        return userMapper.findUserByUserId(userId);
    }

    /**
     * 根据角色id查找角色信息
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public Role findRoleByRoleId(String roleId) throws Exception {
        return userMapper.findRoleByRoleId(roleId);
    }

    /**
     * 新增用户信息
     * @param user
     * @throws Exception
     */
    @Override
    public void insertUser(User user) throws Exception {
        userMapper.insertUser(user);
    }

    /**
     * 存在则插入，不存在则更新用户信息
     * @param user
     * @throws Exception
     */
    @Override
    public void insertOrUpdateUser(User user) throws Exception {
        userMapper.insertOrUpdateUser(user);

    }

    /**
     * 根据用户id修改用户密码
     * @param password
     * @param userId
     * @throws Exception
     */
    @Override
    public void updateUserPasswordByUserId(String password, String userId) throws Exception {
        userMapper.updateUserPasswordByUserId(password, userId);
    }

    /**
     * 根据职工id查询职工信息
     * @param workerId
     * @return
     * @throws Exception
     */
    @Override
    public Worker findWorkerByWorkerId(String workerId) throws Exception {
        return workerMapper.findWorkerByWorkerId(workerId);
    }

    /**
     * 职工修改个人信息
     * @param worker
     * @throws Exception
     */
    @Override
    public void updateWorkerByWorkerId(Worker worker) throws Exception {
        workerMapper.updateWorkerByWorkerId(worker);
    }

    /**
     * 查询所有职工信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Worker> queryWorker() throws Exception {
        return workerMapper.queryWorker();
    }

    /**
     * 插入职工信息
     * @param worker
     * @throws Exception
     */
    @Override
    public void insertWorker(Worker worker) throws Exception {
        workerMapper.insertOrUpdateWorker(worker);
    }

    /**
     * 逻辑删除用户信息
     * @param userId
     * @throws Exception
     */
    @Override
    public void deleteUser(String userId) throws Exception {
        userMapper.deleteUserByUserId(userId);
    }

    /**
     * 逻辑删除职工信息
     * @param workerId
     * @throws Exception
     */
    @Override
    public void deleteWorker(String workerId) throws Exception {
        workerMapper.deleteWorkerByWorkerId(workerId);
    }

    /**
     * 根据用户Id真正的删除用户
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteUserTrue(String id) throws Exception {
        id=id+"%";
        userMapper.deleteUserTrue(id);
    }
}
