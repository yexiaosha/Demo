package com.yhdemo.demo.service.impl;

import com.yhdemo.demo.dao.DeleteMapper;
import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.pojo.vo.ErrorCode;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.DeleteService;
import com.yhdemo.demo.utils.ResultUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 删除用户实现
 * @author wyh
 * @data 2022/11/18 17:22
 */

@Service
public class DeleteServiceImpl implements DeleteService {

    @Resource
    private DeleteMapper deleteMapper;

    @Resource
    private LoginMapper loginMapper;

    /**
     * 通过用户删除用户
     * @param username 用户名
     * @return 通用返回结果
     */
    @Override
    @SystemServiceLog("根据用户名删除用户")
    public Result<Boolean> deleteByUsername(String username) {

        if (loginMapper.findUserByUsername(username) == null) {
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        if (!deleteMapper.deleteById(username)) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        return ResultUtils.success();
    }
}
