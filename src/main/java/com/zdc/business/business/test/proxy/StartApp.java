package com.zdc.business.business.test.proxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApp {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.zdc.business.business");
            HttpTo httpTo = (HttpTo) context.getBean("httpTo");
            String result = httpTo.toGet("http://www.baidu.com");
            System.out.println(result);
        }

}
