package com.yhdemo.demo.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.fastjson.JSON;
import com.yhdemo.demo.handler.validator.ExcelValidatorHelper;
import com.yhdemo.demo.pojo.ExcelCheckErr;
import com.yhdemo.demo.pojo.ExcelCheckResult;
import com.yhdemo.demo.pojo.RegisterUser;
import com.yhdemo.demo.pojo.UserRegisterErr;
import com.yhdemo.demo.service.RegisterService;
import io.netty.util.internal.StringUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 读取excel监听器
 * @author wyh
 * @date 2022/11/29 16:06
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class UploadDataListener<T> extends AnalysisEventListener<T> {


    private static int BATCH_COUNT = 5;
    private static int MAX_ERROR = 100;

    private List<T> successList = new ArrayList<>();
    private List<ExcelCheckErr<T>> errList = new ArrayList<>();

    List<T> list = new ArrayList<>();
    private RegisterService registerService;

    private Class<T> clazz;

    private int sign = 1;

    public UploadDataListener(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(t));
        String errMsg;
        try {
            errMsg = ExcelValidatorHelper.validateEntity(t);
        } catch (NoSuchFieldException e) {
            errMsg = "解析数据出错";
            log.error(errMsg, e);
        }

        if (!StringUtil.isNullOrEmpty(errMsg)) {
            ExcelCheckErr<T> excelCheckErr = new ExcelCheckErr<>(t, errMsg);
            errList.add(excelCheckErr);
        } else {
            list.add(t);
        }

        if (list.size() >= BATCH_COUNT) {
            ExcelCheckResult<T> result = (ExcelCheckResult<T>) registerService.checkImportExcel(
                    (List<RegisterUser>) list);
            successList.addAll(result.getSuccesses());
            errList.addAll(result.getErrs());
            saveData(successList);
            successList.clear();
            list.clear();
        }

        if (errList.size() >= MAX_ERROR) {
            try {
                outPutErrorExcel(errList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            errList.clear();
            sign++;
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ExcelCheckResult<T> result = (ExcelCheckResult<T>) registerService.checkImportExcel((List<RegisterUser>) list);
        successList.addAll(result.getSuccesses());
        errList.addAll(result.getErrs());
        saveData(successList);

        try {
            outPutErrorExcel(errList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.clear();
        errList.clear();
        successList.clear();
        log.info("所有数据解析完毕");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (clazz != null) {
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(clazz);
                Set<Integer> keySet = indexNameMap.keySet();
                for (Integer key : keySet) {
                    if (StringUtil.isNullOrEmpty(headMap.get(key))) {
                        throw new ExcelAnalysisException("解析Excel出错");
                    }
                    if (!headMap.get(key).equals(indexNameMap.get(key))) {
                        throw new ExcelAnalysisException("解析Excel出错");
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<Integer, String> getIndexNameMap(Class<?> clazz) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>(20);
        Field field;
        Field[] fields = clazz.getDeclaredFields();

        for (Field item : fields) {
            field = clazz.getDeclaredField(item.getName());
            field.setAccessible(true);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                int index = excelProperty.index();
                String[] values = excelProperty.value();
                StringBuilder value = new StringBuilder();
                for (String v : values) {
                    value.append(v);
                }
                result.put(index, value.toString());
            }
        }
        return result;
    }

    private void saveData(List<T> list) {
        if (!list.isEmpty()) {
            registerService.updateUsers((List<RegisterUser>) list);
            log.info("操作完成");
        }
    }


    private void outPutErrorExcel(List<ExcelCheckErr<T>> errList) throws IOException {
        OutputStream outputStream = new FileOutputStream("D:/Temp/Err0" + sign + ".xlsx");
        List<UserRegisterErr> excelErrs = errList.stream().map(excelCheckErr -> {
            UserRegisterErr userRegisterErr = JSON.parseObject(JSON.toJSONString(excelCheckErr.getT()),
                    UserRegisterErr.class);
            userRegisterErr.setErrMsg(excelCheckErr.getErrMessage());
            return userRegisterErr;
        }).collect(Collectors.toList());
        EasyExcel.write(outputStream, UserRegisterErr.class).sheet("错误信息表").doWrite(excelErrs);
    }

}