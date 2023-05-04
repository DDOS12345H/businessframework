package com.zdc.business.business.context;

import com.zdc.business.business.factory.AbstractBFactory;
import com.zdc.business.business.factory.CommonBFactory;
import com.zdc.business.business.handle.adapter.AbstractHandleAdapter;
import com.zdc.business.business.stereotype.AdapterBComponent;
import com.zdc.business.business.util.AssertUtil;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdapterBContext extends AbstractBContext<AbstractHandleAdapter> {


    @Override
    public void register(AbstractHandleAdapter handle) {
        Class<? extends AbstractHandleAdapter> aClass = handle.getClass();
        AdapterBComponent annotation = aClass.getAnnotation(AdapterBComponent.class);
        //获取处理器属性名称
        String adapterType = annotation.type();
        Integer priorityOrder = annotation.priorityOrder();
        AssertUtil.notNull(adapterType,"适配器类型名称不能为空");
        AbstractBFactory abstractBFactory = factoryMap.get(adapterType);
        if (Objects.isNull(abstractBFactory)){
            abstractBFactory=new CommonBFactory(new TreeMap<Integer,AbstractHandleAdapter>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return (int)o1-02;
                }
            }));
            factoryMap.put(adapterType,abstractBFactory);
        }
        abstractBFactory.register(handle, Arrays.asList(abstractBFactory.get().size()+priorityOrder));

    }

    @Override
    public <R> R invoke(String handleType, String handleName, Object context, Class<R> resp) {
        List<AbstractHandleAdapter> handles = getHandle(handleType, handleName);
        //记录被处理次数
        Integer result=0;
        for (AbstractHandleAdapter handle : handles) {
            if (handle.isSupport(context)){
                 handle.execute(context);
                 result++;
            }

        }
        return (R)result;
    }

    /**
     * 执行入口
     * @param handleType
     * @param context
     * @return
     */
    public int execute(String handleType,  Object context) {
       return this.invoke(handleType,null,context,Integer.class);
    }

    /**
     * 获取处理器
     */
    public List<AbstractHandleAdapter> getHandle(String type, String key){
        AbstractBFactory handleFactory = super.getHandleFactory(type);
        AssertUtil.notNull(handleFactory,"查询不到处理器工厂");
        Collection handles = handleFactory.get();
        AssertUtil.notNull(handleFactory,"查询不到处理器集合");

        return new ArrayList<>(handles);
    }
}
