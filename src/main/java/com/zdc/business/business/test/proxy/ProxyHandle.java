package com.zdc.business.business.test.proxy;

import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.proxy.AbstractBEnhanceIntecepter;
import com.zdc.business.business.handle.proxy.BEnhanceIntecepter;
import com.zdc.business.business.wrapper.IntecepterProceedWrapper;

@BComponent
public class ProxyHandle extends AbstractBEnhanceIntecepter {

    @Override
    public Object execute(IntecepterProceedWrapper proceed) {
        System.out.println("增强了");
        return proceed.proceed();
    }
}
