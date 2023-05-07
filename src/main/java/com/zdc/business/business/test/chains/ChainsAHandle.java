package com.zdc.business.business.test.chains;

import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.handle.chains.AbstractChainHandle;
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.test.domain.BReqContext;
import com.zdc.business.business.test.domain.BRespContext;

@BComponent
public class ChainsAHandle extends AbstractChainHandle<BReqContext, BRespContext> {

    @Override
    public BRespContext execute(BReqContext context) {
        System.out.println("ChainsAHandle");
        return nextNode(context);
    }

    @Override
    public IChainsEnumBFactory getType() {
        return ChainsEnumBFactory.CHAINS_DEFAULT_TYPE_1;
    }

}
