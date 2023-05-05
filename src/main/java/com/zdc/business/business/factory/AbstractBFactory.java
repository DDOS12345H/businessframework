package com.zdc.business.business.factory;

import com.zdc.business.business.util.AssertUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBFactory<T> {
    private Map<Object,T> factory;

    public AbstractBFactory(Map<Object, T> factory) {
        this.factory = factory;
    }

    public AbstractBFactory() {
        this.factory = new HashMap<>();
    }

    /**
     * 获取处理器
     * @param key
     * @return
     */
    public <T>T get(Object key) {
        return (T)factory.get(key);
    }
    /**
     * 获取处理器集
     */
    public Collection<T> get(){
        return factory.values();
    }

    /**
     * 注册处理器
     * @param t
     * @param names
     */
    public void register(T t, List<Object> names){
        for (Object name : names) {
            AssertUtil.isNull(factory.get(name),"当前类型的处理器名称已存在："+name);
            factory.put(name,t);

        }
    }
}
