package com.yhdemo.demo;

import com.yhdemo.demo.controller.LoginController;
import com.yhdemo.demo.controller.RegisterController;
import com.yhdemo.demo.dao.FindMapper;
import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.dao.OrderMapper;
import com.yhdemo.demo.dao.RegisterMapper;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.service.FindService;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.service.OrderService;
import com.yhdemo.demo.service.RegisterService;
import com.yhdemo.demo.utils.JwtUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    RegisterService registerService;
    @Autowired
    LoginController loginController;
    @Autowired
    RegisterController registerController;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    LoginService loginService;
    @Autowired
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    FindMapper findMapper;

    @Autowired
    OrderService orderService;
    @Autowired
    FindService findService;

    RegisterParam user = new RegisterParam("Red", "123456", "hansondeg@qq.com", "MALE", null, "2001-5-21");

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzA0ODc1NzQsImlhdCI6MTY2OTU5ODU0MSwidXNlcm5hbWUiOiJKYW1lcyJ9._Ob7R1S48xxSTbRpq3FUBWGPW2JsXhWtk_lxgkvZCAM";
    @Test
    void test() {
        System.out.println(JwtUtils.verifyToken(token));

    }

    @Test
    void Test() throws IOException {
        int sign = 1;
        HttpServletResponse response = null;
        String filePath = "D:\\Temp" + File.separator;
        String zipName = "错误表" + ".zip";
        List<File> files = new LinkedList<>();

        for (int i = 0; i < sign; i++) {
            files.add(new File(filePath + "Err0" + i + ".xlsx"));
        }

        try {
            new ZipFile(filePath + zipName).addFiles(files);
        } catch (ZipException e) {
            e.printStackTrace();
        }

        response.setHeader("content-type", "multipart/form-data");
        response.setContentType("application/force-download");
        response.setContentType("application/binary;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipName, "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        byte[] buff = new byte[2048];
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            filePath = filePath + zipName;
            log.info("压缩文件路径：{}", filePath);
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int read = bis.read(buff);

            while (read != 1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            log.info("{}", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("{}", e);
                } finally {
                    if (bis != null) {
                        bis.close();
                    }
                    outputStream.close();
                }
            }
        }
    }
}
