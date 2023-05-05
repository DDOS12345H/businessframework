package com.zdc.business.business.test.adapter;

import com.zdc.business.business.factory.constant.AdapterEnumBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandlesAdapter;
import com.zdc.business.business.stereotype.AdapterBComponent;

@AdapterBComponent
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
    public String getType() {
        return AdapterEnumBFactory.ADAPTER_GLOBAL_EXCEPTION_TYPE_NAME.getCode();
    }
}
