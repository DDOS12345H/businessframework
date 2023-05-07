package com.zdc.business.business.handle.adapter;

import com.zdc.business.business.context.AdapterBContext;
import com.zdc.business.business.factory.IAdapterEnumBFactory;
import com.zdc.business.business.handle.HandleBRegister;
import com.zdc.business.business.util.SpringUtil;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractHandlesAdapter<T,R> extends HandleBRegister {
    /**
     * 是否支持
     * @return
     */
    public abstract boolean isSupport(T context);

    /**
     * 执行处理
     * @return
     */
    public abstract R execute(T context);

    /**
     * 执行完后，是否中断
     * @throws Exception
     */
    public boolean isInterrupt(){
        return false;
    }

    /**
     * 适配器类型
     * @throws Exception
     */
    public abstract IAdapterEnumBFactory getType();

    @Override
    public void afterPropertiesSet() throws Exception {
        AdapterBContext adapterBContext = (AdapterBContext) SpringUtil.getBean("adapterBContext");
        adapterBContext.register(this);
    }
}
