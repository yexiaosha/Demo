package com.yhdemo.demo.service;

import com.yhdemo.demo.vo.Result;

/**
 * 删除用户接口
 * @author wyh
 * @data 2022/11/18 17:20
 */
public interface DeleteService {

    /**
     * 通过username删除对象
     * @param username 用户名
     * @return 通用返回
     */
    Result deleteByUsername(String username);
}
