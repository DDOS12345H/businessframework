package com.zdc.business.business.test;

import com.zdc.business.business.wrapper.CommonBName;
import com.zdc.business.business.test.domain.BReqContext;
import com.zdc.business.business.test.domain.BRespContext;
import com.zdc.business.business.handle.strategy.AbstractBHandle;
import com.zdc.business.business.stereotype.BComponent;

import java.util.Arrays;

@BComponent
public class MyBusinessHandle extends AbstractBHandle<BReqContext, BRespContext> {


    @Override
    public boolean before(BReqContext bReqContext) {
        return true;
    }

    @Override
    public boolean after(BReqContext bReqContext) {
        return true;
    }

    @Override
    public BRespContext doExecute(BReqContext bReqContext) {
        System.out.println(bReqContext.toString());
        int a=1/0;
        return new BRespContext();
    }

    @Override
    public CommonBName getName() {
        return new CommonBName<String>("ZDC", Arrays.asList("T","Z"));
    }

    @Override
    public boolean executeException(Exception e) {
        return false;
    }
}
