package com.zdc.business.business.annotation;

import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.test.chains.ChainsEnumBFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterceptorEnhance {
    //拦截器
    Class[] intecepter () ;
}
