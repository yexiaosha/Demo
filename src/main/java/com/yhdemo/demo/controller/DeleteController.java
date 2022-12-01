package com.yhdemo.demo.controller;

import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.DeleteService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 删除账户
 * @author wyh
 * @data 2022/11/18 17:18
 */

@RestController
@Validated
public class DeleteController {

    @Resource
    private DeleteService deleteService;

    /**
     * 通过用户名删除用户
     * @param username 用户名
     * @return 通用返回类型
     */
    @DeleteMapping("/delete")
    @SystemControllerLog("根据用户名删除用户")
    public Result<Boolean> deleteUserByUsername(
            @Valid @NotBlank @Size(max = 20, min = 3) @RequestParam String username) {
        return deleteService.deleteByUsername(username);
    }
}
