package com.zdc.business.business.handle.proxy;

import com.zdc.business.business.context.IntecepterBContext;
import com.zdc.business.business.util.SpringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.wrapper
 * @Date：2023/5/7 21:18
 * @Wechat: DDOS12345H
 */

@Aspect
public class BIntecepterAspect {
    @Pointcut("@annotation(com.zdc.business.business.annotation.InterceptorEnhance)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Object enhance(ProceedingJoinPoint point){
        IntecepterBContext intecepterBContext = (IntecepterBContext) SpringUtil.getBean("intecepterBContext");
        return intecepterBContext.invokeIntecepter(point);
    }
}
