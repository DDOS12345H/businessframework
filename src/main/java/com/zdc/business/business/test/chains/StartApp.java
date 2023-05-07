package com.zdc.business.business.test.chains;

import com.zdc.business.business.context.ChainsBContext;
import com.zdc.business.business.test.domain.BReqContext;
import com.zdc.business.business.test.domain.BRespContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.zdc.business.business");

        ChainsBContext chainsBContext = (ChainsBContext) context.getBean("chainsBContext");
        chainsBContext.start(ChainsEnumBFactory.CHAINS_DEFAULT_TYPE_1.getType(),new BReqContext(), BRespContext.class);
        chainsBContext.execute(ChainsEnumBFactory.CHAINS_DEFAULT_TYPE_2.getType(),ChainsEnumBFactory.CHAINS_DEFAULT_TYPE_2.getName()
                ,new BReqContext(), BRespContext.class);
    }
}
