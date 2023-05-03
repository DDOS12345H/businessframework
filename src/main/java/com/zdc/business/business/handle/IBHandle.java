package com.zdc.business.business.handle;

import com.zdc.business.business.domain.BName;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;

public interface IBHandle<T,R>  {

     boolean before(T t);
     boolean after(T t);
//    public <R>R invoke(T t);
     R doExecute(T t);
     BName getName();
}
