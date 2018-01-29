package com.uic.mapper;

/**
 * Created by Administrator on 2017/8/31.
 */
public interface StudentOpenIdMapper {

    /**
     * 根据学生id的前缀真实删除openId
     * @param id
     * @throws Exception
     */
    public void deleteStudentOpenIdTrue(String id)throws Exception;
}
