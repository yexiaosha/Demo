package com.yhdemo.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.yhdemo.demo.service.FindService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import com.yhdemo.demo.vo.Result;
import com.yhdemo.demo.vo.UserVo;
import java.io.IOException;
import java.net.URLEncoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/download")
    @SystemControllerLog("下载所有用户信息")
    public void findAllUserToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserVo.class).sheet("Demo")
                .doWrite(findService.findAllUserToExcel(response));
    }
}
