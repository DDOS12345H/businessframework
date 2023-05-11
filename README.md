# 前言

做开发也有好几年时间了，最近总结和梳理自己在工作中遇到的一些问题，工作中最容易写出BUG的需求就是改造需求了。一个成熟的业务系统是需要经过无数次迭代而成的，也意味着经过很多开发人员之手，最后到你这里，大部分时间都是在梳理代码，理解别人的用意。有的业务功能里面一堆IF嵌套嵌套，耦合度太过，理解起来比较费劲，也容易改出BUG。

不同人有不同的编码习惯，这没有办法，但如果系统中能用一些常用的设计模式来编码，那多多少少能增加可读性，降低耦合度，所以想做出几种常用的设计模式工具类，开发时可以直接使用，让我们更加专注于业务代码开发。



# 正文

小框架基于常用的设计模式，策略模式、模板方法模式、工厂模式、责任链模式等，结合Spring IOC，Spring AOP，Springboot自动装配；

**主要有4个通用的设计模式处理器：**

## 通用策略模式处理器

### 业务场景

购买保险产品的费用计算方法有多种，按日计算、按月计算、按费率表计算。不同产品可选择的计费选项可能不一样，如下：

日计算（01）：支持  A产品、B产品

月计算（02）：支持	A产品、C产品

费率表计算（03）：支持	A产品、B产品、C产品

### 代码演示



```java
        //计算类型
        String calculateType="01";
        //产品编号
        String productNo="A";
        if(calculateType.equals("01")){
            if ("A,B".contains(productNo)){
                //按日计算
            }
        }else if(calculateType.equals("02")){
            if ("A,C".contains(productNo)){
                //按月计算
            }
        }else if(calculateType.equals("03")){
            if ("A,B,C".contains(productNo)){
                //按费率表计算
            }
        }

```

上面这种场景如果使用 if..else...处理的话，随着代码不断迭代，其可读性、调整成本会变得越来越大；



下面使用策略模式来演示：



定义处理器，继承策略处理器接口

```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.strategy.AbstractBHandle;
import com.zdc.business.business.wrapper.CommonBName;

import java.util.Arrays;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.strategy
 * @Date：2023/5/11 20:14
 * @Wechat：DDOS12345H
 * 按日计算
 */
@BComponent
public class Calculate01Handle extends AbstractBHandle<RequestDto,ResponseDto> {
    @Override
    public boolean before(RequestDto requestDto) {
        return true;
    }

    @Override
    public boolean after(RequestDto requestDto) {
        return true;
    }

    @Override
    public ResponseDto doExecute(RequestDto requestDto) {
        System.out.println("按日计算");
        return null;
    }

    @Override
    public CommonBName getName() {
        //定义该处理器的类型名称，以及支持的别名集；执行时按这两个维度匹配处理器
        return new CommonBName<String>("01", Arrays.asList("A","B"));
    }
}

```



```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.strategy.AbstractBHandle;
import com.zdc.business.business.wrapper.CommonBName;

import java.util.Arrays;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.strategy
 * @Date：2023/5/11 20:14
 * @Wechat：DDOS12345H
 * 按日计算
 */
@BComponent
public class Calculate02Handle extends AbstractBHandle<RequestDto,ResponseDto> {
    @Override
    public boolean before(RequestDto requestDto) {
        return true;
    }

    @Override
    public boolean after(RequestDto requestDto) {
        return true;
    }

    @Override
    public ResponseDto doExecute(RequestDto requestDto) {
        System.out.println("按月计算");
        return null;
    }

    @Override
    public CommonBName getName() {
        //定义该处理器的类型名称，以及支持的别名集；执行时按这两个维度匹配处理器
        return new CommonBName<String>("02", Arrays.asList("A","C"));
    }
}
```



```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.strategy.AbstractBHandle;
import com.zdc.business.business.wrapper.CommonBName;

import java.util.Arrays;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.strategy
 * @Date：2023/5/11 20:14
 * @Wechat：DDOS12345H
 * 按日计算
 */
@BComponent
public class Calculate03Handle extends AbstractBHandle<RequestDto,ResponseDto> {
    @Override
    public boolean before(RequestDto requestDto) {
        return true;
    }

    @Override
    public boolean after(RequestDto requestDto) {
        return true;
    }

    @Override
    public ResponseDto doExecute(RequestDto requestDto) {
        System.out.println("按费率表计算");
        return null;
    }

    @Override
    public CommonBName getName() {
        //定义该处理器的类型名称，以及支持的别名集；执行时按这两个维度匹配处理器
        return new CommonBName<String>("03", Arrays.asList("A","B","C"));
    }
}
```

定义入参类、出参类；

```java
/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:48
 * @Wechat：DDOS12345H
 */
public class ResponseDto {
    //...
}
```



```java
/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:48
 * @Wechat：DDOS12345H
 */
public class ResponseDto {
    //...
}

```



运行用例

```java
import com.zdc.business.business.context.StrategyBContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.strategy
 * @Date：2023/5/10 19:21
 * @Wechat：DDOS12345H
 */
public class StartApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext("com.example.btest");
        StrategyBContext strategyBContext = (StrategyBContext) applicationContext.getBean("strategyBContext");

        //计算类型
        String calculateType="01";
        //产品编号
        String productNo="A";
        RequestDto requestDto=new RequestDto();
        ResponseDto execute = strategyBContext.invoke(calculateType,productNo,requestDto,ResponseDto.class);

    }
}
```

![image-20230511203416531](C:\Users\huang\AppData\Roaming\Typora\typora-user-images\image-20230511203416531.png)



## 通用适配器模式处理器

### 业务场景

现有公司A和公司B进行投保出单，出完单后需要通知相关人员。

公司A：需要邮件、短信通知投保人；

公司B：需要邮件、短信通知被保人，企信通知业务员；

### 代码演示

```java
/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:48
 * @Wechat：DDOS12345H
 */
@Data
public class RequestDto {
	//定义请求参数
    String companyType;

}
```



```java
/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:48
 * @Wechat：DDOS12345H
 */
public class ResponseDto {
    //定义相应参数
}

```



定义A公司适配器

```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.factory.IAdapterEnumBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandlesAdapter;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:46
 * @Wechat：DDOS12345H
 */
@BComponent
public class NotifyCompanyA extends AbstractHandlesAdapter<RequestDto, ResponseDto> {


    @Override
    public boolean isSupport(RequestDto context) {
        //该方法用于编写适配条件
        if (context.getCompanyType().equals("A")){

            return true;
        }
        return false;
    }

    @Override
    public ResponseDto execute(RequestDto context) {
        System.out.println("发邮件通知投保人");
        System.out.println("发短信通知投保人");
        return null;
    }

    @Override
    public IAdapterEnumBFactory getType() {
        //定义该适配器归属类型
        return ChannelIAdapterEnumBFactory.CHANNEL_NOTIFY;
    }
}
```

定义枚举参数

```java
import com.zdc.business.business.factory.IAdapterEnumBFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 22:30
 * @Wechat：DDOS12345H
 */
@Getter
@AllArgsConstructor

public enum  ChannelIAdapterEnumBFactory implements IAdapterEnumBFactory {
    CHANNEL_NOTIFY("notify",10,"公司消息通知处理器"),
    ;

    String type;
    int priorityOrder;
    String description;
}
```



定义B公司通知适配器

```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.factory.IAdapterEnumBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandlesAdapter;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:46
 * @Wechat：DDOS12345H
 */
@BComponent
public class NotifyCompanyB extends AbstractHandlesAdapter<RequestDto, ResponseDto> {


    @Override
    public boolean isSupport(RequestDto context) {
        //该方法用于编写适配条件
        if (context.getCompanyType().equals("B")){

            return true;
        }
        return false;
    }

    @Override
    public ResponseDto execute(RequestDto context) {
        System.out.println("发邮件通知投保人");
        System.out.println("发短信通知投保人");
        System.out.println("企信通知业务员");
        return null;
    }

    @Override
    public IAdapterEnumBFactory getType() {
        //定义该适配器归属类型
        return ChannelIAdapterEnumBFactory.CHANNEL_NOTIFY;
    }
}
```



入口代码

```java
import com.zdc.business.business.context.AdapterBContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 21:15
 * @Wechat：DDOS12345H
 */
public class StratApp {
    public static void main(String[] args) {
        //SpringBoot环境下可直接使用@Autowire
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext("com.example.btest");
        AdapterBContext adapterBContext = (AdapterBContext) applicationContext.getBean("adapterBContext");

        //假设当前是A公司投保
        RequestDto requestDto=new RequestDto();
        requestDto.setCompanyType("A");
        ResponseDto execute = adapterBContext.execute(ChannelIAdapterEnumBFactory.CHANNEL_NOTIFY.type, requestDto, ResponseDto.class);

    }
}
```

![image-20230511203505728](C:\Users\huang\AppData\Roaming\Typora\typora-user-images\image-20230511203505728.png)



## 通用责任链模式处理器

### 业务场景

在录单系统中，录单员填写完资料，通常下一步需要提交审核，而在正式提交审核之前，系统需要校验数据是否符合要求。某些场景下不想完全卡主流程，通常会以软提示的方式在前端进行提醒；现有以下4种软提示校验（从上到下校验顺序）：

![软提示](C:\Users\huang\Desktop\临时目录\业务框架\软提示.png)



为了提高体验，当系统抛出资料A校验后，录单员点击“是”进行重新提交，此时由于前面已经点击了“是”了，此时后端不应该再对点击”是“的校验器进行校验。通常这种需要给每个校验器都设置一个标识，当为“是”时，后端跳过校验，但如果校验场景较多时，那代码将难以维护。

现使用责任链模式来处理以上场景



### 代码演示

定义好请求参数类和相应参数类

```java
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:48
 * @Wechat：DDOS12345H
 */
@Data
@AllArgsConstructor
public class RequestDto {
    String data;

```



```java
/**
 * @Author：猪大肠
 * @Package：com.example.btest.adapter
 * @Date：2023/5/10 20:48
 * @Wechat：DDOS12345H
 */
public class ResponseDto {
}

```



```java
import com.zdc.business.business.factory.IChainsEnumBFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.chain
 * @Date：2023/5/11 21:04
 * @Wechat：DDOS12345H
 */
@Getter
@AllArgsConstructor
public enum OrderCheckEnumBFactory implements IChainsEnumBFactory {
        ORDER_CHECK_SOFT_A("order","checkA",0,"资料A校验器"),
        ORDER_CHECK_SOFT_B("order","checkB",1,"资料B校验器"),
        ORDER_CHECK_SOFT_C("order","checkC",2,"资料C校验器"),
    ;
	//处理器类型，标记所属链
    String type;
	//处理器名称
    String name;
	//优先级顺序
    int priorityOrder;
	//描述
    String description;
}

```

自定义异常类

```java
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.chain
 * @Date：2023/5/11 21:12
 * @Wechat：DDOS12345H
 */
@AllArgsConstructor
@Data
public class SoftTipException extends RuntimeException{
    private String code;
    private String desc;

}
```

定义校验器

```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.handle.chains.AbstractChainHandle;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.chain
 * @Date：2023/5/11 21:02
 * @Wechat：DDOS12345H
 */
@BComponent
public class CheckAHandle extends AbstractChainHandle<RequestDto,ResponseDto> {
    @Override
    public ResponseDto execute(RequestDto context) {
        System.out.println("校验器A");
        if (context.equals("A")){
            //抛出异常，返回下个处理器名称；下次携带这个名称来找到当前节点
            throw new SoftTipException(getNextNode()==null?"succeed":getNextNode().getHandleName(),"资料A可能存在风险，是否继续提交？");
        }else{
            //调用下个节点校验器
            executeNextNode(context);
        }
        return null;
    }

    @Override
    public IChainsEnumBFactory getType() {
        return OrderCheckEnumBFactory.ORDER_CHECK_SOFT_A;
    }
}

```



```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.handle.chains.AbstractChainHandle;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.chain
 * @Date：2023/5/11 21:02
 * @Wechat：DDOS12345H
 */
@BComponent
public class CheckBHandle extends AbstractChainHandle<RequestDto,ResponseDto> {
    @Override
    public ResponseDto execute(RequestDto context) {
        System.out.println("校验器B");
        if (context.equals("B")){
            //抛出异常，返回下个处理器名称；下次携带这个名称来找到当前节点
            throw new SoftTipException(getNextNode()==null?"succeed":getNextNode().getHandleName(),"资料B可能存在风险，是否继续提交？");
        }else{
            //调用下个节点校验器
            executeNextNode(context);
        }
        return null;
    }

    @Override
    public IChainsEnumBFactory getType() {
        return OrderCheckEnumBFactory.ORDER_CHECK_SOFT_B;
    }
}

```



```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.factory.IChainsEnumBFactory;
import com.zdc.business.business.handle.chains.AbstractChainHandle;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.chain
 * @Date：2023/5/11 21:02
 * @Wechat：DDOS12345H
 */
@BComponent
public class CheckCHandle extends AbstractChainHandle<RequestDto,ResponseDto> {
    @Override
    public ResponseDto execute(RequestDto context) {
        System.out.println("校验器C");
        if (context.equals("C")){
            //抛出异常，返回下个处理器名称；下次携带这个名称来找到当前节点
            throw new SoftTipException(getNextNode()==null?"succeed":getNextNode().getHandleName(),"资料C可能存在风险，是否继续提交？");
        }else{
            //调用下个节点校验器
            executeNextNode(context);
        }
        return null;
    }

    @Override
    public IChainsEnumBFactory getType() {
        return OrderCheckEnumBFactory.ORDER_CHECK_SOFT_C;
    }
}
```

运行用例

```java
import com.zdc.business.business.context.ChainsBContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.strategy
 * @Date：2023/5/10 19:21
 * @Wechat：DDOS12345H
 */
public class StartApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext("com.example.btest");
        ChainsBContext chainsBContext = (ChainsBContext) applicationContext.getBean("chainsBContext");
        //校验标识
        String checkFlag="checkB";
        if (!"succeed".equals(checkFlag)){
            if ("start".equals(checkFlag)){
                chainsBContext.start("order",new RequestDto(checkFlag),null);
            }
            chainsBContext.execute("order",checkFlag,new RequestDto(checkFlag),null);

        }

    }
}
```

![image-20230511223949698](C:\Users\huang\AppData\Roaming\Typora\typora-user-images\image-20230511223949698.png)



## 通用代理模式处理器

###  业务场景

与其它周边系统进行交互时，需要将请求报文和响应报文记录到ES中，方便后续排查，并对请求报文加密加签名，响应报文解密验签；

考虑到复用性等方面，所以这里使用代理模式来增强方法最合适不过了。

### 代码演示

定义ES日志记录增强器

```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.proxy.AbstractBEnhanceIntecepter;
import com.zdc.business.business.wrapper.IntecepterProceedWrapper;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.aop
 * @Date：2023/5/11 22:58
 * @Wechat：DDOS12345H
 */
@BComponent
public class EnhanceEsHandle extends AbstractBEnhanceIntecepter {

    @Override
    public Object execute(IntecepterProceedWrapper ipw) {
        //方法参数
        Object[] args = ipw.getArgs();
        System.out.println("请求参数："+args[0].toString());
        //调用真正的执行方法
        Object result = ipw.proceed();
        System.out.println("响应参数："+args[0].toString());

        return result;
    }
}

```

加解密增强器

```java
import com.zdc.business.business.annotation.BComponent;
import com.zdc.business.business.handle.proxy.AbstractBEnhanceIntecepter;
import com.zdc.business.business.wrapper.IntecepterProceedWrapper;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.aop
 * @Date：2023/5/11 22:58
 * @Wechat：DDOS12345H
 */
@BComponent
public class EnhanceEncryHandle extends AbstractBEnhanceIntecepter {

    @Override
    public Object execute(IntecepterProceedWrapper ipw) {
        //方法参数
        Object[] args = ipw.getArgs();
        System.out.println("对请求报文加密：");
        System.out.println("对请求报文加签：");
        //调用真正的执行方法
        Object result = ipw.proceed();
        System.out.println("对请求报文解密：");
        System.out.println("对请求报文验签：");

        return result;
    }
}
```

被增强类

```java
import com.zdc.business.business.annotation.InterceptorEnhance;
import org.springframework.stereotype.Component;

/**
 * @Author：猪大肠
 * @Package：com.example.btest.aop
 * @Date：2023/5/11 23:06
 * @Wechat：DDOS12345H
 */
@Component
public class HttpToCompanyA {
    //按顺利指定增强器
    @InterceptorEnhance(intecepter = {EnhanceEsHandle.class,EnhanceEncryHandle.class})
    public String sendInfo(String request){
        return  "{code:\"0\",text:\"成功\"}";
    }

}
```

运行用例

![image-20230511231923106](C:\Users\huang\AppData\Roaming\Typora\typora-user-images\image-20230511231923106.png)



## 依赖

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        //打包到本地仓库后，引入使用
        <dependency>
            <groupId>com.zdc.business</groupId>
            <artifactId>business</artifactId>
            <version>0.0.1</version>
        </dependency>
    </dependencies>
```





# 总结

本人3年多开发经验，对于各方面认识有限。欢迎老师们指出改进之处，有好的建议或者有想法大家可以交流探讨，一起完善。

