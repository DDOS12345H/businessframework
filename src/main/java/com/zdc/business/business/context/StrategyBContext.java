package com.zdc.business.business.context;

import com.zdc.business.business.factory.AbstractBFactory;
import com.zdc.business.business.factory.CommonBFactory;
import com.zdc.business.business.handle.strategy.AbstractBHandle;
import com.zdc.business.business.wrapper.CommonBName;
import com.zdc.business.business.util.AssertUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

public class StrategyBContext extends AbstractBContext<AbstractBHandle> {

    @Override
    public void register(AbstractBHandle handle) {
        //获取处理器属性名称
        CommonBName<String> commonBName =  handle.getName();
        AssertUtil.notNull(commonBName,"处理器名称不能为空！");
        AbstractBFactory abstractBFactory = factoryMap.get(commonBName.getType());
        if (Objects.isNull(abstractBFactory)){
            abstractBFactory=new CommonBFactory();
            factoryMap.put(commonBName.getType(),abstractBFactory);
        }
        abstractBFactory.register(handle, commonBName.getAlias());
    }

    @Override
    public   <R> R invoke(String handleType, String handleName, Object context, Class<R> resp) {
        AbstractBHandle handle = getHandle(handleType, handleName);
        Object result = handle.invoke(context);
        return (R)result;
    }

    /**
     * 获取处理器
     */
    public AbstractBHandle getHandle(String type, String key){
        AbstractBFactory handleFactory = super.getHandleFactory(type);
        AssertUtil.notNull(handleFactory,"查询不到类型为“{}”的策略工厂",type);
        Object handle = handleFactory.get(key);
        AssertUtil.notNull(handleFactory,"查询不到类型为“{}”策略处理器",type);

        return (AbstractBHandle)handle;
    }
}
