package com.zdc.business.business.test.adapter;

import com.zdc.business.business.handle.adapter.AbstractHandleAdapter;
import com.zdc.business.business.stereotype.AdapterBComponent;

@AdapterBComponent(type = "ZDC")
public class AHandleAdapter extends AbstractHandleAdapter {
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
}
