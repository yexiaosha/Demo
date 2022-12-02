package com.yhdemo.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.yhdemo.demo.handler.UploadDataListener;
import com.yhdemo.demo.pojo.RegisterUser;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.pojo.vo.RegisterUserVo;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.RegisterService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
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
@Slf4j
@Api(tags = "注册操作")
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
    @ApiOperation(value = "用户注册", notes = "用户注册返回成功信息JSON")
    public Result<Boolean> register(@RequestBody RegisterParam registerParam) {
        registerParam.setRegisterTime(new Date());
        return registerService.register(registerParam);
    }

    /**
     * 获取某个用户注册信息
     * @param username 用户名
     * @return 通用返回类型
     */
    @GetMapping("/regInfo")
    @SystemControllerLog("获取用户注册信息")
    @ApiOperation(value = "获取用户注册信息", notes = "返回想要的用户信息表JSON")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", name = "username")
    })
    public Result<RegisterUserVo> getRegisterInfo(@NotBlank @Size(max = 20, min = 3) @RequestParam String username) {
        System.out.println(username);
        return registerService.getRegisterInfo(username);
    }

    @PostMapping("/upload")
    @SystemControllerLog("通过表格上传批量用户信息")
    @ApiOperation(value = "通过表格上传批量用户信息", notes = "向用户传输文件，错误时返回JSON")
    public void uploadUserInfoByExcel(HttpServletResponse response, @RequestPart("file") MultipartFile file)
            throws IOException {
        InputStream inputStream = file.getInputStream();
        UploadDataListener<RegisterUser> listener = new UploadDataListener<>(registerService);
        EasyExcel.read(inputStream, RegisterUser.class, listener).sheet().doRead();

        String filePath = "D:\\Temp" + File.separator;
        String zipName = "ErrorTable" + ".zip";

        new ZipFile(filePath + zipName).addFiles(registerService.getErrFiles(filePath, listener.getSign()));

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipName, "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        OutputStream osOut = response.getOutputStream();
        File zipFile = new File(filePath + zipName);
        InputStream input = null;
        try {
            input = new FileInputStream(zipFile);
            byte[] buf = new byte[1024];
            int read;
            while ((read = input.read(buf)) > 0) {
                osOut.write(buf, 0, read);
            }
        } finally {
            input.close();
            osOut.close();
        }
    }
}