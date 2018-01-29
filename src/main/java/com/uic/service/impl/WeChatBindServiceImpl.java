package com.uic.service.impl;


import com.uic.mapper.WeChatBindMapper;
import com.uic.po.StudentOpenId;
import com.uic.po.User;
import com.uic.service.WeChatBindService;
import org.springframework.beans.factory.annotation.Autowired;

public class WeChatBindServiceImpl implements WeChatBindService{
    @Autowired
    private WeChatBindMapper weChatBindMapper;

    /**
     * 检验是否绑定
     * @param openId
     * @return
     */
    @Override
    public boolean checkBindExist(String openId) {
        try {
            StudentOpenId studentOpenId = weChatBindMapper.checkBindExist(openId);
            if (studentOpenId!=null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取与openId绑定的学号
     * @param openId
     * @return
     */
    @Override
    public StudentOpenId findStudentOpenId(String openId) {
        try {
            StudentOpenId studentOpenId = weChatBindMapper.checkBindExist(openId);
            return studentOpenId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 学生用户信息校验
     * @param studentId
     * @param password
     * @return
     */
    @Override
    public boolean checkUserInfo(String studentId, String password) {
        try {
            User user = weChatBindMapper.findStudentUser(studentId);
            if (password.equals(user.getUserPassword())) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 绑定学号与openId
     * @param studentId
     * @param openId
     */
    @Override
    public void BindOpenId(String studentId, String openId) {
        try {
            weChatBindMapper.BindOpenId(openId, studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检验学号是否存在
     * @param studentId
     * @return
     */
    @Override
    public User checkStudentIdExist(String studentId) {
        try {
            User user = weChatBindMapper.findStudentUser(studentId);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检验学号是否已绑定微信号
     * @param studentId
     * @return
     */
    @Override
    public boolean checkStudentIdBindExist(String studentId) {
        try {
            StudentOpenId studentOpenId = weChatBindMapper.checkStudentIdBindExist(studentId);
            if (studentOpenId == null) {
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
