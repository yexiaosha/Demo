package com.yhdemo.demo.utils;

import java.io.File;
import java.io.InputStream;
import java.sql.Statement;

/**
 * 文件io操作组件
 * @author wyh
 * @date 2022/11/29 11:24
 */
public class FileUtils {
    public static InputStream getResourcesFileInputStream(String fileName){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath(){
        return FileUtils.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName){
        File file = new File(getPath() + pathName);
        if (file.exists()){
            file.delete();
        }else {
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName){
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName){
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }
}
