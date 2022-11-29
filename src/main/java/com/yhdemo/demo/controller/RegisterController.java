package com.yhdemo.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.yhdemo.demo.handler.UploadDataListener;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.service.RegisterService;
import com.yhdemo.demo.utils.DateUtils;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import com.yhdemo.demo.vo.Result;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 注册接口
 * @author wyh
 * @data 2022/11/18 14:10
 */

@CrossOrigin
@RestController
@Validated
public class RegisterController {

    @Resource
    private RegisterService registerService;

    /**
     * 注册接口
     * @param registerParam 用户注册时信息的字段
     * @return 通用返回结果
     */
    @PutMapping("/register")
    @SystemControllerLog("用户注册")
    public Result register(@RequestBody RegisterParam registerParam) {
        registerParam.setRegisterTime(DateUtils.getPresentTime());
        return registerService.register(registerParam);
    }

    /**
     * 获取某个用户注册信息
     * @param username 用户名
     * @return 通用返回类型
     */
    @GetMapping("/regInfo")
    @SystemControllerLog("查看用户注册信息")
    public Result getRegisterName(@NotBlank @Size(max = 20, min = 3) @RequestParam String username) {
        System.out.println(username);
        return registerService.getRegisterInfo(username);
    }

    @PostMapping("/upload")
    @SystemControllerLog("通过表格上传批量用户信息")
    public Result uploadUserInfoByExcel(@RequestPart("file") MultipartFile[] files) throws IOException {
        for (MultipartFile file:files){
            System.out.println(file.getSize());
            InputStream inputStream = file.getInputStream();
            System.out.println(inputStream.getClass());
            EasyExcel.read(inputStream, RegisterParam.class, new UploadDataListener(registerService)).sheet().headRowNumber(1).doRead();
        }
        return Result.success();
    }
}