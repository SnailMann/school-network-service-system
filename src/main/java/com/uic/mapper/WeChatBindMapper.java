package com.uic.mapper;

import com.uic.po.StudentOpenId;
import com.uic.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * 微信绑定
 */
public interface WeChatBindMapper {

    /**
     * 检验学号是否已绑定微信号
     * @param studentId
     * @return
     * @throws Exception
     */
    public StudentOpenId checkStudentIdBindExist(String studentId) throws Exception;

    /**
     * 检验微信号是否绑定学号
     * @param openId
     * @return
     * @throws Exception
     */
    public StudentOpenId checkBindExist(String openId) throws Exception;

    /**
     * 查找学生用户信息
     * @param studentId
     * @return
     * @throws Exception
     */
    public User findStudentUser(String studentId) throws Exception;

    /**
     * 绑定学生id与openId
     * @param openId
     * @param studentId
     * @throws Exception
     */
    public void BindOpenId(@Param("openId") String openId,@Param("studentId") String studentId) throws Exception;
}
