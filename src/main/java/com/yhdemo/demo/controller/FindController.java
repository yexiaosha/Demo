package com.yhdemo.demo.controller;


import com.yhdemo.demo.service.FindService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import com.yhdemo.demo.vo.Result;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一些查找的请求的接口
 * @author wyh
 * @date 2022/11/22 14:08
 */

@RestController
public class FindController {

    @Resource
    private FindService findService;

    @GetMapping("/listAll")
    @SystemControllerLog("查找所有用户信息")
    public Result findAll() {
        return findService.findAll();
    }
}
