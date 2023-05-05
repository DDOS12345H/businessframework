package com.zdc.business.business.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@BComponent
public @interface ChainsBComponent {


    /**
     * 优先级
     * @return
     */
    int priorityOrder() default 100;
}
