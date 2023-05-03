package com.zdc.business.business.context;

import com.zdc.business.business.domain.BName;
import com.zdc.business.business.domain.BReqContext;
import com.zdc.business.business.factory.AbstractBFactory;
import com.zdc.business.business.factory.CommonBFactory;
import com.zdc.business.business.handle.AbstractBHandle;
import com.zdc.business.business.handle.IBHandle;
import com.zdc.business.business.util.AssertUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractBContext {
    Map<String , AbstractBFactory> factoryMap;

    /**
     * 注册
     */
    public void register(AbstractBHandle handle){
        //获取处理器属性名称
        BName bName =  handle.getName();
        AssertUtils.notNull(bName,"处理器名称不能为空！");
        AbstractBFactory abstractBFactory = factoryMap.get(bName.getType());
        if (Objects.isNull(abstractBFactory)){
            abstractBFactory=new CommonBFactory();
            factoryMap.put(bName.getType(),abstractBFactory);
        }
        abstractBFactory.register(handle,bName.getAlias());
    };

    /**
     * 执行
     */

    public <R>R invoke(String handleType, String handleName,  Object context,Class<R> resp){
        AbstractBHandle handle = getHandle(handleType, handleName);
        Object result = handle.invoke(context);
        return (R)result;
    };

    /**
     * 获取处理器工厂
     */
    public AbstractBFactory getHandleFactory(String key){
        return factoryMap.get(key);
    }
    /**
     * 获取处理器
     */
    public AbstractBHandle getHandle(String type,String key){
        AbstractBFactory handleFactory = this.getHandleFactory(type);
        AssertUtils.notNull(handleFactory,"查询不到处理器工厂");
        Object handle = handleFactory.get(key);
        AssertUtils.notNull(handleFactory,"查询不到处理器");

        return (AbstractBHandle)handle;
    }

    public AbstractBContext(Map<String, AbstractBFactory> factoryMap) {
        this.factoryMap = factoryMap;
    }
    public AbstractBContext() {
        this.factoryMap = new HashMap<String, AbstractBFactory>();
    }
}
