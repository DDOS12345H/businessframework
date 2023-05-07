package com.zdc.business.business.handle.strategy;

import com.zdc.business.business.context.AdapterBContext;
import com.zdc.business.business.context.StrategyBContext;
import com.zdc.business.business.handle.HandleBRegister;
import com.zdc.business.business.test.adapter.AdapterEnumBFactory;
import com.zdc.business.business.wrapper.BExceptionWrapper;
import com.zdc.business.business.wrapper.CommonBName;
import com.zdc.business.business.util.SpringUtil;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractBHandle<T, R> extends HandleBRegister {
    //异常时，抛出
    private boolean isThrow=true;

    public R invoke(T t) {
        //前置处理
        if (!before(t)) {
            return null;
        }
        try {
            //业务处理
            return  doExecute(t);
        } catch (Exception e) {
            if (!executeException(e)){
                //执行全局异常处理
                invokeGlobalExceptionHandle(
                        BExceptionWrapper.builder()
                        .context(t)
                        .exception(e).build()
                );
            }
            if (isThrow){
                throw e;
            }
        } finally {
            //后置处理
            after(t);
        }
        return null;
    }

    @Override
    public void afterPropertiesSet()   {
        StrategyBContext strategyBContext = (StrategyBContext) SpringUtil.getBean("strategyBContext");
        strategyBContext.register(this);

    }
    public void invokeGlobalExceptionHandle(BExceptionWrapper contenxt){
        //获取所有异常处理适配器
        AdapterBContext adapterBContext = (AdapterBContext) SpringUtil.getBean("adapterBContext");
        try {
            adapterBContext.execute(AdapterEnumBFactory.ADAPTER_GLOBAL_EXCEPTION_TYPE_NAME.getType(),contenxt);
        }catch (Exception e){
            e.printStackTrace();
        }
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

    /**
     * 异常处理
     * @param e
     * @return
     */
    public abstract boolean executeException(Exception e);



}
