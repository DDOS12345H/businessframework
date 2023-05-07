package com.zdc.business.business.util;
/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.wrapper
 * @Date：2023/5/7 21:18
 * @Wechat: DDOS12345H
 */
public class ExceptionUtil {
    /**
     * 抛出运行时异常
     * @param tip
     */
    public static void newRuntime(String tip){
        throw new RuntimeException(tip);
    }

    /**
     * 抛出运行时异常
     * @param tip
     * @param placeholder
     * @param text
     */
    public static void newRuntime(String tip,String placeholder,String text){
        throw new RuntimeException(tip.replace(placeholder,text));
    }
}
