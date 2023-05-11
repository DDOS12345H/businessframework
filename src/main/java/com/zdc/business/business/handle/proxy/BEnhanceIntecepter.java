package com.zdc.business.business.handle.proxy;

import com.zdc.business.business.wrapper.IntecepterProceedWrapper;

/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.wrapper
 * @Date：2023/5/7 21:18
 * @Wechat: DDOS12345H
 */
public interface BEnhanceIntecepter {
     Object execute(IntecepterProceedWrapper ipw);
}
