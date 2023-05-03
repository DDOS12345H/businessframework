package com.zdc.business.business.context;

import com.zdc.business.business.handle.AbstractBHandle;
import com.zdc.business.business.handle.IBHandle;
import com.zdc.business.business.stereotype.BComponent;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class CommonBContext extends AbstractBContext {

    @Override
    public <R> R invoke(String handleType, String handleName, Object context, Class<R> resp) {
        return super.invoke(handleType, handleName, context, resp);
    }
}
