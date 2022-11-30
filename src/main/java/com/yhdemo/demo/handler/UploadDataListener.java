package com.yhdemo.demo.handler;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.fastjson.JSON;
import com.yhdemo.demo.handler.validator.ExcelValidatorHelper;
import com.yhdemo.demo.pojo.ExcelCheckErr;
import com.yhdemo.demo.pojo.ExcelCheckResult;
import com.yhdemo.demo.pojo.RegisterUser;
import com.yhdemo.demo.service.RegisterService;
import io.netty.util.internal.StringUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 读取excel监听器
 * @author wyh
 * @date 2022/11/29 16:06
 */
@Slf4j
@Data
public class UploadDataListener<T> extends AnalysisEventListener<RegisterUser> {

    private static final int BATCH_COUNT = 5;

    private List<T> successList = new ArrayList<>();
    private List<ExcelCheckErr<T>> errList = new ArrayList<>();

    List<RegisterUser> list = new ArrayList<>();
    private RegisterService registerService;

    private Class<T> clazz;

    public UploadDataListener(RegisterService registerService) {
        this.registerService = registerService;
    }

    public UploadDataListener(RegisterService registerService, Class<T> clazz) {
        this.registerService = registerService;
        this.clazz = clazz;
    }

    @Override
    public void invoke(RegisterUser registerUser, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(registerUser));
        String errMsg;
        try {
            errMsg = ExcelValidatorHelper.validateEntity(registerUser);
        } catch (NoSuchFieldException e) {
            errMsg = "解析数据出错";
            log.error(errMsg, e);
        }

        if (!StringUtil.isNullOrEmpty(errMsg)) {
            ExcelCheckErr excelCheckErr = new ExcelCheckErr(registerUser, errMsg);
            errList.add(excelCheckErr);
        } else {
            list.add(registerUser);
        }

        if (list.size() >= BATCH_COUNT) {
            ExcelCheckResult result = registerService.checkImportExcel(list);
            successList.addAll(result.getSuccesses());
            errList.addAll(result.getErrs());
            saveData(successList);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ExcelCheckResult result = registerService.checkImportExcel(list);
        successList.addAll(result.getSuccesses());
        errList.addAll(result.getErrs());
        saveData(successList);
        list.clear();
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

    private Map<Integer, String> getIndexNameMap(Class clazz) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>();
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
}
