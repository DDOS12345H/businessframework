package com.zdc.business.business.test;

import com.zdc.business.business.domain.BName;
import com.zdc.business.business.domain.BReqContext;
import com.zdc.business.business.domain.BRespContext;
import com.zdc.business.business.handle.AbstractBHandle;
import com.zdc.business.business.stereotype.BComponent;
import org.springframework.stereotype.Component;

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
        return new BRespContext();
    }

    @Override
    public BName getName() {
        return new BName("ZDC", Arrays.asList("T","Z"));
    }

    @Override
    public boolean executeException(Exception e) {
        return false;
    }
}
