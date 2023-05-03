package com.zdc.business.business.factory;

import com.zdc.business.business.util.AssertUtils;

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
    public T get(Object key) {
        return factory.get(key);
    }

    /**
     * 注册处理器
     * @param t
     * @param names
     */
    public void register(T t, List<String> names){
        for (String name : names) {
            AssertUtils.isNull(factory.get(name),"当前类型的处理器名称已存在："+name);
            factory.put(name,t);

        }
    }
}
