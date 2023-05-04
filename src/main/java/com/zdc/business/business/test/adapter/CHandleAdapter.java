package com.zdc.business.business.test.adapter;

import com.zdc.business.business.handle.adapter.AbstractHandleAdapter;
import com.zdc.business.business.stereotype.AdapterBComponent;

@AdapterBComponent(type = "ZDC",priorityOrder = -100)
public class CHandleAdapter extends AbstractHandleAdapter {
    @Override
    public boolean isSupport(Object context) {
        System.out.println("CHandleAdapter:"+"isSupport");
        return true;
    }

    @Override
    public Object execute(Object context) {
        System.out.println("CHandleAdapter:"+"execute");

        return null;
    }
}
