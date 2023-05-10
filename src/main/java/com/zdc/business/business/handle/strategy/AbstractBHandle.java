package com.zdc.business.business.handle.strategy;

import com.zdc.business.business.context.AdapterBContext;
import com.zdc.business.business.context.StrategyBContext;
import com.zdc.business.business.factory.ExceptionEnumBFactory;
import com.zdc.business.business.handle.HandleBRegister;
import com.zdc.business.business.wrapper.BExceptionWrapper;
import com.zdc.business.business.wrapper.CommonBName;
import com.zdc.business.business.util.SpringUtil;

public abstract class AbstractBHandle<T, R> extends HandleBRegister {


    public R invoke(T t) {
        //前置处理
        if (!before(t)) {
            return null;
        }
        try {
            //业务处理
            return doExecute(t);
        } catch (Exception e) {

            throw e;

        } finally {
            //后置处理
            after(t);
        }
    }

    @Override
    public void afterPropertiesSet() {
        StrategyBContext strategyBContext = (StrategyBContext) SpringUtil.getBean("strategyBContext");
        strategyBContext.register(this);

    }



    /**
     * 前置处理
     *
     * @param t
     * @return
     */
    public abstract boolean before(T t);

    /**
     * 后置处理
     *
     * @param t
     * @return
     */
    public abstract boolean after(T t);

    /**
     * 业务处理
     *
     * @param t
     * @return
     */
    public abstract R doExecute(T t);

    /**
     * 处理器类型、别名
     *
     * @return
     */
    public abstract CommonBName getName();




}
