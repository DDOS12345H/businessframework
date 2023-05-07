package com.zdc.business.business.test.proxy;

import com.zdc.business.business.annotation.InterceptorEnhance;
import org.springframework.stereotype.Component;

/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.test.proxy
 * @Date：2023/5/7 21:48
 * @Wechat：DDOS12345H
 */
@Component
public class HttpTo {
    @InterceptorEnhance(intecepter ={ ProxyHandle.class,ProxyHandleB.class})
    public String toGet(String url){
        return "succeed";
    }
}
