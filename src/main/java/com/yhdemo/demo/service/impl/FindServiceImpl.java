package com.yhdemo.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhdemo.demo.dao.FindMapper;
import com.yhdemo.demo.pojo.PageData;
import com.yhdemo.demo.pojo.param.PageParam;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.pojo.vo.UserVo;
import com.yhdemo.demo.service.FindService;
import com.yhdemo.demo.utils.ResultUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
 * 查找的业务实现
 * @author wyh
 * @date 2022/11/22 14:09
 */

@Service
public class FindServiceImpl implements FindService {

    @Resource
    FindMapper findMapper;

    /**
     * 获取所有用户实现
     * @return 通用返回结果
     */
    @Override
    @SystemServiceLog("获取所有用户")
    public Result<PageData<UserVo>> findAll(PageParam pageParam) {
        Page<UserVo> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize(), true);
        IPage<UserVo> result = findMapper.findAll(page);
        return ResultUtils.success(new PageData<>(result.getRecords()));
    }

    @Override
    @SystemServiceLog("获取所有用户输出表格文件")
    public List<UserVo> findAllUserToExcel(HttpServletResponse response) {
        Page<UserVo> page = new Page<>(1, 20, true);
        return findMapper.findAll(page).getRecords();
    }
}
