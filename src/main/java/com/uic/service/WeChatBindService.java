package com.uic.service;

import com.uic.po.StudentOpenId;
import com.uic.po.User;

/**
 * 微信绑定
 */
public interface WeChatBindService {

    /**
     * 检验学号是否存在
     * @param studentId
     * @return
     */
    public User checkStudentIdExist(String studentId);

    /**
     * 检验是否绑定
     * @param openId
     * @return
     */
    public boolean checkBindExist(String openId);

    /**
     * 获取与openId绑定的学号
     * @param openId
     * @return
     */
    public StudentOpenId findStudentOpenId(String openId);

    /**
     * 学生用户信息校验
     * @param studentId
     * @param password
     * @return
     */
    public boolean checkUserInfo(String studentId, String password);

    /**
     * openId绑定学生id
     * @param studentId
     * @param openId
     */
    public void BindOpenId(String studentId, String openId);

    /**
     * 检验学号是否已绑定微信号
     * @param studentId
     * @return
     */
    public boolean checkStudentIdBindExist(String studentId);
}
