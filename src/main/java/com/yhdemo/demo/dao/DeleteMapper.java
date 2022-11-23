package com.yhdemo.demo.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 删除用户
 * @author wyh
 * @data 2022/11/18 17:22
 */

@Mapper
public interface DeleteMapper {

    /**
     * 通过username删除对象
     * @param username 用户名
     * @return 是否正确的删除
     */
    boolean deleteById(String username);
}
