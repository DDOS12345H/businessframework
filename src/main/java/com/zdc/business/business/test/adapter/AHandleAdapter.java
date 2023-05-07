package com.zdc.business.business.test.adapter;

import com.zdc.business.business.factory.IAdapterEnumBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandlesAdapter;
import com.zdc.business.business.annotation.BComponent;

@BComponent
public class AHandleAdapter extends AbstractHandlesAdapter {
    @Override
    public boolean isSupport(Object context) {
        System.out.println("AHandleAdapter:"+"isSupport");
        return true;
    }

    @Override
    public Object execute(Object context) {
        System.out.println("AHandleAdapter:"+"execute");

        return null;
    }

    @Override
    public IAdapterEnumBFactory getType() {
        return AdapterEnumBFactory.ADAPTER_GLOBAL_EXCEPTION_TYPE_NAME;
    }
}
