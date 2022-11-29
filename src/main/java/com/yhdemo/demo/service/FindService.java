package com.yhdemo.demo.service;

import com.yhdemo.demo.vo.Result;
import com.yhdemo.demo.vo.UserVo;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * 查找的业务接口
 * @author wyh
 * @date 2022/11/22 14:08
 */
public interface FindService {

    /**
     * 获取所有用户接口
     * @return 通用结果
     */
    Result findAll();

    /**
     *
     * @return
     */
    List<UserVo> findAllUserToExcel(HttpServletResponse response);
}
