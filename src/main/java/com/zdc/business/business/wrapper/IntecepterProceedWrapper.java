package com.zdc.business.business.wrapper;

import com.zdc.business.business.handle.proxy.BEnhanceIntecepter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

/**
 * @Author：猪大肠
 * @Package：com.zdc.business.business.wrapper
 * @Date：2023/5/7 21:18
 * @Wechat: DDOS12345H
 */
public class IntecepterProceedWrapper {
    //拦截器集
    private List<BEnhanceIntecepter> intecepters;
    //目标方法
    private ProceedingJoinPoint point;
    //当前拦截器指针
    private Integer index=0;

    public Object proceed(){
        if (intecepters.size()==index){
            try {
                //真正调用目标方法
                return point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        //调用拦截器
        BEnhanceIntecepter BEnhanceIntecepter = intecepters.get(index++);
        return BEnhanceIntecepter.execute(this);
    }

    /**
     * 获取方法参数
     * @return
     */
    public Object[] getArgs(){
      return  point.getArgs();
    }

    public IntecepterProceedWrapper(List<BEnhanceIntecepter> intecepters, ProceedingJoinPoint point) {
        this.intecepters = intecepters;
        this.point = point;
    }

    public List<BEnhanceIntecepter> getIntecepters() {
        return intecepters;
    }



    public ProceedingJoinPoint getPoint() {
        return point;
    }


}
