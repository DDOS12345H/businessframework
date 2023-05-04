package com.zdc.business.business.test.adapter;

import com.zdc.business.business.handle.adapter.AbstractHandleAdapter;
import com.zdc.business.business.stereotype.AdapterBComponent;

@AdapterBComponent(type = "ZDC",priorityOrder = -10)
public class BHandleAdapter extends AbstractHandleAdapter {
    @Override
    public boolean isSupport(Object context) {
        System.out.println("BHandleAdapter:"+"isSupport");
        return true;
    }

    @Override
    public Object execute(Object context) {
        System.out.println("BHandleAdapter:"+"execute");

        return null;
    }
}
