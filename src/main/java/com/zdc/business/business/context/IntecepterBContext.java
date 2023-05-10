package com.zdc.business.business.context;

import com.zdc.business.business.annotation.InterceptorEnhance;
import com.zdc.business.business.handle.proxy.BEnhanceIntecepter;
import com.zdc.business.business.wrapper.IntecepterProceedWrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class IntecepterBContext extends AbstractBContext<BEnhanceIntecepter> {
    Map<String, BEnhanceIntecepter> intecepterMap;

    /**
     * 方法增强处理
     * @param point
     * @return
     */
    public Object invokeIntecepter(ProceedingJoinPoint point) {
        //获取目标方法
        MethodInvocationProceedingJoinPoint methodJoinPoint=(MethodInvocationProceedingJoinPoint)point;
        MethodSignature signature = (MethodSignature) methodJoinPoint.getSignature();
        Method method = signature.getMethod();
        List<BEnhanceIntecepter> intecepters = matchIntecepters(method);
        //没有拦截器可以处理，则直接调用目标方法
        if (intecepters.size() == 0) {
            try {
                return point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return new IntecepterProceedWrapper(intecepters,point).proceed();

    }

    @Override
    public void register(BEnhanceIntecepter handle) {
        intecepterMap.put(handle.getClass().getName(), handle);
    }

    @Override
    public Object invoke(String handleType, String handleName, Object context, Class resp) {
        return null;
    }

    /**
     * 获取匹配的拦截器
     */
    public List<BEnhanceIntecepter> matchIntecepters(Method method) {
        List<BEnhanceIntecepter> intecepterList = new ArrayList<>();
        InterceptorEnhance annotation = method.getAnnotation(InterceptorEnhance.class);
        if (annotation == null) {
            return null;
        }
        //获取注解中配置的拦截器
        Class[] intecepters = annotation.intecepter();
        if (intecepters.length == 0) {
            return null;
        }
        //获取匹配的拦截器
        for (Class intecepter : intecepters) {
            BEnhanceIntecepter BEnhanceIntecepter = intecepterMap.get(intecepter.getName());
            if (BEnhanceIntecepter == null) {
                continue;
            }
            intecepterList.add(BEnhanceIntecepter);
        }
        return intecepterList;

    }

    public IntecepterBContext() {
        this.intecepterMap = new HashMap<>();
    }
}
