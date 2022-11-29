package com.yhdemo.demo.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.service.RegisterService;
import com.yhdemo.demo.vo.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 读取excel监听器
 * @author wyh
 * @date 2022/11/29 16:06
 */
@Slf4j
public class UploadDataListener implements ReadListener<RegisterParam> {

    private static final int BATCH_COUNT = 5;

    List<RegisterParam> list = new ArrayList<>();

    private RegisterService registerService;

    public UploadDataListener(RegisterService registerService){
       this.registerService = registerService;
    }

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {

    }

    @Override
    public void invoke(RegisterParam registerParam, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(registerParam));
        list.add(registerParam);
        if (list.size() >= BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完毕");
    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return false;
    }

    private void saveData(){
        log.info("{}条数据，开始存储数据库！", list.size());
        registerService.updateUsers(list);
        log.info("存储数据成功");
    }
}
