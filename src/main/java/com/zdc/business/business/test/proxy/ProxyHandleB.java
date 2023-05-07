package com.zdc.business.business.test.proxy;

import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.proxy.AbstractBEnhanceIntecepter;
import com.zdc.business.business.wrapper.IntecepterProceedWrapper;

@BComponent
public class ProxyHandleB extends AbstractBEnhanceIntecepter {

    @Override
    public Object execute(IntecepterProceedWrapper proceed) {
        System.out.println("增强了2");
        Object proceed1 = proceed.proceed();
        System.out.println("增强了3");
        return proceed1;
    }
}
