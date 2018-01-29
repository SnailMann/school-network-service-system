package com.uic.service;

/**
 *  openId的业务层
 */
public interface StudentOpenIdService {
    /**
     * 根据学生id的前缀真实删除openId
     * @param id
     * @throws Exception
     */
    public void deleteStudentOpenIdTrue(String id)throws Exception;
}
