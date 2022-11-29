package com.yhdemo.demo.utils;

/**
 * 订单唯一流水号生成组件
 * @author wyh
 * @date 2022/11/25 10:00
 */
public class UUIDUtils {
    public static String getUUID(){
        int front = (int) (Math.random()*(10));
        int behind = (int) (Math.random()*(10));
        long now = System.currentTimeMillis();
        String paymentId = String.valueOf(front) + String.valueOf(behind) + String.valueOf(now);
        return paymentId;
    }
}
