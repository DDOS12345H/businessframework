package com.zdc.business.business.test;

import com.zdc.business.business.context.AdapterBContext;
import com.zdc.business.business.context.StrategyBContext;
import com.zdc.business.business.test.domain.BReqContext;
import com.zdc.business.business.test.domain.BRespContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApp {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.zdc.business.business");
/*            StrategyBContext strategyBContext = (StrategyBContext) context.getBean("strategyBContext");

            BRespContext invoke = strategyBContext.invoke("ZDC", "T", new BReqContext(), BRespContext.class);
            System.out.println(invoke);*/
        }
        //测试适配器模式
/*    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.zdc.business.business");
        AdapterBContext adapterBContext = (AdapterBContext) context.getBean("adapterBContext");
        adapterBContext.execute("ZDC",new BReqContext());

    }*/
}
