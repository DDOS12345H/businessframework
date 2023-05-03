package com.zdc.business.business.test;

import com.zdc.business.business.context.CommonBContext;
import com.zdc.business.business.domain.BReqContext;
import com.zdc.business.business.domain.BRespContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.zdc.business.business");
        Object myBusinessHandle = context.getBean("myBusinessHandle");
        System.out.println(myBusinessHandle);
        CommonBContext commonBContext = (CommonBContext) context.getBean("commonBContext");

        BRespContext invoke = commonBContext.invoke("ZDC", "T", new BReqContext(), BRespContext.class);
        System.out.println(invoke);
    }
}
