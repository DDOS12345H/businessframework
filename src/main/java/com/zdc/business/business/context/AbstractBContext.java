package com.zdc.business.business.context;

import com.zdc.business.business.factory.AbstractBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandlesAdapter;
import com.zdc.business.business.util.AssertUtil;

import java.util.*;


public abstract class AbstractBContext<T> {
    Map<String , AbstractBFactory> factoryMap;

    /**
     * 注册
     */
    public abstract void register(T handle);

    /**
     * 执行
     */

    public abstract  <R>R invoke(String handleType, String handleName,  Object context,Class<R> resp);

    /**
     * 获取处理器工厂
     */
    public AbstractBFactory getHandleFactory(String key){
        return factoryMap.get(key);
    }


    public AbstractBContext(Map<String, AbstractBFactory> factoryMap) {
        this.factoryMap = factoryMap;
    }
    public AbstractBContext() {
        this.factoryMap = new HashMap<String, AbstractBFactory>();
    }
}
