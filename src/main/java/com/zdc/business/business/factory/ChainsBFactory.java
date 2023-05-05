package com.zdc.business.business.factory;

import java.util.Map;

public class ChainsBFactory<T> extends AbstractBFactory<T> {


    //链表头节点处理器
    private T headHandle;


    public ChainsBFactory(Map factory) {
        super(factory);
    }

    public ChainsBFactory() {
        super();
    }

    public T getHeadHandle() {
        return headHandle;
    }

    public void setHeadHandle(T headHandle) {
        this.headHandle = headHandle;
    }

    @Override
    public T get(Object key) {
        return super.get(key);
    }
}
