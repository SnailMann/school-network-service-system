package com.uic.service.impl;

import com.uic.mapper.StudentOpenIdMapper;
import com.uic.service.StudentOpenIdService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *openId的业务实现类
 */
public class StudentOpenIdServiceImpl implements StudentOpenIdService
{
    @Autowired
    StudentOpenIdMapper studentOpenIdMapper;

    /**
     * 根据学生id的前缀真实删除openId
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteStudentOpenIdTrue(String id) throws Exception {
        id=id+"%";
        studentOpenIdMapper.deleteStudentOpenIdTrue(id);

    }
}
