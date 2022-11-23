package com.yhdemo.demo.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yhdemo.demo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 查找业务的接口
 * @author wyh
 * @date 2022/11/22 14:13
 */

@Mapper
public interface FindMapper {

    /**
     * 查找所有的用户
     * @param page 分页参数
     * @return 查找之后分页对象
     */
    IPage<UserVo> findAll(IPage<UserVo> page);
}
