package com.zdc.business.business.handle;

import com.zdc.business.business.context.CommonBContext;
import com.zdc.business.business.domain.BName;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractBHandle<T, R> implements InitializingBean, ApplicationContextAware {
    //异常时，抛出
    private boolean isThrow=true;
    private ApplicationContext applicationContext;

    public <R>R invoke(T t) {
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
                invokeGlobalExceptionHandle(e);
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
    public void afterPropertiesSet() throws Exception {
        CommonBContext commonBContext = (CommonBContext) applicationContext.getBean("commonBContext");
        commonBContext.register(this);

    }
    public void invokeGlobalExceptionHandle(Exception e){
        //获取所有异常处理适配器
        Map<String, IExceptionHandleAdapter> beansOfType = applicationContext.getBeansOfType(IExceptionHandleAdapter.class);
        Collection<IExceptionHandleAdapter> handles = beansOfType.values();
        for (IExceptionHandleAdapter handle : handles) {
            //判断是否支持
            if (handle.isSupport(this,e)){
                handle.execute(this,e);
            }

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
    public abstract <R>R doExecute(T t);

    /**
     * 处理器类型、别名
     *
     * @return
     */
    public abstract BName getName();

    /**
     * 异常处理
     * @param e
     * @return
     */
    public abstract boolean executeException(Exception e);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
